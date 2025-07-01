package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.TaskResponseDTO;
import com.example.demo.dto.TaskUpdateDTO;
import com.example.demo.service.TaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Api(value = "Task Controller", tags = "Tasks")
public class TaskController {

 private final TaskService service;

 @GetMapping
 @ApiOperation(value = "Find All Tasks", notes = "This endpoint return all tasks registers")
 public List<TaskResponseDTO> listar() {
   return service.listar();
 }

 @GetMapping("/{id}")
 public TaskResponseDTO findById(@PathVariable Long id) {
  return service.findById(id);
 }

 @PostMapping
 public TaskResponseDTO criar(@RequestBody @Valid TaskDTO dto) {
  return service.criar(dto);
 }

 @PutMapping("/{id}")
 public TaskResponseDTO update(@PathVariable Long id, @RequestBody @Valid TaskUpdateDTO dto) {
  return service.update(id, dto);
 }

 @DeleteMapping("/{id}")
 public void deletar(@PathVariable Long id) {
  service.deletar(id);
 }

}
