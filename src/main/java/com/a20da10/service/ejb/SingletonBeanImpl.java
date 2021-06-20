package com.a20da10.service.ejb;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton(name = "SingletonBeanImpl")
@Lock(LockType.WRITE)
public class SingletonBeanImpl implements SingletonBeanLocal, SingletonBeanRemote {
    private Map<String, Integer> userMap = new HashMap<>();

    @Lock(LockType.WRITE)
    public void addToList(String email) {
        userMap.put(email, userMap.getOrDefault(email,0) + 1);
    }
    @Lock(LockType.WRITE)
    public void removeFromList(String email) {
        if (userMap.getOrDefault(email,0) > 1)
            userMap.put(email, userMap.get(email) - 1);
        if (userMap.getOrDefault(email,0) == 1)
            userMap.remove(email);
    }
    @Lock(LockType.READ)
    public Map<String, Integer> getFromList() {
        return this.userMap;
    }
}