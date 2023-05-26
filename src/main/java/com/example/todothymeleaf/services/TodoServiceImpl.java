package com.example.todothymeleaf.services;

import com.example.todothymeleaf.dto.TodoDto;
import com.example.todothymeleaf.entities.TodoEntity;
import com.example.todothymeleaf.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    public final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoDto> findTodos() {
        return todoRepository
                .findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto findById(int id) throws Exception {
        var todo = todoRepository.findById(id)
                .orElseThrow(() -> new Exception("Todo not found [%s]".formatted(id)));

        return mapToDto(todo);
    }

    @Override
    public TodoEntity create(TodoEntity todoEntity) {
//        var newTodoEntity = mapToEntity(todoDto);
        return todoRepository.save(todoEntity);
    }

    @Override
    public void update(TodoDto todoDto) {
        var todo = mapToEntity(todoDto);
        todoRepository.save(todo);
    }

    public TodoEntity mapToEntity(TodoDto todoDto) {
        return TodoEntity.builder()
                .id(todoDto.getId())
                .name(todoDto.getName())
                .createAt(todoDto.getCreateAt())
                .updateAt(todoDto.getUpdatedAt())
                .build();
    }

    public TodoDto mapToDto(TodoEntity todoEntity) {
//        return new TodoDto(
//                todoEntity.getId(),
//                todoEntity.getName(),
//                todoEntity.getCreateAt(),
//                todoEntity.getUpdateAt()
//        );

        return TodoDto
                .builder()
                .id(todoEntity.getId())
                .name(todoEntity.getName())
                .createAt(todoEntity.getCreateAt())
                .updatedAt(todoEntity.getUpdateAt())
                .build();
    }
}
