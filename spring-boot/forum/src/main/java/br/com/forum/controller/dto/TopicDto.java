package br.com.forum.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.forum.model.Topic;

public class TopicDto {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime created_at;

    public TopicDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.created_at = topic.getCreatedAt();
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

    public static Page<TopicDto> converter(Page<Topic> topics) {
        return topics.map(TopicDto::new);
    }
}