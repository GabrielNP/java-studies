package br.com.forum.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.forum.model.Topic;
import br.com.forum.repository.TopicRepository;

public class UpdateTopicForm {

    @NotNull @NotEmpty
    private String title;

    @NotNull @NotEmpty
    private String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Topic update(Long id, TopicRepository topicRepository) {
        Topic topic = topicRepository.getOne(id);
        topic.setTitle(this.title);
        topic.setMessage(this.message);

        return topic;
    }

}
