package org.example.design_pattern.demo1;

public class client {
    public static void main(String[] args) {
        Boss boss=new Boss();
        Employ e=new Employ("yy");
        e.addObserver(boss);
        e.setEvent();
    }
}
