package com.example.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String title;

 private String description;

 private LocalDate dueDate;

 private Boolean completed;

 private LocalDate dateCreation;

 @ManyToOne(fetch = FetchType.LAZY)
 @JsonIgnoreProperties({"tarefas", "password"}) 
 private User user;
}
