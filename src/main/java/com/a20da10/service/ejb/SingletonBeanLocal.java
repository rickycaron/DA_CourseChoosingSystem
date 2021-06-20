package com.a20da10.service.ejb;

import javax.ejb.Local;
import java.util.Map;

@Local
public interface SingletonBeanLocal {
    void addToList(String email);
    void removeFromList(String email);
    Map<String, Integer> getFromList();
}
