package br.com.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.forum.controller.dto.TopicDeatilsDto;
import br.com.forum.controller.dto.TopicDto;
import br.com.forum.form.TopicForm;
import br.com.forum.form.UpdateTopicForm;
import br.com.forum.model.Topic;
import br.com.forum.repository.CourseRepository;
import br.com.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping
    @Cacheable(value="topicList")
    public Page<TopicDto> getTopics(
        @RequestParam(required = false) String nameCourse, 
        @PageableDefault(sort="id", direction=Direction.DESC, page=0, size=10) Pageable pagination) {

        if (nameCourse == null) {
            Page<Topic> topics = topicRepository.findAll(pagination);
            return TopicDto.converter(topics);    
        } else {
            Page<Topic> topics = topicRepository.findByCourseName(nameCourse, pagination);
            return TopicDto.converter(topics);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDeatilsDto> retrieve(@PathVariable Long id) {
        Optional<Topic> topic  = topicRepository.findById(id);
        if (topic.isPresent()) {
            return ResponseEntity.ok(new TopicDeatilsDto(topic.get()));
        }
        return ResponseEntity.notFound().build();
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
        Optional<Topic> optionalTopic  = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
            Topic topic = form.update(id, topicRepository);
            return ResponseEntity.ok(new TopicDto(topic));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Topic> optionalTopic  = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
}
