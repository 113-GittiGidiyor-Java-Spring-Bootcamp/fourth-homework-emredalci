package dev.patika.fourthhomework.mapper;

import dev.patika.fourthhomework.dto.StudentDTO;
import dev.patika.fourthhomework.model.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {

    Student mapFromStudentDTOtoStudent(StudentDTO studentDTO);

    StudentDTO mapFromStudenttoStudentDTO(Student student);


}
