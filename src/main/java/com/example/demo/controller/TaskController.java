package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.TaskResponseDTO;
import com.example.demo.dto.TaskUpdateDTO;
import com.example.demo.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
@Tag(name = "Registro de tarefas", description = "Operações relacionadas ás tarefas")
public class TaskController {

  private final TaskService service;

  @GetMapping
  @Operation(summary = "Listar todas as tarefas", description = "Usuário autenticado envia seu token JWT e lista todas as suas tarefas cadastradas")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Tarefa listada com sucesso"),
      @ApiResponse(responseCode = "401", description = "Token inválido ou expirado"),
  })
  public List<TaskResponseDTO> listar() {
    return service.listar();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Buscar tarefa por ID", description = "Retorna os dados da tarefa correspondente ao ID fornecido")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Tarefa encontrada", content = @Content(schema = @Schema(implementation = TaskResponseDTO.class))),
      @ApiResponse(responseCode = "404", description = "Tarefa não encontrada com esse ID")
  })
  @ResponseStatus(HttpStatus.OK)
  public TaskResponseDTO findById(
      @Parameter(description = "ID da tarefa", example = "1") @PathVariable Long id) {
    return service.findById(id);
  }

  @PostMapping
  @Operation(summary = "Registrar nova tarefa", description = "Usuário autenticado envia seu token JWT e registra uma nova tarefa")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Tarefa cadastrada com sucesso!"),
      @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")
  })
  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados para registrar uma nova tarefa", required = true, content = @Content(schema = @Schema(implementation = TaskDTO.class)))
  @ResponseStatus(HttpStatus.OK)
  public TaskResponseDTO createTask(@RequestBody @Valid TaskDTO dto) {
    return service.create(dto);
  }

  @Operation(summary = "Atualizar tarefa", description = "Usuário autenticado envia seu token JWT e o ID da tarefa que deseja atualizar")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso!"),
      @ApiResponse(responseCode = "401", description = "Token inválido ou expirado"),
      @ApiResponse(responseCode = "404", description = "Tarefa com esse ID não identificado para prosseguir com a atualização"),
  })
  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados para atualizar uma tarefa existente", required = true, content = @Content(schema = @Schema(implementation = TaskUpdateDTO.class)))
  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{id}")
  public TaskResponseDTO updateTask(@Parameter(description = "ID da tarefa", example = "1") @PathVariable Long id,
      @RequestBody @Valid TaskUpdateDTO dto) {
    return service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Deletar tarefa", description = "Usuário autenticado envia seu token JWT e o ID da tarefa que deseja excluir")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso!"),
      @ApiResponse(responseCode = "401", description = "Token inválido ou expirado"),
      @ApiResponse(responseCode = "404", description = "Tarefa com esse ID não identificado para prosseguir com a exclusão"),
  })
  public ResponseEntity<String> deleteTask(
      @Parameter(description = "ID da tarefa", example = "1") @PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.ok("Tarefa deletada com sucesso");
  }

}
