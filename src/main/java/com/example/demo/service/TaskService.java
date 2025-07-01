package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.TaskResponseDTO;
import com.example.demo.dto.TaskUpdateDTO;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

 private final TaskRepository taskRepository;
 private final TaskMapper taskMapper;

 public List<TaskResponseDTO> listar() {
  return taskRepository.findAll()
    .stream()
    .map(taskMapper::toResponseDto)
    .collect(Collectors.toList());
 }

 public TaskResponseDTO findById(Long id) {
  Task task = taskRepository.findById(id)
    .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + id));
  return taskMapper.toResponseDto(task);
 }

 public TaskResponseDTO criar(TaskDTO dto) {
  Task task = taskMapper.toEntity(dto);
  task.setDateCreation(LocalDate.now());
  task.setCompleted(false);
  Task saved = taskRepository.save(task);
  return taskMapper.toResponseDto(saved);
 }

 public TaskResponseDTO update(Long id, TaskUpdateDTO dto) {
  Task task = taskRepository.findById(id)
    .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + id));
  taskMapper.updateEntityFromDto(dto, task);
  Task updated = taskRepository.save(task);
  return taskMapper.toResponseDto(updated);
 }

 public void deletar(Long id) {
  if (!taskRepository.existsById(id)) {
   throw new EntityNotFoundException("Task not found with ID: " + id);
  }
  taskRepository.deleteById(id);
 }
}
