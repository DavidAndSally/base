package com.jd.pop.base.annotation.practice1;

@Description("i am class annotation")
public class Child implements people {
    @Override
    @Description("i am method annotation")
    public String name() {
        return null;
    }

    @Override
    public int age() {
        return 0;
    }

    @Override
    public void work() {

    }
}