package com.glodon.cloudt.rest.client.computer.impl;

import com.glodon.cloudt.rest.client.computer.HardwareFeatureCollectorSupport;

import java.io.IOException;
import java.util.Scanner;

/**
 * Linux操作系统特性收集器
 * <p/>
 * Created by chenxy on 2016/3/15.
 */
public class LinuxHardwareFeatureCollector extends HardwareFeatureCollectorSupport {

    /**
     * 获取硬盘ID
     *
     * @return 硬盘ID
     */
    @Override
    public String getDiskID() {
        return getHardwareId(new String[]{"/bin/sh", "-c", "dmidecode -s system-serial-number"});
    }

    /**
     * 获取Cpu序列号
     *
     * @return Cpu序列号
     */
    @Override
    public String getCpuID() {
        return getHardwareId(new String[]{"/bin/sh", "-c", "dmidecode -t processor | grep -m1 'ID'"});
    }

    /**
     * 获取主板序列号
     *
     * @return 主板序列号
     */
    @Override
    public String getMotherboardID() {
        return getHardwareId(new String[]{"/bin/sh", "-c", "dmidecode | grep -m1 'Serial Number'"});
    }

    /**
     * 获取硬件ID
     *
     * @param cmdArray 命令参数
     * @return 硬件ID
     */
    private String getHardwareId(String[] cmdArray) {
        try {
            Process process = Runtime.getRuntime().exec(cmdArray);
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream(), "UTF-8");
            StringBuilder idBuilder = new StringBuilder();
            while (sc.hasNext()) {
                idBuilder.append(sc.next());
            }
            return idBuilder.toString();
        } catch (IOException e) {
            return "UNKNOWN";
        }
    }
}
