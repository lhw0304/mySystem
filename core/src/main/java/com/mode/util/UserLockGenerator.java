package com.mode.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoweiwei on 15/12/17.
 */
public class UserLockGenerator {

    private static UserLockGenerator instance = new UserLockGenerator();

    private  Map<Integer, Object> locks = new HashMap<Integer, Object>();

    private Object getLock = new Object();

    private UserLockGenerator() {
    }

    public static UserLockGenerator getInstance() {
        return instance;
    }

    public Object getLock(Integer userId) {
        Object o = locks.get(userId);
        if (o != null) {
            return o;
        }
        synchronized(getLock) {
            o = locks.get(userId);
            if (o != null)
                return o;
            else {
                o = new Object();
                locks.put(userId, o);
                return o;
            }
        }
    }
}
