package com.glodon.cloudt.rest.client.data;

import org.apache.http.cookie.Cookie;

import com.glodon.cloudt.rest.client.exception.AuthenticateException;

/**
 * cookie 方式认证信息
 * <p/>
 * Created by chenxy on 2016/3/14.
 */
public class CookieRestAuthInfo extends RestAuthInfo {

    private static final long serialVersionUID = 1694539837374471773L;
    /**
     * 认证cookie
     */
    private transient Cookie[] cookies;
    /**
     * 认证服务根地址
     */
    private String restSiteDomain;

    public Cookie[] getCookies() {
        return cookies;
    }

    public void setCookies(Cookie[] cookies) {
        this.cookies = cookies;
    }

    public String getRestSiteDomain() {
        return restSiteDomain;
    }

    public void setRestSiteDomain(String restSiteDomain) {
        this.restSiteDomain = restSiteDomain;
    }

    public void validate()
            throws AuthenticateException {
        if (null == cookies ||
                cookies.length == 0) {
            throw new AuthenticateException("认证参数非法，未找到可用的认证COOKIE。");
        }
    }
}
