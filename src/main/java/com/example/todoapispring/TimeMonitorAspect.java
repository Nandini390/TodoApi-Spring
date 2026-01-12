package com.example.todoapispring;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitorAspect {

    //We want that this method of this class should execute wherever we attach @TimeMonitor annotation
    //but this class don't know about it so, we add annotation
    @Around("@annotation(TimeMonitor)")
    public void logTime(){
        System.out.println("Logging time...");
    }
}
