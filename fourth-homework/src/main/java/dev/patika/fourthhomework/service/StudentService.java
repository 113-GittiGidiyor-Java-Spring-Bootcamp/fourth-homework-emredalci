package dev.patika.fourthhomework.service;


import dev.patika.fourthhomework.dto.CourseResponseDTO;
import dev.patika.fourthhomework.dto.StudentRequestDTO;
import dev.patika.fourthhomework.dto.StudentResponseDTO;
import dev.patika.fourthhomework.exception.EntityNotFoundException;
import dev.patika.fourthhomework.exception.StudentAgeNotValidException;
import dev.patika.fourthhomework.exception.StudentIsAlreadyExistException;
import dev.patika.fourthhomework.mapper.StudentMapper;
import dev.patika.fourthhomework.model.Student;
import dev.patika.fourthhomework.repository.StudentRepository;
import dev.patika.fourthhomework.util.MessageConstants;
import dev.patika.fourthhomework.util.StudentValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService implements BaseService<StudentResponseDTO>{

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    /** Represent all StudentResponseDTOs
     *
     * @return StudentDTO
     */
    @Override
    public List<StudentResponseDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::mapFromStudenttoStudentResponseDTO)
                .collect(Collectors.toList());
    }

    /** Represent StudentResposneDTO by student name
     *
     * @param name
     * @return StudentDTO
     */
    @Override
    public StudentResponseDTO findByName(String name) {
        return studentRepository.findByName(name)
                .map(studentMapper::mapFromStudenttoStudentResponseDTO).get();
    }


    /** Save student using StudentRequestDTO
     *
     * @param studentRequestDTO
     * @return
     */
    public StudentResponseDTO save(StudentRequestDTO studentRequestDTO) {
        if (!StudentValidatorUtil.validateStudentAge(studentRequestDTO.getBirthDate(), LocalDate.now())){
            throw new StudentAgeNotValidException(MessageConstants.STUDENT_NOT_VALID);
        }else if (studentRepository.existsByNameAndBirthDate(studentRequestDTO.getName(), studentRequestDTO.getBirthDate())){
            throw new StudentIsAlreadyExistException(MessageConstants.ENTITY_ALREADY_EXIST);
        }else {
            Student student = studentMapper.mapFromStudentRequestDTOtoStudent(studentRequestDTO);
            studentRepository.save(student);
            StudentResponseDTO studentResponseDTO  = studentMapper.mapFromStudenttoStudentResponseDTO(student);
            return studentResponseDTO;
        }

    }


    /** Delete student using id
     *
     * @param id
     */
    @Override
    public void deleteById(long id) {
        if(!studentRepository.findById(id).isPresent()){
            throw new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND);
        }else {
            studentRepository.deleteById(id);
            System.out.println(MessageConstants.ENTITY_DELETED);
        }
    }

    /** Update Student using StudentResponseDTO. We use StudentResponseDTO for id
     *
     * @param studentResponseDTO
     * @return StuentDTO
     */

    public StudentResponseDTO update(@RequestBody @Valid StudentResponseDTO studentResponseDTO) {
        Student student = studentRepository.findById(studentResponseDTO.getId()).orElseThrow(() -> new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND));
        Student mappedStudent = studentMapper.mapFromStudentResponseDTOtoStudent(studentResponseDTO);
        mappedStudent.setId(student.getId());
        studentRepository.save(mappedStudent);
        return studentResponseDTO;
    }

    /** Represent students by id for course mapping
     *
     * @param studentIds
     * @return Students
     */
    public Set<Student> findAllIds(Set<Long> studentIds){
        Set<Student> students = new HashSet<>();
        for (Long i:studentIds
             ) {
            students.add(studentRepository
                    .findById(i)
                    .orElseThrow(()-> new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND)));
        };
        return students;

    }
}
