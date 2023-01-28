package org.example.design_pattern.demo1;


import java.util.Observable;
public class Employ extends Observable {
    private String name;
    public void setEvent(){
        setChanged();//标注要更新状态
        notifyObservers();//通知观察者
    }
    Employ(String str){
        this.name=str;
    }
    public String getName(){
        return this.name;
    }
}
