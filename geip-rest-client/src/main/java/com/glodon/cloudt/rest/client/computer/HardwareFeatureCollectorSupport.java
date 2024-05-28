package com.glodon.cloudt.rest.client.computer;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 硬件信息收集器基类
 * <p/>
 * Created by chenxy on 2016/3/15.
 */
public abstract class HardwareFeatureCollectorSupport implements HardwareFeatureCollector {
    protected static final String DEFAULT_CHARSET_NAME = "UTF-8";

    /**
     * 获取计算机描述信息
     *
     * @return 计算机描述信息
     */
    @Override
    public String getComputerDescription() {
        StringBuilder nameBuilder = new StringBuilder();
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String hostName = inetAddress.getHostName();
            nameBuilder.append("[" + hostName + "]");
            String ip = inetAddress.getHostAddress();
            nameBuilder.append("[" + ip + "]");
            String userName = System.getProperty("user.name");
            nameBuilder.append("-[" + userName + "]");
            String os = System.getProperty("os.name");
            nameBuilder.append("-[" + os + "]");
        } catch (UnknownHostException e) {
        }
        return nameBuilder.toString();
    }
}
