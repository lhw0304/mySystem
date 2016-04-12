package com.mode.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Lei on 11/19/15.
 */
public class SecuritySignedRequestHelper {

    public static void main(String[] args) {
        try {
            SecuritySignedRequestHelper helper = SecuritySignedRequestHelper.getInstance("post",
                    "http://localhost:8080/platform-2.0-user/v2/brands/test/abc",
                    "1", "lajsdlkjsldjlsjdks");
            String url = helper.sign("a=1&b=1&c=1&d=1");
            System.out.println(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * All strings are handled as UTF-8
     */
    private static final String UTF8_CHARSET = "UTF-8";

    /**
     * The HMAC algorithm required by Amazon
     */
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    private String requestMethod = null;
    private String requestURL = null;
    private String publicKey = null;
    private String privateKey = null;


    private SecretKeySpec secretKeySpec = null;
    private Mac mac = null;

    /**
     * You must provide the three values below to initialize the helper.
     *
     * @param requestMethod Method for the requests.
     * @param requestURL    Request urL for the request
     * @param publicKey     Your mode public key
     * @param privateKey    Your mode private key
     */
    public static SecuritySignedRequestHelper getInstance(
            String requestMethod,
            String requestURL,
            String publicKey,
            String privateKey
    ) throws IllegalArgumentException, UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException {
        if (null == requestMethod || requestMethod.length() == 0) {
            throw new IllegalArgumentException("requestMethod is null or empty");
        }
        if (null == requestURL || requestURL.length() == 0) {
            throw new IllegalArgumentException("requestURI is null or empty");
        }
        if (null == publicKey || publicKey.length() == 0) {
            throw new IllegalArgumentException("publicKey is null or empty");
        }
        if (null == privateKey || privateKey.length() == 0) {
            throw new IllegalArgumentException("privateKey is null or empty");
        }

        SecuritySignedRequestHelper instance = new SecuritySignedRequestHelper();
        instance.requestMethod = requestMethod.toLowerCase();
        instance.requestURL = requestURL;
        instance.publicKey = publicKey;
        instance.privateKey = privateKey;

        byte[] secretyKeyBytes = instance.privateKey.getBytes(UTF8_CHARSET);
        instance.secretKeySpec = new SecretKeySpec(secretyKeyBytes, HMAC_SHA256_ALGORITHM);
        instance.mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        instance.mac.init(instance.secretKeySpec);

        return instance;
    }

    /**
     * The construct is private since we'd rather use getInstance()
     */
    private SecuritySignedRequestHelper() {
    }

    /**
     * This method signs requests in query-string form. It returns a URL that
     * should be used to fetch the response. The URL returned should not be
     * modified in any way, doing so will invalidate the signature and Amazon
     * will reject the request.
     */
    public String sign(String urlParams) {
        // let's break the query string into it's constituent name-value pairs
        // e.g 'a=1&b=2&c=3'
        Map<String, String> params = this.createParameterMap(urlParams);

        // then we can sign the request as before
        return this.sign(params);
    }

    /**
     * This method signs requests in hashmap form. It returns a URL that should
     * be used to fetch the response. The URL returned should not be modified in
     * any way, doing so will invalidate the signature and Amazon will reject
     * the request.
     */
    public String sign(Map<String, String> params) {

        // Let's add the public key and Timestamp parameters to the request.
        params.put("publicKey", this.publicKey);

        params.remove("Signature");

        // The parameters need to be processed in lexicographical order, so we'll
        // use a TreeMap implementation for that.
        SortedMap<String, String> sortedParamMap = new TreeMap<String, String>(params);

        // get the canonical form the query string
        String canonicalQS = this.canonicalize(sortedParamMap);

        // create the string upon which the signature is calculated
        String toSign = requestMethod + "\n"
                + this.requestURL + "\n"
                + canonicalQS;

        // get the signature
        String hmac = this.hmac(toSign);
        String sig = this.percentEncodeRfc3986(hmac);

        // construct the URL
        String url = this.requestURL + "?" + canonicalQS + "&Signature=" + sig;

        return url;
    }


    public String getSignature(String urlParams) {
        // let's break the query string into it's constituent name-value pairs
        // e.g 'a=1&b=2&c=3'
        Map<String, String> params = this.createParameterMap(urlParams);

        // then we can sign the request as before
        return this.getSignature(params);
    }

    public String getSignature(Map<String, String> params) {
        // Let's add the public key and Timestamp parameters to the request.
        params.put("publicKey", this.publicKey);

        params.remove("Signature");

        // The parameters need to be processed in lexicographical order, so we'll
        // use a TreeMap implementation for that.
        SortedMap<String, String> sortedParamMap = new TreeMap<String, String>(params);

        // get the canonical form the query string
        String canonicalQS = this.canonicalize(sortedParamMap);

        // create the string upon which the signature is calculated
        String toSign = requestMethod + "\n"
                + this.requestURL + "\n"
                + canonicalQS;

        // get the signature
        String hmac = this.hmac(toSign);
        String sig = this.percentEncodeRfc3986(hmac);

        return sig;
    }


    /**
     * Compute the HMAC.
     *
     * @param stringToSign String to compute the HMAC over.
     * @return base64-encoded hmac value.
     */
    private String hmac(String stringToSign) {
        String signature = null;
        byte[] data;
        byte[] rawHmac;
        try {
            data = stringToSign.getBytes(UTF8_CHARSET);
            rawHmac = mac.doFinal(data);
            Base64 encoder = new Base64();
            signature = new String(encoder.encode(rawHmac));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(UTF8_CHARSET + " is unsupported!", e);
        }
        return signature;
    }

    /**
     * Generate a 13 bits UTC time
     *
     * @return UTC time
     */
    private String timestamp() {
        Long now = System.currentTimeMillis();
        return now.toString();
    }

    /**
     * Canonicalize the query string as required by Amazon.
     *
     * @param sortedParamMap Parameter name-value pairs in lexicographical order.
     * @return Canonical form of query string.
     */
    private String canonicalize(SortedMap<String, String> sortedParamMap) {
        if (sortedParamMap.isEmpty()) {
            return "";
        }

        StringBuffer buffer = new StringBuffer();
        Iterator<Map.Entry<String, String>> iter = sortedParamMap.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry<String, String> kvpair = iter.next();
            buffer.append(percentEncodeRfc3986(kvpair.getKey()));
            buffer.append("=");
            buffer.append(percentEncodeRfc3986(kvpair.getValue()));
            if (iter.hasNext()) {
                buffer.append("&");
            }
        }
        String cannoical = buffer.toString();
        return cannoical;
    }

    /**
     * Percent-encode values according the RFC 3986. The built-in Java
     * URLEncoder does not encode according to the RFC, so we make the
     * extra replacements.
     *
     * @param s decoded string
     * @return encoded string per RFC 3986
     */
    private String percentEncodeRfc3986(String s) {
        String out;
        try {
            out = URLEncoder.encode(s, UTF8_CHARSET)
                    .replace("+", "%20")
                    .replace("*", "%2A")
                    .replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            out = s;
        }
        return out;
    }

    /**
     * Takes a query string, separates the constituent name-value pairs
     * and stores them in a hashmap.
     *
     * @param queryString
     * @return
     */
    private Map<String, String> createParameterMap(String queryString) {
        Map<String, String> map = new HashMap<String, String>();
        String[] pairs = queryString.split("&");

        for (String pair : pairs) {
            if (pair.length() < 1) {
                continue;
            }

            String[] tokens = pair.split("=", 2);
            for (int j = 0; j < tokens.length; j++) {
                try {
                    tokens[j] = URLDecoder.decode(tokens[j], UTF8_CHARSET);
                } catch (UnsupportedEncodingException e) {
                }
            }
            switch (tokens.length) {
                case 1: {
                    if (pair.charAt(0) == '=') {
                        map.put("", tokens[0]);
                    } else {
                        map.put(tokens[0], "");
                    }
                    break;
                }
                case 2: {
                    map.put(tokens[0], tokens[1]);
                    break;
                }
            }
        }
        return map;
    }
}
