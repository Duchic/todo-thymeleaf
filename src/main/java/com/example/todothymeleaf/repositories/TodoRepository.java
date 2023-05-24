package com.example.todothymeleaf.repositories;

import com.example.todotemplates.entities.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//"""
//SELECT * FROM todos WHERE name = 'ahoj';
//"""

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
    TodoEntity findByName(String name);
}
