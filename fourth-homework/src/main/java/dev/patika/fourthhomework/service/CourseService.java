package dev.patika.fourthhomework.service;

import dev.patika.fourthhomework.dto.CourseRequestDTO;
import dev.patika.fourthhomework.dto.CourseResponseDTO;
import dev.patika.fourthhomework.exception.CourseIsAlreadyExistException;
import dev.patika.fourthhomework.exception.EntityNotFoundException;
import dev.patika.fourthhomework.mapper.CourseMapper;
import dev.patika.fourthhomework.model.Course;
import dev.patika.fourthhomework.repository.CourseRepository;
import dev.patika.fourthhomework.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService implements BaseService<CourseResponseDTO>{

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    /** Represent all CourseDTOs
     *
     * @return CourseDTO
     */
    @Override
    public List<CourseResponseDTO> findAll() {
        return courseRepository.findAll().
                stream().map(courseMapper::mapFromCoursetoCourseResponseDTO).
                collect(Collectors.toList());
    }

    /** Represent CourseDTO by course name
     *
     * @param name
     * @return CourseDTO
     */
    @Override
    public CourseResponseDTO findByName(String name) {
        return courseRepository.findByCourseName(name).map(courseMapper::mapFromCoursetoCourseResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND));
    }

    /** Save Course to database using CourseRequestDTO
     *
     * @param courseRequestDTO
     * @return CourseDTO
     */

    public CourseResponseDTO save(CourseRequestDTO courseRequestDTO) {
        if(courseRepository.existsByCourseCode(courseRequestDTO.getCourseCode())){
            throw new CourseIsAlreadyExistException(MessageConstants.ENTITY_ALREADY_EXIST);
        }else{
            Course course = courseMapper.mapFromCourseRequestDTOtoCourse(courseRequestDTO);
            courseRepository.save(course);
            CourseResponseDTO courseResponseDTO = courseMapper.mapFromCoursetoCourseResponseDTO(course);
            return courseResponseDTO;
        }

    }

    /** Delete course by id
     *
     * @param id
     */
    @Override
    public void deleteById(long id) {
        if(!courseRepository.findById(id).isPresent()){
            throw new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND);
        }else {
            courseRepository.deleteById(id);
            System.out.println(MessageConstants.ENTITY_DELETED);
        }
    }


    /** Update Course using CourseResponseDTO. We use CourseResponseDTO for id
     *
     * @param courseResponseDTO
     * @return CourseDTO
     */

    public CourseResponseDTO update(@RequestBody @Valid CourseResponseDTO courseResponseDTO) {

        Course course = courseRepository.findById(courseResponseDTO.getId()).orElseThrow(() -> new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND));
        Course mappedCourse = courseMapper.mapFromCourseResponseDTOtoCourse(courseResponseDTO);
        mappedCourse.setId(course.getId());
        courseRepository.save(mappedCourse);
        return courseResponseDTO;
    }

}
