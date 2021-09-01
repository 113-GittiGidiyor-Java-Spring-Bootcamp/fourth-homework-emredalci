package dev.patika.fourthhomework.service;

import dev.patika.fourthhomework.dto.CourseDTO;
import dev.patika.fourthhomework.exception.CourseIsAlreadyExistException;
import dev.patika.fourthhomework.exception.EntityNotFoundException;
import dev.patika.fourthhomework.mapper.CourseMapper;
import dev.patika.fourthhomework.model.Course;
import dev.patika.fourthhomework.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService implements BaseService<CourseDTO>{

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().
                stream().map(courseMapper::mapFromCoursetoCourseDTO).
                collect(Collectors.toList());
    }

    @Override
    public CourseDTO findByName(String name) {
        return courseRepository.findByCourseName(name).map(courseMapper::mapFromCoursetoCourseDTO).get();
    }


    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        if(courseRepository.existsByCourseCode(course.getCourseCode())){
            throw new CourseIsAlreadyExistException("Course is already exists");
        }else{
            courseRepository.save(course);
            return courseDTO;
        }

    }

    @Override
    public void deleteByName(String courseName) {
        if(!courseRepository.findByCourseName(courseName).isPresent()){
            throw new EntityNotFoundException("Entity is not found");
        }else
            courseRepository.deleteByCourseName(courseName);
    }


    @Override
    public CourseDTO update(CourseDTO courseDTO) {
        if(!courseRepository.existsByCourseCode(courseDTO.getCourseCode())){
            throw new EntityNotFoundException("Entity is not found");
        }else {
            Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
            courseRepository.save(course);
            return courseDTO;
        }

    }


//List<CourseDTO> courses = courseRepository.findAll().stream().map(courseMapper::mapFromCoursetoCourseDTO).collect(Collectors.toList());


    /*
    public Optional<Course> saveCourse(CourseDTO courseDTO){
        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        return Optional.of(courseRepository.save(course));
    }
    */
}
