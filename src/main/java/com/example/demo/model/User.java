package com.example.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;

 private String name;

 @Column(unique = true, nullable = false)
 private String email;

 @Column(nullable = false)
 private String password;

 @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<Task> tarefas; // relacionamento com Task
}
