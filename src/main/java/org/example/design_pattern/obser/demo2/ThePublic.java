package org.example.design_pattern.obser.demo2;


import java.lang.management.OperatingSystemMXBean;
import java.util.ArrayList;
import java.util.List;

public class ThePublic {
    private List<User> observer=new ArrayList<>();
    private String content;
    public void addObserver(User user){
        for (User us: observer) {
            if(us.getName().equals(user.getName())){
                break;
            }
        }
        observer.add(user);
    }
    public void deleteObserver(User user){
        for (User us: observer) {
            if(us.getName().equals(user.getName())){
                break;
            }
        }
        observer.remove(user);
    }
    public void updateContent(String str){
        this.content=str;
        notifyChange(content);
    }
    public void notifyChange(String content){
        for (User user:observer) {
            user.update(content);
        }
    }
}
