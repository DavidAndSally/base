package com.jd.pop.base.gof.factory.abstrac;

import com.jd.pop.base.gof.factory.simple.Client;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        try {
            Product1 p1;
            Product2 p2;
            AbstractFactory af;
            af = new ConcreteFactory1();
            //从配置文件中读取
//            af = (AbstractFactory) ReadXML1.getObject();
            p1 = af.newProduct1();
            p2 = af.newProduct2();
            p1.show();
            p2.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

//抽象产品：提供了产品的接口
interface Product1 {
    public void show();
}

//抽象产品：提供了产品的接口
interface Product2 {
    public void show();
}


//具体产品11：实现抽象产品中的抽象方法
class ConcreteProduct11 implements Product1 {
    public void show() {
        System.out.println("具体产品11显示...");
    }
}

//具体产品12：实现抽象产品中的抽象方法
class ConcreteProduct12 implements Product1 {
    public void show() {
        System.out.println("具体产品12显示...");
    }
}

//具体产品21：实现抽象产品中的抽象方法
class ConcreteProduct21 implements Product2 {
    public void show() {
        System.out.println("具体产品21显示...");
    }
}

//具体产品22：实现抽象产品中的抽象方法
class ConcreteProduct22 implements Product2 {
    public void show() {
        System.out.println("具体产品22显示...");
    }
}

//抽象工厂：提供了厂品的生成方法
interface AbstractFactory {
    public Product1 newProduct1();

    public Product2 newProduct2();
}

//具体工厂1：实现了厂品的生成方法
class ConcreteFactory1 implements AbstractFactory {
    @Override
    public Product1 newProduct1() {
        System.out.println("具体工厂1生成-->具体产品11...");
        return new ConcreteProduct11();
    }

    @Override
    public Product2 newProduct2() {
        System.out.println("具体工厂1 生成-->具体产品21...");
        return new ConcreteProduct21();
    }
}

//具体工厂2：实现了厂品的生成方法
class ConcreteFactory2 implements AbstractFactory {
    @Override
    public Product1 newProduct1() {
        System.out.println("具体工厂2生成-->具体产品12...");
        return new ConcreteProduct12();
    }

    @Override
    public Product2 newProduct2() {
        System.out.println("具体工厂2生成-->具体产品22...");
        return new ConcreteProduct22();
    }
}