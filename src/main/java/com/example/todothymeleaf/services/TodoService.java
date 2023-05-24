package com.example.todothymeleaf.services;

import com.example.todotemplates.dto.TodoDto;
import com.example.todotemplates.entities.TodoEntity;

import java.util.List;

public interface TodoService {
    List<TodoDto> findTodos();

    TodoEntity create(TodoEntity todoEntity);

    TodoDto findById(int id) throws Exception;

    void update(TodoDto todoDto);
}
