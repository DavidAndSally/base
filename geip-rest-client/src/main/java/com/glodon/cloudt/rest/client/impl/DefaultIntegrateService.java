package com.glodon.cloudt.rest.client.impl;

import com.glodon.cloudt.rest.client.IntegrateService;
import com.glodon.cloudt.rest.client.data.LicenseInfo;
import com.glodon.cloudt.rest.client.exception.InvalidLicFileException;
import com.glodon.cloudt.rest.client.exception.UnableSsoException;
import com.glodon.cloudt.rest.client.utils.CredenceUtils;
import com.glodon.cloudt.rest.client.utils.CryptoUtils;
import com.glodon.cloudt.rest.client.utils.LicenseUtils;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by chenxy on 2017/5/4.
 */
public class DefaultIntegrateService implements IntegrateService {
    private LicenseInfo licenseInfo;

    @Override
    public void loadLicense(String licPath) throws InvalidLicFileException {
        licenseInfo = LicenseUtils.load(licPath);
    }

    @Override
    public String createSsoUrl(String service, String userId, String productCode) throws UnableSsoException {
        if (null == licenseInfo) {
            return null;
        }
        try {
            String credence = CredenceUtils.create(licenseInfo);
            String data1 = licenseInfo.getAccessId() + "|" + credence;
            data1 = CryptoUtils.rsaEncode(data1);
            data1 = CryptoUtils.convertBytesToString(data1.getBytes("UTF-8"));

            String data = userId + "|" + System.currentTimeMillis();
            data = CryptoUtils.encrypt(data, licenseInfo.getSecret());

            String url = String.format("%s/identity/integrate/sso?data=%s&data1=%s&service=%s&productCode=%s",
                    licenseInfo.getRootAddress(), data, data1, URLEncoder.encode(service, "UTF-8"), productCode);
            return url;
        } catch (IOException e) {
            throw new UnableSsoException("创建单点url失败", e);
        }
    }
}
