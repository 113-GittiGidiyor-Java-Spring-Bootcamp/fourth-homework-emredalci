package dev.patika.fourthhomework.controller;


import dev.patika.fourthhomework.dto.*;
import dev.patika.fourthhomework.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/find-all")
    public List<InstructorResponseDTO> findAll(){
        return instructorService.findAll();
    }

    @GetMapping("/find-by-name")
    public InstructorResponseDTO findByName(String instructorName){
        return instructorService.findByName(instructorName);
    }

    @PostMapping("/save-permanent-instructor")
    public PermanentInstructorResponseDTO savePermentInstructor(@RequestBody @Valid PermanentInstructorRequestDTO permanentInstructorRequestDTO) {
        return instructorService.savePermentInstructor(permanentInstructorRequestDTO);
    }

    @PostMapping("/save-visiting-researcher")
    public VisitingResearcherResponseDTO saveVisitingResearcher(@RequestBody @Valid VisitingResearcherRequestDTO visitingResearcherRequestDTO) {
        return instructorService.saveVisitingResearcher(visitingResearcherRequestDTO);
    }

    @DeleteMapping("/delete-by-id")
    public void deleteById(long id){
        instructorService.deleteById(id);
    }


    @PutMapping("/update-permanent-instructor")
    public PermanentInstructorResponseDTO updatePermanentInstructor(@RequestBody @Valid PermanentInstructorResponseDTO permanentInstructorResponseDTO){
        return instructorService.updatePermanentInstructor(permanentInstructorResponseDTO);
    }

    @PutMapping("/update-visiting-researcher")
    public VisitingResearcherResponseDTO updateVisitingResearcher(@RequestBody @Valid VisitingResearcherResponseDTO visitingResearcherResponseDTO){
        return instructorService.updateVisitingResearcher(visitingResearcherResponseDTO);
    }




}
