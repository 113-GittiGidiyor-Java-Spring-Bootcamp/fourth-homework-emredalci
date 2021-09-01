package dev.patika.fourthhomework.mapper;

import dev.patika.fourthhomework.dto.CourseDTO;
import dev.patika.fourthhomework.model.Course;
import dev.patika.fourthhomework.repository.CourseRepository;
import dev.patika.fourthhomework.repository.InstructorRepository;
import dev.patika.fourthhomework.service.CourseService;
import dev.patika.fourthhomework.service.InstructorService;
import dev.patika.fourthhomework.service.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CourseMapper {

    @Autowired
    protected InstructorService instructorService;

    @Autowired
    protected StudentService studentService;


    @Mapping(target = "instructor", expression = "java(instructorService.findById(courseDTO.getInstructorId()))")
    @Mapping(target = "students",expression = "java(studentService.findAllIds(courseDTO.getStudentIds()))")
    public abstract Course mapFromCourseDTOtoCourse(CourseDTO courseDTO);
    public abstract CourseDTO mapFromCoursetoCourseDTO(Course course);
}
