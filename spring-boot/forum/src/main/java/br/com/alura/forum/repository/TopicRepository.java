package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByCourseName(String nameCourse);

    @Query("SELECT t FROM Topic t WHERE  t.course.name = :nameCourse")
    List<Topic> getTopicByCourseName(@Param("nameCourse") String nameCourse);
    
}
