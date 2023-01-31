package org.example.design_pattern.obser.demo2;


public class client {
    public static void main(String[] args) {
        User user = new User("l");
        User user1 = new User("x");
        ThePublic thePublic = new ThePublic();
        thePublic.addObserver(user);
        thePublic.addObserver(user1);
        thePublic.updateContent("bs");
        thePublic.deleteObserver(user);
        thePublic.updateContent("bm");
    }
}
