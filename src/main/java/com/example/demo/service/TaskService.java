package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.TaskResponseDTO;
import com.example.demo.dto.TaskUpdateDTO;
import com.example.demo.exception.OperationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<TaskResponseDTO> listar() {
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String email = auth.getName();

      User user = userRepository.findByEmail(email)
          .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

      List<Task> tasks = taskRepository.findByUser(user);

      return tasks.stream()
          .map(taskMapper::toResponseDto)
          .collect(Collectors.toList());

    } catch (ResourceNotFoundException e) {
      throw e;
    } catch (DataAccessException e) {
      log.error("Erro ao acessar o banco de dados para listar tarefas", e);
      throw new OperationException("Erro ao procurar tarefas! Entre em contato com o desenvolvedor ou o suporte de TI.",
          e);
    } catch (Exception e) {
      log.error("Erro Inesperado", e);
      throw new OperationException("Erro ao procurar tarefas! Entre em contato com o desenvolvedor ou o suporte de TI.",
          e);
    }
  }

  @Transactional(readOnly = true)
  public TaskResponseDTO findById(Long id) {
    try {
      Task task = taskRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com esse ID " + id));
      return taskMapper.toResponseDto(task);
    } catch (ResourceNotFoundException e) {
      throw e;
    } catch (DataAccessException e) {
      log.error("Erro ao acessar o banco de dados para listar tarefas", e);
      throw new OperationException("Erro ao procurar tarefas! Entre em contato com o desenvolvedor ou o suporte de TI.",
          e);
    } catch (Exception e) {
      log.error("Erro Inesperado", e);
      throw new OperationException("Erro ao procurar tarefas! Entre em contato com o desenvolvedor ou o suporte de TI.",
          e);
    }

  }

  @Transactional
  public TaskResponseDTO create(TaskDTO dto) {
    try {

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String email = authentication.getName();

      User user = userRepository.findByEmail(email)
          .orElseThrow(() -> new RuntimeException("Usuário não localizado"));

      Task task = taskMapper.toEntity(dto);
      task.setDateCreation(LocalDate.now());
      task.setCompleted(false);
      task.setUser(user);
      Task saved = taskRepository.save(task);
      return taskMapper.toResponseDto(saved);
    } catch (ResourceNotFoundException e) {
      throw e;
    } catch (DataAccessException e) {
      log.error("Erro ao acessar o banco de dados para listar tarefas", e);
      throw new OperationException("Erro ao procurar tarefas! Entre em contato com o desenvolvedor ou o suporte de TI.",
          e);
    } catch (Exception e) {
      log.error("Unexpected error", e);
      throw new OperationException("Erro ao procurar tarefas! Entre em contato com o desenvolvedor ou o suporte de TI.",
          e);
    }
  }

  @Transactional
  public TaskResponseDTO update(Long id, TaskUpdateDTO dto) {
    try {
      Task task = taskRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com esse ID : " + id));
      taskMapper.updateEntityFromDto(dto, task);
      Task updated = taskRepository.save(task);
      return taskMapper.toResponseDto(updated);
    } catch (ResourceNotFoundException e) {
      throw e;
    } catch (DataAccessException e) {
      log.error("Erro ao acessar o banco de dados para listar tarefas", e);
      throw new OperationException("Erro ao procurar tarefas! Entre em contato com o desenvolvedor ou o suporte de TI.",
          e);
    } catch (Exception e) {
      log.error("Unexpected error", e);
      throw new OperationException("Erro ao procurar tarefas! Entre em contato com o desenvolvedor ou o suporte de TI.",
          e);
    }
  }

  @Transactional
  public void delete(Long id) {
    try {
      if (!taskRepository.existsById(id)) {
        throw new ResourceNotFoundException("Tarefa não encontrada com esse ID : " + id);
      }
      taskRepository.deleteById(id);
    } catch (ResourceNotFoundException e) {
      throw e;
    } catch (DataAccessException e) {
      log.error("Erro ao acessar o banco de dados para listar tarefas", e);
      throw new OperationException("Erro ao procurar tarefas! Entre em contato com o desenvolvedor ou o suporte de TI.",
          e);
    } catch (Exception e) {
      log.error("Unexpected error", e);
      throw new OperationException("Erro ao procurar tarefas! Entre em contato com o desenvolvedor ou o suporte de TI.",
          e);
    }
  }
}
