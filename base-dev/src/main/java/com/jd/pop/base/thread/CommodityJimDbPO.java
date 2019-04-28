package com.jd.pop.base.thread;

import java.io.Serializable;

/**
 * 商品 jimdb po
 *
 * @author sunboyu
 * @date 2017/12/5
 */
public class CommodityJimDbPO implements Comparable<CommodityJimDbPO>, Serializable {

    private static final long serialVersionUID = -8657654883705132634L;
    /**
     * 店铺id
     */
    private Long shopId;
    /**
     * 商家id
     */
    private Long venderId;
    /**
     * 最小售卖单元
     */
    private Long skuId;
    /**
     * 商品ID
     */
    private Long wareId;
    /**
     * 商品名称
     */
    private String wareName;
    /**
     * 商品链接
     */
    private String wareUrl;
    /**
     * 商品所属关键词
     */
    private String keyName;
    /**
     * 一级类目 code
     */
    private Long cate1stCode;
    /**
     * 二级类目 code
     */
    private Long cate2ndCode;
    /**
     * 三级类目 code
     */
    private Long cate3rdCode;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getVenderId() {
        return venderId;
    }

    public void setVenderId(Long venderId) {
        this.venderId = venderId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public String getWareUrl() {
        return wareUrl;
    }

    public void setWareUrl(String wareUrl) {
        this.wareUrl = wareUrl;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public Long getCate1stCode() {
        return cate1stCode;
    }

    public void setCate1stCode(Long cate1stCode) {
        this.cate1stCode = cate1stCode;
    }

    public Long getCate2ndCode() {
        return cate2ndCode;
    }

    public void setCate2ndCode(Long cate2ndCode) {
        this.cate2ndCode = cate2ndCode;
    }

    public Long getCate3rdCode() {
        return cate3rdCode;
    }

    public void setCate3rdCode(Long cate3rdCode) {
        this.cate3rdCode = cate3rdCode;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && (this == o || getClass() == o.getClass());
    }

    @Override
    public int hashCode() {
        return wareId.intValue();
    }

    @Override
    public int compareTo(CommodityJimDbPO o) {
        return this.getWareId().compareTo(o.getWareId());
    }
}
