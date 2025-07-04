package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Task;
import com.example.demo.model.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

 List<Task> findByUser(User user);


}
