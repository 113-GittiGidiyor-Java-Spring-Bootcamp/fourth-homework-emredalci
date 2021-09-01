package dev.patika.fourthhomework.service;


import dev.patika.fourthhomework.dto.StudentDTO;
import dev.patika.fourthhomework.exception.EntityNotFoundException;
import dev.patika.fourthhomework.exception.StudentAgeNotValidException;
import dev.patika.fourthhomework.exception.StudentIsAlreadyExistException;
import dev.patika.fourthhomework.mapper.StudentMapper;
import dev.patika.fourthhomework.model.Student;
import dev.patika.fourthhomework.repository.StudentRepository;
import dev.patika.fourthhomework.util.StudentValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService implements BaseService<StudentDTO>{

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::mapFromStudenttoStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO findByName(String name) {
        return studentRepository.findByName(name)
                .map(studentMapper::mapFromStudenttoStudentDTO).get();
    }


    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        if (!StudentValidatorUtil.validateStudentAge(studentDTO.getBirthDate(), LocalDate.now())){
            throw new StudentAgeNotValidException("Student age is not valid");
        }else if (studentRepository.existsByNameAndBirthDate(studentDTO.getName(),studentDTO.getBirthDate())){
            throw new StudentIsAlreadyExistException("Student is already exist");
        }else {
            Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
            studentRepository.save(student);
            return studentDTO;
        }

    }

    @Override
    public void deleteByName(String name) {
        if(!studentRepository.findByName(name).isPresent()){
            throw new EntityNotFoundException("Entity is not found");
        }else {
            studentRepository.deleteByName(name);
        }

    }


    @Override
    public StudentDTO update(@RequestBody @Valid StudentDTO studentDTO) {
        if(!studentRepository.existsByNameAndBirthDate(studentDTO.getName(),studentDTO.getBirthDate())){
            throw new EntityNotFoundException("Entity is not found");
        }else{
            Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
            studentRepository.save(student);
            return studentDTO;
        }

    }
}
