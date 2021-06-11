package br.com.forum.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.forum.model.Course;
import br.com.forum.model.Topic;
import br.com.forum.repository.CourseRepository;

public class TopicForm {

    @NotNull @NotEmpty
    private String title;
    @NotNull @NotEmpty
    private String message;
    @NotNull @NotEmpty
    private String nameCourse;


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

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public Topic converter(CourseRepository courseRepository) {
        Course course = courseRepository.findByName(nameCourse);
        return new Topic(title, message, course);
    }
    
}
