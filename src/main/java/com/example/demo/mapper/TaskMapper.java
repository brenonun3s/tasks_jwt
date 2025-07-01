package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.TaskResponseDTO;
import com.example.demo.dto.TaskUpdateDTO;
import com.example.demo.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

 @Mapping(target = "completed", ignore = true)
 @Mapping(target = "dateCreation", ignore = true)
 @Mapping(target = "usuario", ignore = true)
 @Mapping(target = "id", ignore = true)
 Task toEntity(TaskDTO dto);

 @Mapping(target = "id", ignore = true)
 @Mapping(target = "dateCreation", ignore = true)
 @Mapping(target = "usuario", ignore = true)
 void updateEntityFromDto(TaskUpdateDTO dto, @MappingTarget Task entity);

 @Mapping(source = "dateCreation", target = "createdAt")
 TaskResponseDTO toResponseDto(Task task);
}
