package com.a20da10.service.ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import java.util.logging.Logger;

@Stateless(name = "MyTimerServiceImpl")
public class MyTimerServiceImpl implements  MyTimerServiceLocal, MyTimerServiceRemote{
    @Resource
    TimerService timerService;
    private static final Logger logger = Logger.getLogger("ejb.MyTimerService");
    public MyTimerServiceImpl(){}
    public void setTimer(long interval){
        timerService.createTimer(interval,"Setting a programmatic timer");
    }

    @Timeout
    public void programmaticTimeout(Timer timer){ //mouse click event
        logger.info("Your session will be expired in 10 minutes ! ");
    }
}
