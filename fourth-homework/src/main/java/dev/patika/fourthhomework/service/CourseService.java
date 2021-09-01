package dev.patika.fourthhomework.service;

import dev.patika.fourthhomework.dto.CourseDTO;
import dev.patika.fourthhomework.exception.CourseIsAlreadyExistException;
import dev.patika.fourthhomework.exception.EntityNotFoundException;
import dev.patika.fourthhomework.mapper.CourseMapper;
import dev.patika.fourthhomework.model.Course;
import dev.patika.fourthhomework.repository.CourseRepository;
import dev.patika.fourthhomework.util.MessageConstants;
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

    /** Represent all CourseDTOs
     *
     * @return CourseDTO
     */
    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().
                stream().map(courseMapper::mapFromCoursetoCourseDTO).
                collect(Collectors.toList());
    }

    /** Represent CourseDTO by course name
     *
     * @param name
     * @return CourseDTO
     */
    @Override
    public CourseDTO findByName(String name) {
        return courseRepository.findByCourseName(name).map(courseMapper::mapFromCoursetoCourseDTO).get();
    }

    /** Save a CourseDTO  to database changing Course
     *
     * @param courseDTO
     * @return CourseDTO
     */
    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        if(courseRepository.existsByCourseCode(course.getCourseCode())){
            throw new CourseIsAlreadyExistException(MessageConstants.ENTITY_ALREADY_EXIST);
        }else{
            courseRepository.save(course);
            return courseDTO;
        }

    }

    /** Delete a course by name
     *
     * @param courseName
     */
    @Override
    public void deleteByName(String courseName) {
        if(!courseRepository.findByCourseName(courseName).isPresent()){
            throw new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND);
        }else
            courseRepository.deleteByCourseName(courseName);
    }


    /** Update Course using CourseDTO
     *
     * @param courseDTO
     * @return CourseDTO
     */
    @Override
    public CourseDTO update(CourseDTO courseDTO) {
        Course course = courseRepository.findByCourseCode(courseDTO.getCourseCode()).orElseThrow(() -> new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND));

        Course mappedCourse=courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        mappedCourse.setId(course.getId());
        courseRepository.save(mappedCourse);
        return courseDTO;

        /*
        if(!courseRepository.existsByCourseCode(courseDTO.getCourseCode())){
            throw new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND);
        }else {


            Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
            courseRepository.save(course);
            return courseDTO;


        }

        */

    }




//List<CourseDTO> courses = courseRepository.findAll().stream().map(courseMapper::mapFromCoursetoCourseDTO).collect(Collectors.toList());


    /*
    public Optional<Course> saveCourse(CourseDTO courseDTO){
        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        return Optional.of(courseRepository.save(course));
    }
    */
}
