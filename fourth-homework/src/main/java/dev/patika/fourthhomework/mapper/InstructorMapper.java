package dev.patika.fourthhomework.mapper;


import dev.patika.fourthhomework.dto.*;
import dev.patika.fourthhomework.model.Instructor;
import dev.patika.fourthhomework.model.PermanentInstructor;
import dev.patika.fourthhomework.model.VisitingResearcher;
import org.mapstruct.Mapper;

@Mapper
public interface InstructorMapper {


    //Yeni
    PermanentInstructor mapFromPermanentInstructorRequestDTOtoPermanentInstructor(PermanentInstructorRequestDTO permanentInstructorRequestDTO);
    PermanentInstructorResponseDTO mapFromPermanentInstructortoPermanentInstructorResponseDTO(PermanentInstructor permanentInstructor);
    PermanentInstructor mapFromPermanentInstructorResponseDTOtoPermanentInstructor(PermanentInstructorResponseDTO permanentInstructorResponseDTO);

    //Yeni
    VisitingResearcher mapFromVisitingResearcherRequestDTOtoVisitingResearcher(VisitingResearcherRequestDTO visitingResearcherRequestDTO);
    VisitingResearcherResponseDTO mapFromVisitingResearchertoVisitingResearcherResponseDTO(VisitingResearcher visitingResearcher);
    VisitingResearcher mapFromVisitingResearherResponseDTOtoVisitingResearcher(VisitingResearcherResponseDTO visitingResearcherResponseDTO);

    //Yeni
    Instructor mapFromInstructorResponseDTOtoInstructor(InstructorResponseDTO instructorResponseDTO);
    InstructorResponseDTO mapFromInstructortoInstructorResponseDTO(Instructor instructor);



    /*
    //Eski
    VisitingResearcher mapFromVisitingResearcherDTOtoVisitingResearcher(VisitingResearcherRequestRequestDTO visitingResearcherRequestDTO);
    VisitingResearcherRequestRequestDTO mapFromVisitingResearchertoVisitingResearcherDTO(VisitingResearcher visitingResearcher);

    //Eski
    PermanentInstructor mapFromPermanentInstructorDTOtoPermanentInstructor(PermanentInstructorRequestRequestDTO permanentInstructorRequestDTO);
    PermanentInstructorRequestRequestDTO mapFromPermanentInstructortoPermanentInstructorDTO(PermanentInstructor permanentInstructor);

     */


}
