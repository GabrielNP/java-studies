package br.com.forum.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.forum.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Page<Topic> findByCourseName(String nameCourse, Pageable pagination);

    @Query("SELECT t FROM Topic t WHERE  t.course.name = :nameCourse")
    List<Topic> getTopicByCourseName(@Param("nameCourse") String nameCourse);
    
}
