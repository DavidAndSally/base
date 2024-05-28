package com.glodon.cloudt.rest.client.data;

import com.glodon.cloudt.rest.client.utils.StringUtils;

import java.io.File;
import java.io.InputStream;

/**
 * Created by chenxy on 2016/3/14.
 */
public class HmacRestAuthInfo extends RestAuthInfo {

    private static final long serialVersionUID = -8395226117427402047L;
    private transient InputStream licStream;
    private String licPath;

    public InputStream getLicStream() {
        return licStream;
    }

    public void setLicStream(InputStream licStream) {
        this.licStream = licStream;
    }

    public String getLicPath() {
        return licPath;
    }

    public void setLicPath(String licPath) {
        this.licPath = licPath;
    }

    public boolean validate() {
        if (null != this.licStream) {
            return true;
        }
        if (StringUtils.isEmpty(this.licPath)) {
            return false;
        }
        File file = new File(this.licPath);
        return file.exists();
    }
}
