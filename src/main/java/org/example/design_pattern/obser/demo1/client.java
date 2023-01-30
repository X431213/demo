package org.example.design_pattern.obser.demo1;

public class client {
    public static void main(String[] args) {
        Boss boss=new Boss();
        Employ e=new Employ("ssb");
        e.addObserver(boss);
        e.setEvent();
    }
}
