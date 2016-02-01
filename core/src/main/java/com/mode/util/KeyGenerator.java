package com.mode.util;

import java.security.Key;

import com.mode.config.BaseConfig;

import io.jsonwebtoken.impl.crypto.MacProvider;


/**
 * Created by Lei on 1/11/16.
 */
public class KeyGenerator {

    private static Key signingKey = MacProvider.generateKey(BaseConfig.SIGNATURE_ALGORITHM);

    public KeyGenerator(Key signingKey){
        this.signingKey = signingKey;
    }

    public static Key getKey(){
        return signingKey;
    }

    public static void refreshKey(){
        signingKey = MacProvider.generateKey(BaseConfig.SIGNATURE_ALGORITHM);
    }
}
