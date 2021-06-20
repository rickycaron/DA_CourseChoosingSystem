package com.a20da10.service.ejb;

import javax.ejb.Remote;
import java.util.Map;

@Remote
public interface SingletonBeanRemote {
    void addToList(String email);
    void removeFromList(String email);
    Map<String, Integer> getFromList();
}
