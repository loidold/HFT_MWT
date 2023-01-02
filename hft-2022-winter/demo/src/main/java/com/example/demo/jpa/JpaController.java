package com.example.demo.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/todos/")
public class JpaController {

    @Autowired
    TodoItemRepository todoItemRepository;

    @ApiResponses(value = 
    {
        @ApiResponse(responseCode = "201", description = "Item has been created" , content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{name}")
    public TodoItem createAndAddTodoItem(@PathVariable String name){

        TodoItem item = new TodoItem(name);
        todoItemRepository.save(item);

        return item;
    }
    
    
	
	  //POST + 2 variable (name, priority)
	  //-----------------------------------------------------------------------------
	   // Add new item to list
	  
	  @Operation(summary =
	  "Creates a Todo Item with path variable name and priority ")
	  
	  @ApiResponses(value = {
	  
	  @ApiResponse(responseCode = "201", description = "Item has been created" ,
	  content = @Content) })
	  
	  @ResponseStatus(HttpStatus.CREATED)
	  
	  @PostMapping("/{name}/{prio}") public TodoItem
	  createAndAddTTwoItems(@PathVariable String name, @PathVariable String prio){
	  
	  int priority = Integer.valueOf(prio);
	  
	  TodoItem item = new TodoItem(name, priority); 
	  todoItemRepository.save(item);
	  
	  return item; 
	  }
	 
    

    // TODO implement other CRUD methods and make API compliant to v1

    @GetMapping("/count")
    public long getAmountOfItems(){

        //todoItemRepository.
        return todoItemRepository.count();

    }

    @GetMapping("/id/{id}")
    public Optional<TodoItem> findById(@PathVariable String id){

        return todoItemRepository.findById(id);

    }

    // List all elements in ArrayList
    @Operation(summary = "Returns a list of ToDo items")
    @GetMapping(value = "/", produces = "application/json")
    @ApiResponses(value = 
                    {
                        @ApiResponse(responseCode = "200", description = "List all items" , content = @Content)
                    })
    @ResponseStatus(HttpStatus.OK)
    public List<TodoItem> getTodoItems(){

        return todoItemRepository.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(produces = "application/json", path = "/{itemId}")
    TodoItem deleteTodoItem(@PathVariable String itemId){

        todoItemRepository.deleteById(itemId);

        return null;
    }


    
}
