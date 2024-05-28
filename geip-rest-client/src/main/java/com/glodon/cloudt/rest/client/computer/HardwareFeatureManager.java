package com.glodon.cloudt.rest.client.computer;

import com.glodon.cloudt.rest.client.computer.impl.LinuxHardwareFeatureCollector;
import com.glodon.cloudt.rest.client.computer.impl.WindowHardwareFeatureCollector;
import com.glodon.cloudt.rest.client.utils.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 计算机硬件ID收集
 * <p/>
 * Created by chenxy on 2016/3/15.
 */
public class HardwareFeatureManager {
    private static final HardwareFeatureCollector FEATURE_COLLECTOR;
    private static final String KEY_MD5 = "MD5";

    static {
        String os = System.getProperty("os.name");
        if (os.startsWith("Window")) {
            FEATURE_COLLECTOR = new LinuxHardwareFeatureCollector();
        } else {
            FEATURE_COLLECTOR = new WindowHardwareFeatureCollector();
        }
    }

    /**
     * 计算计算机标识
     *
     * @return 计算机标识
     */
    public static String computeComputerId() {
        StringBuilder idBuilder = new StringBuilder();
        idBuilder.append(FEATURE_COLLECTOR.getCpuID()).append("$")
                .append(FEATURE_COLLECTOR.getDiskID()).append("$")
                .append(FEATURE_COLLECTOR.getMotherboardID()).append("$");
        try {
            MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
            md5.update(StringUtils.getBytes(idBuilder.toString()));
            byte[] data = md5.digest();
            return convertBytesToString(data);
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(idBuilder.toString().hashCode());
        }
    }

    /**
     * 获取计算机描述信息
     *
     * @return 计算机描述信息
     */
    public static String getComputerDescription() {
        return FEATURE_COLLECTOR.getComputerDescription();
    }

    /**
     * 将字节数组转为字符串
     *
     * @param bytes 字节数组
     * @return 字符串结果
     */
    private static String convertBytesToString(byte[] bytes) {
        StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            int tmp = b & 0xff;
            builder.append(convertToChar(tmp >> 4));
            builder.append(convertToChar(tmp & 0xf));
        }
        return builder.toString();
    }

    /**
     * 将整数转为字符
     *
     * @param number 整数
     * @return 字符结果
     */
    private static char convertToChar(int number) {
        if (number <= 9) {
            return (char) (number + '0');
        }
        return (char) (number + 'A' - 10);
    }
}
