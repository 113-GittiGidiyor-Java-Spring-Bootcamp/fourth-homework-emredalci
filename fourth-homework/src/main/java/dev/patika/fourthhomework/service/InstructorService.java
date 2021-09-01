package dev.patika.fourthhomework.service;

import dev.patika.fourthhomework.dto.InstructorDTO;
import dev.patika.fourthhomework.exception.EntityNotFoundException;
import dev.patika.fourthhomework.exception.InstructorIsAlreadyExistException;
import dev.patika.fourthhomework.mapper.InstructorMapper;
import dev.patika.fourthhomework.model.Instructor;
import dev.patika.fourthhomework.repository.InstructorRepository;
import dev.patika.fourthhomework.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService implements BaseService<InstructorDTO>{

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    /** Represent all InstructorDTOs
     *
     * @return InstructorDTO
     */
    @Override
    public List<InstructorDTO> findAll() {
        return instructorRepository.findAll()
                .stream().map(instructorMapper::mapFromInstructortoInstructorDTO)
                .collect(Collectors.toList());
    }

    /** Represent InstructorDTO by instructor name
     *
     * @param instructorName
     * @return InstructorDTO
     */
    @Override
    public InstructorDTO findByName(String instructorName) {
        return instructorRepository.findByName(instructorName)
                .map(instructorMapper::mapFromInstructortoInstructorDTO).get();
    }

    /** Save a InstructorDTO to database changing Instructor
     *
     * @param instructorDTO
     * @return InstructorDTO
     */
    @Override
    public InstructorDTO save(InstructorDTO instructorDTO) {
        Instructor instructor = instructorMapper.mapFromInstructorDTOtoInstructor(instructorDTO);
        if(instructorRepository.existsByNameAndPhoneNumber(instructor.getName(),instructor.getPhoneNumber())){
            throw new InstructorIsAlreadyExistException(MessageConstants.ENTITY_ALREADY_EXIST);
        }else {
            instructorRepository.save(instructor);
            return instructorDTO;

        }

    }

    /** Delete a instructor by name
     *
     * @param name
     */
    @Override
    public void deleteByName(String name) {
        if(!instructorRepository.findByName(name).isPresent()){
            throw new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND);
        }else {
            instructorRepository.deleteByName(name);
        }

    }

    /** Update Instructor using InstructorDTO
     *
     * @param instructorDTO
     * @return
     */
    @Override
    public InstructorDTO update(InstructorDTO instructorDTO) {
        if (!instructorRepository.existsByNameAndPhoneNumber(instructorDTO.getName(),instructorDTO.getPhoneNumber())){
            throw new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND);
        }else {
            Instructor instructor = instructorMapper.mapFromInstructorDTOtoInstructor(instructorDTO);
            instructorRepository.save(instructor);
            return instructorDTO;
        }

    }

    public Instructor findById(long id){
        return instructorRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND));
    }
}
