package com.jd.pop.base.gof.factory.normal;

import org.springframework.beans.factory.FactoryBean;

public class StuFactoryBean implements FactoryBean<Stu> {
    private String info;

    public StuFactoryBean() {
    }

    @Override
    public Stu getObject() throws Exception {
        return new Stu(info.split("_")[0], info.split("_")[1]);
    }

    @Override
    public Class<?> getObjectType() {
        return Stu.class;
    }

    /**
     * 是否是单例
     *
     * @return true:是  false:否
     */
    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}