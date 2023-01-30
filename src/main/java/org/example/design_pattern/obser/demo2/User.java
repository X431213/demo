package org.example.design_pattern.obser.demo2;




public class User {
    private String name;

    public void update(String str) {
        System.out.println(name+"update content:"+str);
    }
    public User(String str){
        this.name=str;
    }
    public String getName(){
        return this.name;
    }
}
