package com.glodon.cloudt.rest.client.computer;

/**
 * 计算机特征收集器
 * <p/>
 * Created by chenxy on 2016/3/15.
 */
public interface HardwareFeatureCollector {
    /**
     * 获取硬盘序列号
     *
     * @return 硬盘序列号
     */
    String getDiskID();

    /**
     * 获取CPU序列号
     *
     * @return CPU序列号
     */
    String getCpuID();

    /**
     * 获取主板序列号
     *
     * @return 主板序列号
     */
    String getMotherboardID();

    /**
     * 获取计算机描述信息
     *
     * @return 计算机描述信息
     */
    String getComputerDescription();
}
