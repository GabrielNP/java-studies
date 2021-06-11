package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.model.Answer;

public class AnswerDto {
    private Long id;
    private String message;
    private LocalDateTime created_at;
    private String nameAuthor;

    public AnswerDto(Answer answer) {
        this.id = answer.getId();
        this.message = answer.getMessage();
        this.created_at = answer.getCreatedAt();
        this.nameAuthor = answer.getAuthor().getName();
    }
    
    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

}
