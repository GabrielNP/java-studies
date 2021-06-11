package br.com.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.forum.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
    Course findByName(String name);

}
