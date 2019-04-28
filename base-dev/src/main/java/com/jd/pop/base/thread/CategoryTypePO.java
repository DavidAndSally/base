package com.jd.pop.base.thread;

import java.io.Serializable;

/**
 * 三级类目 PO
 * <p>
 *
 * @author sunboyu
 * @date 2017/11/16
 */
public class CategoryTypePO implements Serializable {

    private static final long serialVersionUID = 5797405242549187044L;

    /**
     * 一级类目 code
     */
    private Long cate1stCode;

    /**
     * 一级类目 名称
     */
    private String cate1stName;

    /**
     * 二级类目 code
     */
    private Long cate2ndCode;

    /**
     * 二级类目 名称
     */
    private String cate2ndName;

    /**
     * 三级类目 code
     */
    private Long cate3rdCode;

    /**
     * 三级类目 名称
     */
    private String cate3rdName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCate1stCode() {
        return cate1stCode;
    }

    public void setCate1stCode(Long cate1stCode) {
        this.cate1stCode = cate1stCode;
    }

    public String getCate1stName() {
        return cate1stName;
    }

    public void setCate1stName(String cate1stName) {
        this.cate1stName = cate1stName;
    }

    public Long getCate2ndCode() {
        return cate2ndCode;
    }

    public void setCate2ndCode(Long cate2ndCode) {
        this.cate2ndCode = cate2ndCode;
    }

    public String getCate2ndName() {
        return cate2ndName;
    }

    public void setCate2ndName(String cate2ndName) {
        this.cate2ndName = cate2ndName;
    }

    public Long getCate3rdCode() {
        return cate3rdCode;
    }

    public void setCate3rdCode(Long cate3rdCode) {
        this.cate3rdCode = cate3rdCode;
    }

    public String getCate3rdName() {
        return cate3rdName;
    }

    public void setCate3rdName(String cate3rdName) {
        this.cate3rdName = cate3rdName;
    }
}