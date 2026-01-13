package com.example.todoapispring;

import org.springframework.stereotype.Service;

@Service("FakeToDoService")
public class FakeToDoService implements ToDoService{

    @TimeMonitor
    public String doSomething(){
        for(int i=0;i<1000000000;i++);
        return "something";
    }
}
