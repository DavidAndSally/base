package com.glodon.cloudt.rest.client.utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by chenxy on 2016/3/17.
 */
public class StringUtils {
    private static final String DEFAULT_CHARSET_NAME = "UTF-8";

    public static boolean isEmpty(String content) {
        return (null == content || content.length() == 0);
    }

    public static String getString(byte[] bytes) {
        if (null == bytes) {
            return null;
        }
        try {
            return new String(bytes, DEFAULT_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return new String(bytes);
        }
    }

    public static byte[] getBytes(String content) {
        if (isEmpty(content)) {
            return new byte[0];
        }
        try {
            return content.getBytes(DEFAULT_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return content.getBytes();
        }
    }
}
