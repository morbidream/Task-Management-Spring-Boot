package com.example.taskmanagement.service;
import com.example.taskmanagement.DTO.CountType;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    public List<Task> getTasks()
    {
       // return taskRepository.findAll();
        return taskRepository.getAllTasksByTitle();
    }

    public Task getTaskById(Long id)
    {
        return taskRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("The requested task is not found.")
        );
    }

    public Task save(Task task)
    {
        return taskRepository.saveAndFlush(task);
    }

    public boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);

    }
    public List<CountType> getPercentageGroupByType()
    {
        return taskRepository.getPercentageGroupByType();
    }
}
