package com.example.todoapispring;

import org.aspectj.lang.ProceedingJoinPoint;
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
    public void logTime(ProceedingJoinPoint JoinPoint){
        long start=System.currentTimeMillis(); //start time of the code
        try{
           JoinPoint.proceed();
        }catch (Throwable e) {
            System.out.println("Something Went Wrong during the execution");
        } finally{
            long end=System.currentTimeMillis(); //end time of the code
            long totalTime= end-start;
            System.out.println("Total execution time of the method is: "+totalTime + " ms.");
        }


    }
}
