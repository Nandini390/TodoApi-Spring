package com.example.todoapispring;

import org.springframework.stereotype.Service;

@Service("FakeToDoService")
public class FakeToDoService implements ToDoService{

    @TimeMonitor
    public String doSomething(){

        return "something";
    }
}
