package com.example.todoapispring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("AnotherToDoService")
public class AnotherToDoService implements ToDoService{
    @Override
    public String doSomething() {
        return "something from another ToDo service";
    }
}
