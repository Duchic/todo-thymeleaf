package com.example.todothymeleaf.controllers;

import com.example.todothymeleaf.dto.TodoDto;
import com.example.todothymeleaf.entities.TodoEntity;
import com.example.todothymeleaf.services.TodoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoController {

    public final TodoServiceImpl todoService;

    public TodoController(TodoServiceImpl todoService) {
        this.todoService = todoService;
    }

    // vypisuje vsechny todo do todos.html
    @GetMapping("/todos") // Model je specialni objekt ktery si spravuje spring a slouzi k praci se sablonou
    public String todoListPage(Model model) {
        var todos = todoService.findTodos();
        model.addAttribute("todos", todos); // vytvori identifikator (promennou) v todos v sablone todos.html

        return "todos";
    }

    // vykresluje stranku create-todo.html
    @GetMapping("/todo/create")
    public String createTodoPage(Model model) {
        var todo = new TodoEntity();
        model.addAttribute("todo", todo);

        return "create-todo";
    }

    // vezme data z formulare ze stranky create-todo.html
    // na uspesne ulozeni, pripadnou validaci presmerujeme na todos.html
    // jinak vypiseme chyby do create-todo.html
    @PostMapping("/todo/create")
    public String createTodo(@ModelAttribute("todo") TodoEntity todoEntity) {
        todoService.create(todoEntity);

        return "redirect:/todos";
    }

    @GetMapping("/todo/{id}/update")
    public String updateTodoPage(
            @PathVariable("id") int id,
            Model model
    ) throws Exception {
        var todo = todoService.findById(id);
        model.addAttribute("todo", todo);

        return "update-todo";
    }

    @PostMapping("/todo/{id}/update")
    public String updateTodo(
            @PathVariable("id") int id,
            @ModelAttribute("todo") @Valid TodoDto todoDto,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return "update-todo";
        }
//        todoDto.setId(id);

        todoService.update(todoDto);

        return "redirect:/todos";
    }


}
