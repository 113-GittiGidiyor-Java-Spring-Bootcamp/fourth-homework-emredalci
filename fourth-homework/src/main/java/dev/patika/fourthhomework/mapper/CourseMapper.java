package dev.patika.fourthhomework.mapper;

import dev.patika.fourthhomework.dto.CourseDTO;
import dev.patika.fourthhomework.model.Course;
import org.mapstruct.Mapper;

@Mapper
public interface CourseMapper {

    Course mapFromCourseDTOtoCourse(CourseDTO courseDTO);

    CourseDTO mapFromCoursetoCourseDTO(Course course);
}
