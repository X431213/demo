package org.example.design_pattern.demo1;

import java.util.*;

public class Boss implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Employ){
            Employ employ=(Employ) o;
            report(employ,arg);
        }
    }
    private void report(Employ employ, Object arg){
        System.out.println(employ.getName()+"被我抓到摸鱼！");
    }
}
