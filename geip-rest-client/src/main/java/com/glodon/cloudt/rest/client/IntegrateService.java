package com.glodon.cloudt.rest.client;

import com.glodon.cloudt.rest.client.exception.InvalidLicFileException;
import com.glodon.cloudt.rest.client.exception.UnableSsoException;

/**
 * 三方与云端集成服务
 * <p>
 * Created by chenxy on 2017/5/4.
 */
public interface IntegrateService {
    /**
     * 加载授权文件
     *
     * @param licPath 授权文件路径
     * @throws InvalidLicFileException 非法的授权文件异常
     */
    void loadLicense(String licPath) throws InvalidLicFileException;

    /**
     * 创建单点登录url
     *
     * @param service     云端页面相对地址
     * @param userId      三方用户Id
     * @param productCode 云端产品编码
     * @return 单点登录url
     * @throws UnableSsoException 创建单点url失败异常
     */
    String createSsoUrl(String service, String userId, String productCode) throws UnableSsoException;
}
