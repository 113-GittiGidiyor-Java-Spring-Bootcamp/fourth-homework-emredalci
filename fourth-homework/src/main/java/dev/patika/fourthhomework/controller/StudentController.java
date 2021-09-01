package dev.patika.fourthhomework.controller;


import dev.patika.fourthhomework.dto.InstructorDTO;
import dev.patika.fourthhomework.dto.StudentDTO;
import dev.patika.fourthhomework.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/find-all-students")
    public List<StudentDTO> findAll(){
        return studentService.findAll();
    }



    @GetMapping("/find-by-name")
    public StudentDTO findByName(String name) {
        return studentService.findByName(name);
    }

    @DeleteMapping("/delete-by-name")
    public void deleteByName(String name) {
        studentService.deleteByName(name);
    }


    @PostMapping("/save-student")
    public StudentDTO save(@RequestBody @Valid StudentDTO studentDTO) {
        return studentService.save(studentDTO);
    }

    @PutMapping("/update-student")
    public StudentDTO update(@RequestBody @Valid StudentDTO studentDTO) {
        return studentService.update(studentDTO);
    }

}
