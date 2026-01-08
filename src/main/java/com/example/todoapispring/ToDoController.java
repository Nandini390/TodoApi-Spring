package com.example.todoapispring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ToDoController {
    private static List<ToDo> toDoList;

    public ToDoController(){
        toDoList=new ArrayList<>();
        toDoList.add(new ToDo(1,false,"Todo 1", 1));
        toDoList.add(new ToDo(2,true,"Todo 2", 2));
    }

    @GetMapping("/todos")
    public List<ToDo> getTodos(){
        return toDoList;
    }

    @PostMapping("/todos")
    public ResponseEntity<ToDo> createTodo(@RequestBody ToDo newTodo){
        toDoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

}


//To make this code working:
//In Postman, create a collection in which make a Get request (Get todos), enter the url localhost:8080/todos, select Get method and run
// make another Post Request ( create todos), enter the url and select method as Post.
//The data is not persistent, as no database is connected so add the todos we will add will gone once we refresh the server.