package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.model.StatusTopic;
import br.com.alura.forum.model.Topic;

public class TopicDeatilsDto {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime created_at;
    private String nameAuthor;
    private StatusTopic status;
    private List<AnswerDto> answers;

    public TopicDeatilsDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.created_at = topic.getCreatedAt();
        this.nameAuthor = topic.getAuthor().getName();
        this.status = topic.getStatus();
        this.answers = new ArrayList<>();
        this.answers.addAll(topic.getAnswers().stream().map(AnswerDto::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public StatusTopic getStatus() {
        return status;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public List<AnswerDto> getAnswer() {
        return answers;
    }
}
 