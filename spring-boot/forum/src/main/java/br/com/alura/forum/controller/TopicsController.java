package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.TopicDeatilsDto;
import br.com.alura.forum.controller.dto.TopicDto;
import br.com.alura.forum.form.TopicForm;
import br.com.alura.forum.form.UpdateTopicForm;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping
    public List<TopicDto> getTopics(String nameCourse) {
        if (nameCourse == null) {
            List<Topic> topics = topicRepository.findAll();
            return TopicDto.converter(topics);    
        } else {
            List<Topic> topics = topicRepository.findByCourseName(nameCourse);
            return TopicDto.converter(topics);
        }
    }

    @GetMapping("/{id}")
    public TopicDeatilsDto retrieve(@PathVariable Long id) {
        Topic topic  = topicRepository.getOne(id);
        return new TopicDeatilsDto(topic);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDto> create(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
        Topic topic = form.converter(courseRepository);
        topicRepository.save(topic);

        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form) {
        Topic topic = form.update(id, topicRepository);
        return ResponseEntity.ok(new TopicDto(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        topicRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
