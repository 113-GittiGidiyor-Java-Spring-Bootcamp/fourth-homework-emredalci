package dev.patika.fourthhomework.controller;


import dev.patika.fourthhomework.dto.CourseDTO;
import dev.patika.fourthhomework.model.Course;
import dev.patika.fourthhomework.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/find-all")
    public ResponseEntity<List<CourseDTO>> findAllCourses(){
        return new ResponseEntity<>(courseService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/find-by-id")
    public CourseDTO findById(long id){
        return courseService.findById(id);
    }

    @DeleteMapping("/delete-by-id")
    public void deleteById(long id) {
        courseService.deleteById(id);
    }

    @PostMapping("/save-course")
    public ResponseEntity<CourseDTO> saveCourse(@RequestBody @Valid CourseDTO courseDTO){
        return new ResponseEntity<>(courseService.save(courseDTO), HttpStatus.OK);
    }

    @PutMapping("/update")
    public CourseDTO update(CourseDTO courseDTO) {
        return courseService.update(courseDTO);
    }

}
