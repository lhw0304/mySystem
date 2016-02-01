package com.mode.util;

/**
 * Created by Lei on 8/7/15.
 */
public class HexHandler {

    private static String hexToASCII(String hex) {
        if (hex.length() % 2 != 0) {
            System.err.println("requires EVEN number of chars");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        //Convert Hex 0232343536AB into two characters stream.
        for (int i = 0; i < hex.length() - 1; i += 2) {
               /*
                * Grab the hex in pairs
                */
            String output = hex.substring(i, (i + 2));
              /*
               * Convert Hex to Decimal
               */
            int decimal = Integer.parseInt(output, 16);
            sb.append((char) decimal);
        }
        return sb.toString();
    }

    private static String asciiToHex(String ascii) {
        StringBuilder hex = new StringBuilder();

        for (int i = 0; i < ascii.length(); i++) {
            hex.append(Integer.toHexString(ascii.charAt(i)));
        }
        return hex.toString();
    }

    // Decode params into plain text characters
    public static String decodeParams(String encodedParams) {
        return hexToASCII(encodedParams);
    }

    // Encode params
    public static String encodeParams(String params) {return asciiToHex(params);}
}