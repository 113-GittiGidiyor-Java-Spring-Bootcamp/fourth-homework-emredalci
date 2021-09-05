package dev.patika.fourthhomework.mapper;

import dev.patika.fourthhomework.dto.StudentRequestDTO;
import dev.patika.fourthhomework.dto.StudentResponseDTO;
import dev.patika.fourthhomework.model.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {

    Student mapFromStudentRequestDTOtoStudent(StudentRequestDTO studentRequestDTO);
    StudentResponseDTO mapFromStudenttoStudentResponseDTO(Student student);
    Student mapFromStudentResponseDTOtoStudent(StudentResponseDTO studentResponseDTO);

}
