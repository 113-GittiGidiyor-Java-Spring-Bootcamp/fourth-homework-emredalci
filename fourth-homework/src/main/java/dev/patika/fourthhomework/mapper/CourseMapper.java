package dev.patika.fourthhomework.mapper;

import dev.patika.fourthhomework.dto.CourseRequestDTO;
import dev.patika.fourthhomework.dto.CourseResponseDTO;
import dev.patika.fourthhomework.model.Course;
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



    //Yeni
    @Mapping(target = "instructor", expression = "java(instructorService.findById(courseRequestDTO.getInstructorId()))")
    @Mapping(target = "students",expression = "java(studentService.findAllIds(courseRequestDTO.getStudentIds()))")
    public abstract Course mapFromCourseRequestDTOtoCourse(CourseRequestDTO courseRequestDTO);

    public abstract CourseResponseDTO mapFromCoursetoCourseResponseDTO(Course course);
    public abstract Course mapFromCourseResponseDTOtoCourse(CourseResponseDTO courseResponseDTO);


    //Eski
    /*
    @Mapping(target = "instructor", expression = "java(instructorService.findById(courseDTO.getInstructorId()))")
    @Mapping(target = "students",expression = "java(studentService.findAllIds(courseDTO.getStudentIds()))")
    public abstract Course mapFromCourseDTOtoCourse(CourseRequestDTO courseRequestDTO);
    public abstract CourseRequestDTO mapFromCoursetoCourseDTO(Course course);
     */

}
