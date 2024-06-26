package com.jd.pop.base.gof.factory.abstrac;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class FarmTest {
    public static void main(String[] args) {
        try {
//            Farm f;
            Animal a;
            Plant p;
//            f = new SRfarm();
//            f = (Farm) ReadXML.getObject();

//            a = f.newAnimal();
//            p = f.newPlant();
            a = OptimizeFarm.newAnimal();
            p = OptimizeFarm.newPlant();
            a.show();
            p.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

//抽象产品：动物类
interface Animal {
    public void show();
}

//具体产品：马类
class Horse implements Animal {
    JScrollPane sp;
    JFrame jf = new JFrame("抽象工厂模式测试");

    public Horse() {
        Container contentPane = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("动物：马"));
        sp = new JScrollPane(p1);
        contentPane.add(sp, BorderLayout.CENTER);
        JLabel l1 = new JLabel(new ImageIcon("base-dev/src/main/resources/A_Horse.jpg"));
        p1.add(l1);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//用户点击窗口关闭
    }

    public void show() {
        jf.setVisible(true);
    }
}

//具体产品：牛类
class Cattle implements Animal {
    JScrollPane sp;
    JFrame jf = new JFrame("抽象工厂模式测试");

    public Cattle() {
        Container contentPane = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("动物：牛"));
        sp = new JScrollPane(p1);
        contentPane.add(sp, BorderLayout.CENTER);
        JLabel l1 = new JLabel(new ImageIcon("base-dev/src/main/resources/A_Cattle.jpg"));
        p1.add(l1);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//用户点击窗口关闭
    }

    public void show() {
        jf.setVisible(true);
    }
}

//抽象产品：植物类
interface Plant {
    public void show();
}

//具体产品：水果类
class Fruitage implements Plant {
    JScrollPane sp;
    JFrame jf = new JFrame("抽象工厂模式测试");

    public Fruitage() {
        Container contentPane = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("植物：水果"));
        sp = new JScrollPane(p1);
        contentPane.add(sp, BorderLayout.CENTER);
        JLabel l1 = new JLabel(new ImageIcon("base-dev/src/main/resources/P_Fruitage.jpg"));
        p1.add(l1);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//用户点击窗口关闭
    }

    public void show() {
        jf.setVisible(true);
    }
}

//具体产品：蔬菜类
class Vegetables implements Plant {
    JScrollPane sp;
    JFrame jf = new JFrame("抽象工厂模式测试");

    public Vegetables() {
        Container contentPane = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("植物：蔬菜"));
        sp = new JScrollPane(p1);
        contentPane.add(sp, BorderLayout.CENTER);
        JLabel l1 = new JLabel(new ImageIcon("base-dev/src/main/resources/P_Vegetables.jpg"));
        p1.add(l1);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//用户点击窗口关闭
    }

    public void show() {
        jf.setVisible(true);
    }
}

//抽象工厂：农场类
interface Farm {
    public Animal newAnimal();

    public Plant newPlant();
}

//具体工厂：韶关农场类
class SGfarm implements Farm {
    public Animal newAnimal() {
        System.out.println("新牛出生！");
        return new Cattle();
    }

    public Plant newPlant() {
        System.out.println("蔬菜长成！");
        return new Vegetables();
    }
}

//具体工厂：上饶农场类
class SRfarm implements Farm {
    public Animal newAnimal() {
        System.out.println("新马出生！");
        return new Horse();
    }

    public Plant newPlant() {
        System.out.println("水果长成！");
        return new Fruitage();
    }
}

class OptimizeFarm {
//    private static final String type = "2";

    public static Animal newAnimal() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Animal animal = null;
//        switch (type) {
//            case "1":
//                animal = new Horse();
//                break;
//            case "2":
//                animal = new Cattle();
//                break;
//            default:
//        }
//        return animal;
        //反射替代简单工厂中的switch或if-start
        String cName = "com.jd.pop.base.gof.factory.abstrac." + ReadXML2.getObject(1);
        System.out.println("新类名：" + cName);
        Class<?> c = Class.forName(cName);
        return (Animal) c.newInstance();
//        反射替代简单工厂中的switch或if--end
    }

    public static Plant newPlant() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Plant plant = null;
//        switch (type) {
//            case "1":
//                plant = new Fruitage();
//                break;
//            case "2":
//                plant = new Vegetables();
//                break;
//            default:
//        }
//        return plant;
        String cName = "com.jd.pop.base.gof.factory.abstrac." + ReadXML2.getObject(2);
        System.out.println("新类名：" + cName);
        Class<?> c = Class.forName(cName);
        return (Plant) c.newInstance();
    }
}