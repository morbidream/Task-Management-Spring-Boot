package com.example.taskmanagement.repository;

import com.example.taskmanagement.DTO.CountType;
import com.example.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {

    @Query(value = "select * from task order by title desc", nativeQuery = true)
    public List<Task> getAllTasksByTitle();

    //select count(*), type from task group by type
    //select (count(*)/(select count(*) from task)*100), type from task group by type

    @Query(value = "select new com.example.taskmanagement.DTO.CountType( count(*) / (select count(*) from Task) * 100, type ) from Task GROUP BY type")
    public List<CountType> getPercentageGroupByType();
}
