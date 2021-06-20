package com.a20da10.service.ejb;

import javax.ejb.Remote;
import javax.ejb.Timer;

@Remote
public interface MyTimerServiceRemote {
    void setTimer(long interval);
    void programmaticTimeout(Timer timer);
}
