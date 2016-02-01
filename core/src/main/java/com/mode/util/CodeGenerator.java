package com.mode.util;

import java.util.Random;

/**
 * Generate invitation code with 6 chars (2-9, A-Z).
 *
 * Created by zhaoweiwei on 15/10/9.
 */
public class CodeGenerator {

    // Exclude 0,1 which confuse with O,L, exclude O for make up len
    private static final char[] rule = new char[]{'g', 'h', 'q', 'w', 'e', '8', 'a', 's','2', 'd',
                                                  'z', 'x', '9', 'c', '7', 'p', 'n', '6','5', 'i',
                                                  'k', '3', 'm', 'j', 'u', 'f', 'r', '4','v', 'y',
                                                  'l', 't', 'b' };
    // Indicator the str after O is random generation for make up len
    private static final char b = 'o';

    private static final int binLen = rule.length;

    private static final int len = 6;


    public static String toBase33(int n) {
        char[] buf = new char[32];
        int charPos = 32;

        while ((n / binLen) > 0) {
            int ind = (int) (n % binLen);
            buf[--charPos] = rule[ind];
            n /= binLen;
        }
        buf[--charPos] = rule[(int) (n % binLen)];
        String str = new String(buf, charPos, (32 - charPos));
        if (str.length() < len) {
            StringBuilder sb = new StringBuilder();
            sb.append(b);
            Random rnd = new Random();
            for (int i = 1; i < len - str.length(); i++) {
                sb.append(rule[rnd.nextInt(binLen)]);
            }
            str += sb.toString();
        }
        return str;
    }


    public static int fromBase33(String code) {
        code = code.toLowerCase();
        char chs[] = code.toCharArray();
        int res = 0;
        for (int i = 0; i < chs.length; i++) {
            int ind = 0;
            for (int j = 0; j < binLen; j++) {
                if (chs[i] == rule[j]) {
                    ind = j;
                    break;
                }
            }
            if (chs[i] == b) {
                break;
            }
            if (i > 0) {
                res = res * binLen + ind;
            } else {
                res = ind;
            }
        }
        return res;
    }

    public static void main(String[] args){
        System.out.println(toBase33(2));
    }
}
