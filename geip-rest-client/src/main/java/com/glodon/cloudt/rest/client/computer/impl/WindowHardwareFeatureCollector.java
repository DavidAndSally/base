package com.glodon.cloudt.rest.client.computer.impl;

import com.glodon.cloudt.rest.client.computer.HardwareFeatureCollectorSupport;

import java.io.IOException;
import java.util.Scanner;

/**
 * windows操作系统特性收集器
 * <p/>
 * Created by chenxy on 2016/3/15.
 */
public class WindowHardwareFeatureCollector extends HardwareFeatureCollectorSupport {

    /**
     * 获取硬盘ID
     *
     * @return 硬盘ID
     */
    @Override
    public String getDiskID() {
        return getHardwareId("diskdrive", "serialnumber");
    }

    /**
     * 获取Cpu序列号
     *
     * @return Cpu序列号
     */
    @Override
    public String getCpuID() {
        return getHardwareId("cpu", "ProcessorId");
    }

    /**
     * 获取主板序列号
     *
     * @return 主板序列号
     */
    @Override
    public String getMotherboardID() {
        return getHardwareId("bios", "SerialNumber");
    }

    /**
     * 获取硬件ID
     *
     * @param hardwareName 硬件名称
     * @param propertyName 属性名称
     * @return 硬件ID
     */
    private String getHardwareId(String hardwareName, String propertyName) {
        try {
            String hardwareId = "";
            Process process = Runtime.getRuntime().exec(new String[]{"wmic", hardwareName, "get", propertyName});
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream(), DEFAULT_CHARSET_NAME);
            hardwareId = sc.next();
            if (sc.hasNext()) {
                hardwareId = sc.next();
            }
            return hardwareId;
        } catch (IOException e) {
            return "UNKNOWN";
        }
    }
}
