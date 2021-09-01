package dev.patika.fourthhomework.controller;


import dev.patika.fourthhomework.dto.InstructorDTO;
import dev.patika.fourthhomework.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instructor")
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/find-all")
    public List<InstructorDTO> findAll(){
        return instructorService.findAll();
    }

    @GetMapping("/find-by-name")
    public InstructorDTO findByName(String instructorName){
        return instructorService.findByName(instructorName);
    }

    @PostMapping("/save-instructor")
    public InstructorDTO save(@RequestBody @Valid InstructorDTO instructorDTO){
        return instructorService.save(instructorDTO);
    }

    @DeleteMapping("/delete-by-name")
    public void deleteByName(String name){
        instructorService.deleteByName(name);
    }

    @PutMapping("/instructor-update")
    public InstructorDTO update(@RequestBody @Valid InstructorDTO instructorDTO){
        return instructorService.update(instructorDTO);
    }


}
