package com.example.taskmanagement.controller;

import com.example.taskmanagement.DTO.CountType;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class TaskController {

    private TaskService taskService;

    //Display
    @GetMapping("/tasks")
    public List<Task> getTasks()
    {
        return taskService.getTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable Long id)
    {
        return taskService.getTaskById(id);
    }

    //Create
    @PostMapping("/newtask")
    public Task addTask(@RequestBody Task task)
    {
        return  taskService.save(task);
    }

    //update
    @PutMapping("editask/{id}")
    public ResponseEntity<?> editTask(@RequestBody Task task, @PathVariable Long id)
    {
        if(taskService.existsById(id))
        {
            Task task1 = taskService.getTaskById(id);
            task1.setTitle((task.getTitle()));
            task1.setDescription(task.getDescription());
            task1.setType(task.getType());
            task1.setDueDate(task.getDueDate());
            taskService.save(task1);
            return  ResponseEntity.ok().body(task1);
        }
        else
        {
            HashMap<String, String> message = new HashMap<>();
            message.put("message ", id + " task not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }

    }

    //remove
    @DeleteMapping("deltask/{id}")
    public ResponseEntity<?> delTask(@PathVariable Long id)
    {
        if(taskService.existsById(id))
        {
            taskService.deleteTask(id);
            HashMap<String, String> message = new HashMap<>();
            message.put("message ", "Task with id " + id + " deleted successfully.");
            return ResponseEntity.status(HttpStatus.OK).body(message);

        }
        else
        {
            HashMap<String, String> message = new HashMap<>();
            message.put("message ", id + " task not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }
    }


    @GetMapping("/per")
    List<CountType> getPercentageGroupByType()
    {
        return taskService.getPercentageGroupByType();
    }
}
