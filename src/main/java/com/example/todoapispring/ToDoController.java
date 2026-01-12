package com.example.todoapispring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class ToDoController {
    private static List<ToDo> toDoList;

    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService){
        this.toDoService=toDoService;
        toDoList=new ArrayList<>();
        toDoList.add(new ToDo(1,false,"Todo 1", 1));
        toDoList.add(new ToDo(2,true,"Todo 2", 2));
    }

    @GetMapping
    public  ResponseEntity<List<ToDo>> getTodos(){

        return ResponseEntity.status(HttpStatus.OK).body(toDoList);
    }

    @GetMapping("/status")
    //required=false, makes it optional i.e. it is not mandatory to send a query param
    //byDefault the value is false, hence when we set defaultValue="true" , it makes it true.
    public ResponseEntity<List<ToDo>> getTodos(@RequestParam(required = false, defaultValue = "true") boolean isCompleted){
        System.out.println("Query Param is: "+ isCompleted + " ...." + toDoService.doSomething());
        return ResponseEntity.ok(toDoList);
    }

    @PostMapping
    public ResponseEntity<ToDo> createTodo(@RequestBody ToDo newTodo){
        toDoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodobyId(@PathVariable long todoId){
        for(ToDo todo:toDoList){
            if(todo.getId()==todoId){
                return ResponseEntity.ok(todo);
            }
        }
        //when we return notFound(), it is returning builder object, hence we have to add .build()
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TODO_NOT_FOUND");
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable long todoId){
        for(ToDo todo: toDoList){
            if(todo.getUserId()==todoId){
                toDoList.remove(todo);
                return ResponseEntity.ok("TODO_DELETED_SUCCESSFULLY");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TODO_NOT_FOUND");
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable long todoId,@RequestBody java.util.Map<String, Object> updates){
        for (ToDo todo : toDoList) {

            if (todo.getId() == todoId) {

                if (updates.containsKey("completed")) {
                    todo.setCompleted((Boolean) updates.get("completed"));
                }
                if (updates.containsKey("title")) {
                    todo.setTitle((String) updates.get("title"));
                }
                if (updates.containsKey("userId")) {
                    todo.setUserId(((Number) updates.get("userId")).intValue());
                }

                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("TODO_NOT_FOUND");

    }



}


//To make this code working:
//In Postman, create a collection in which make a Get request (Get todos), enter the url localhost:8080/todos, select Get method and run
// make another Post Request ( create todos), enter the url and select method as Post.
//The data is not persistent, as no database is connected so add the todos we will add will gone once we refresh the server.