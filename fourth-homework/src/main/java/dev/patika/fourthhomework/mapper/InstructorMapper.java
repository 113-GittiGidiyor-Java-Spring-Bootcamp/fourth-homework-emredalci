package dev.patika.fourthhomework.mapper;


import dev.patika.fourthhomework.dto.InstructorDTO;
import dev.patika.fourthhomework.model.Instructor;
import org.mapstruct.Mapper;

@Mapper
public interface InstructorMapper {

    Instructor mapFromInstructorDTOtoInstructor(InstructorDTO instructorDTO);
    InstructorDTO mapFromInstructortoInstructorDTO(Instructor instructor);

}
