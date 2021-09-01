package dev.patika.fourthhomework.service;

import dev.patika.fourthhomework.dto.InstructorDTO;
import dev.patika.fourthhomework.exception.EntityNotFoundException;
import dev.patika.fourthhomework.exception.InstructorIsAlreadyExistException;
import dev.patika.fourthhomework.mapper.InstructorMapper;
import dev.patika.fourthhomework.model.Instructor;
import dev.patika.fourthhomework.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService implements BaseService<InstructorDTO>{

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    @Override
    public List<InstructorDTO> findAll() {
        return instructorRepository.findAll()
                .stream().map(instructorMapper::mapFromInstructortoInstructorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InstructorDTO findByName(String instructorName) {
        return instructorRepository.findByName(instructorName)
                .map(instructorMapper::mapFromInstructortoInstructorDTO).get();
    }

    @Override
    public InstructorDTO save(InstructorDTO instructorDTO) {
        Instructor instructor = instructorMapper.mapFromInstructorDTOtoInstructor(instructorDTO);
        if(instructorRepository.existsByNameAndPhoneNumber(instructor.getName(),instructor.getPhoneNumber())){
            throw new InstructorIsAlreadyExistException("Instructor is already exist");
        }else {
            instructorRepository.save(instructor);
            return instructorDTO;

        }

    }

    @Override
    public void deleteByName(String name) {
        if(!instructorRepository.findByName(name).isPresent()){
            throw new EntityNotFoundException("Entity is not found");
        }else {
            instructorRepository.deleteByName(name);
        }

    }

    @Override
    public InstructorDTO update(InstructorDTO instructorDTO) {
        if (!instructorRepository.existsByNameAndPhoneNumber(instructorDTO.getName(),instructorDTO.getPhoneNumber())){
            throw new EntityNotFoundException("Entity is not found");
        }else {
            Instructor instructor = instructorMapper.mapFromInstructorDTOtoInstructor(instructorDTO);
            instructorRepository.save(instructor);
            return instructorDTO;
        }

    }
}
