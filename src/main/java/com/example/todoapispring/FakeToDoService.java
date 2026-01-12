package com.example.todoapispring;

import org.springframework.stereotype.Service;

@Service
public class FakeToDoService implements ToDoService{
    public String doSomething(){

        return "something";
    }
}
