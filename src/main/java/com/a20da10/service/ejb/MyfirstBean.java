package com.a20da10.service.ejb;


import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;


@Stateless(name = "MyfirstBean")
//@Remote(com.a20da10.service.ejb.MyFirstBeanRemote.class)
//@Local(com.a20da10.service.ejb.MyFirstBeanLocal.class)
public class MyfirstBean  {
    public MyfirstBean(){}
    //@Override
    public int doOperation(int a, int b) {
        return 2*a*b;
    }
}
