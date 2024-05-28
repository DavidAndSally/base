package com.glodon.cloudt.rest.client.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by chenxy on 2017/2/27.
 */
public class CryptoUtils {
    private static final String KEY_MD5 = "SHA-1";
    private static Random random = null;
    /**
     * 加密算法RSA
     */
    private static final String KEY_ALGORITHM = "RSA";
    /**
     * 公钥
     */
    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWO5bv4RnMb5IpHWfjauu4Ut/jYYD4JvUcbuII2EguPatcKtqJnRWuJ7VQytCH5Zn3K/2uqss3kqYde9CjsgEfCSK2yQ2rLe+ergqdjLSJpNmcwvBQRXB/LvXKD7ug4J6UNZ3sSeTpamxV4zE8zFsJxmGTZweTFmMnNeK3pYjb5wIDAQAB";
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    static {
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            random = new Random();
        }
    }

    public static String rsaEncode(String content) {
        try {
            byte[] keyBytes = base64Decode(PUBLIC_KEY);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicK = keyFactory.generatePublic(x509KeySpec);
            //对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicK);
            byte[] data = content.getBytes("UTF-8");
            int inputLen = data.length;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                outputStream.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = outputStream.toByteArray();
            outputStream.close();
            return base64Encode(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密传输数据
     *
     * @param plainText 原文
     * @param seeds     密钥种子
     * @return
     */
    public static String encrypt(String plainText, String seeds) {
        if (StringUtils.isEmpty(plainText) || StringUtils.isEmpty(seeds)) {
            return null;
        }
        byte[] data = plainText.getBytes();
        byte[] seedBytes = seeds.getBytes();
        byte[] cypherData = encrypt3DES(data, seedBytes);
        return convertBytesToString(cypherData);
    }

    private static byte[] base64Decode(String base64) throws IOException {
        return Base64.getDecoder().decode(base64);
    }

    private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 3des 加密
     *
     * @param plainBytes 原文
     * @param seed       用于生成key和iv
     * @return
     */
    private static byte[] encrypt3DES(byte[] plainBytes, byte[] seed) {
        try {
            byte[] des3KeyBytes;
            byte[] des3IVBytes;
            byte[] keyBytes = getMd5Value(seed, 20);
            byte[] ivBytes = getMd5Value(seed, 10);
            des3KeyBytes = copyOfArray(keyBytes, 24);
            des3IVBytes = copyOfArray(ivBytes, 8);

            byte[] salt = new byte[8];
            random.nextBytes(salt);

            byte[] data = new byte[salt.length + plainBytes.length];
            System.arraycopy(salt, 0, data, 0, salt.length);
            System.arraycopy(plainBytes, 0, data, salt.length, plainBytes.length);

            DESedeKeySpec spec = new DESedeKeySpec(des3KeyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
            Key desKey = keyFactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(des3IVBytes);
            cipher.init(Cipher.ENCRYPT_MODE, desKey, ips);
            byte[] bOut = cipher.doFinal(data);
            return bOut;
        } catch (Exception e) {
        }
        return new byte[0];
    }

    private static byte[] copyOfArray(byte[] original, int newLength) {
        byte[] copy = new byte[newLength];
        System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
        return copy;
    }

    public static String convertBytesToString(byte[] bytes) {
        StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            int tmp = b & 0xff;
            builder.append(convertToChar(tmp >> 4));
            builder.append(convertToChar(tmp & 0xf));
        }
        return builder.toString();
    }

    private static char convertToChar(int number) {
        if (number <= 9) {
            return (char) (number + '0');
        }
        return (char) (number + 'A' - 10);
    }

    private static byte[] getMd5Value(byte[] input, int round) {
        try {
            byte[] last = null;
            byte[] current = input;
            for (int i = 0; i < round; i++) {
                MessageDigest md5Provider = MessageDigest.getInstance(KEY_MD5);
                last = current;
                md5Provider.update(last);
                current = md5Provider.digest();
            }
            if (last == null) return current;
            byte[] result = new byte[current.length + last.length];
            System.arraycopy(last, 0, result, 0, last.length);
            System.arraycopy(current, 0, result, last.length, current.length);
            return result;
        } catch (NoSuchAlgorithmException e) {
        }
        return new byte[0];
    }
}
