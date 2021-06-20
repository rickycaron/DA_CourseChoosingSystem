package com.a20da10.service.ejb;

import javax.ejb.Local;
import javax.ejb.Timer;

@Local
public interface MyTimerServiceLocal {
    void setTimer(long interval);
    void programmaticTimeout(Timer timer);
}
