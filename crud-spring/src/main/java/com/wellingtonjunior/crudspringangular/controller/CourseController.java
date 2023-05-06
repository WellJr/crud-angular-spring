package com.wellingtonjunior.crudspringangular.controller;

import com.wellingtonjunior.crudspringangular.domain.Course;
import com.wellingtonjunior.crudspringangular.repository.CourseRepository;
import com.wellingtonjunior.crudspringangular.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

@Validated // <-- Para reconhecer validações a nivel de classe. EX @Positive Long id
@RestController
@RequestMapping(path = "/api/courses")
public class CourseController {


    private final CourseService courseService;

    // Dependency Injection
    public CourseController(CourseRepository courseRepository, CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public @ResponseBody List<Course> getCourses() {
        return courseService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course findById(@PathVariable("id") @NotNull @Positive Long id){
        return courseService.findById(id);
    }

    @PostMapping
//    @ResponseStatus(code = HttpStatus.CREATED) <-- forma alternativa ao response entity
    public ResponseEntity<Course> save(@RequestBody @Valid Course course){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @PutMapping(path = "/{id}")
    public Course update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course) {
        return courseService.update(id, course);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        courseService.delete(id);
    }
}
