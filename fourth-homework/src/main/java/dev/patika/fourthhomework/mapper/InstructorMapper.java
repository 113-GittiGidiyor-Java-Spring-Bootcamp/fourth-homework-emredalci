package dev.patika.fourthhomework.mapper;


import dev.patika.fourthhomework.dto.*;
import dev.patika.fourthhomework.model.Instructor;
import dev.patika.fourthhomework.model.PermanentInstructor;
import dev.patika.fourthhomework.model.VisitingResearcher;
import org.mapstruct.Mapper;

@Mapper
public interface InstructorMapper {



    PermanentInstructor mapFromPermanentInstructorRequestDTOtoPermanentInstructor(PermanentInstructorRequestDTO permanentInstructorRequestDTO);
    PermanentInstructorResponseDTO mapFromPermanentInstructortoPermanentInstructorResponseDTO(PermanentInstructor permanentInstructor);
    PermanentInstructor mapFromPermanentInstructorResponseDTOtoPermanentInstructor(PermanentInstructorResponseDTO permanentInstructorResponseDTO);


    VisitingResearcher mapFromVisitingResearcherRequestDTOtoVisitingResearcher(VisitingResearcherRequestDTO visitingResearcherRequestDTO);
    VisitingResearcherResponseDTO mapFromVisitingResearchertoVisitingResearcherResponseDTO(VisitingResearcher visitingResearcher);
    VisitingResearcher mapFromVisitingResearherResponseDTOtoVisitingResearcher(VisitingResearcherResponseDTO visitingResearcherResponseDTO);


    Instructor mapFromInstructorResponseDTOtoInstructor(InstructorResponseDTO instructorResponseDTO);
    InstructorResponseDTO mapFromInstructortoInstructorResponseDTO(Instructor instructor);






}
