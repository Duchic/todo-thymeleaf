package com.example.todothymeleaf.services;

import com.example.todothymeleaf.dto.TodoDto;
import com.example.todothymeleaf.entities.TodoEntity;

import java.util.List;

public interface TodoService {
    List<TodoDto> findTodos();

    TodoEntity create(TodoEntity todoEntity);

    TodoDto findById(int id) throws Exception;

    void update(TodoDto todoDto);
}
