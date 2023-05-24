package com.example.todothymeleaf.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
//@AllArgsConstructor
public class TodoDto {
    public int id;

    @NotEmpty(message = "Toto pole je povinne")
    private String name;

    private LocalDateTime createAt;

    private LocalDateTime updatedAt;

}
