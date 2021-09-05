package dev.patika.fourthhomework.service;

import dev.patika.fourthhomework.dto.*;
import dev.patika.fourthhomework.exception.EntityNotFoundException;
import dev.patika.fourthhomework.exception.InstructorIsAlreadyExistException;
import dev.patika.fourthhomework.mapper.InstructorMapper;
import dev.patika.fourthhomework.model.Instructor;
import dev.patika.fourthhomework.model.PermanentInstructor;
import dev.patika.fourthhomework.model.VisitingResearcher;
import dev.patika.fourthhomework.repository.InstructorRepository;
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
public class InstructorService implements BaseService<InstructorResponseDTO>{

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    /** Represent all InstructorResponseDTOs
     *
     * @return InstructorDTO
     */
    @Override
    public List<InstructorResponseDTO> findAll() {
        return instructorRepository.findAll()
                .stream().map(instructorMapper::mapFromInstructortoInstructorResponseDTO)
                .collect(Collectors.toList());
    }

    /** Represent InstructorResponseDTO by instructor name
     *
     * @param instructorName
     * @return InstructorDTO
     */
    @Override
    public InstructorResponseDTO findByName(String instructorName) {
        return instructorRepository.findByName(instructorName)
                .map(instructorMapper::mapFromInstructortoInstructorResponseDTO).get();
    }

    /** save PermanentInstructor using PermanentInstructorRequestDTO
     *
     * @param permanentInstructorRequestDTO
     * @return PermanentInstructorDTO
     */
    public PermanentInstructorResponseDTO savePermentInstructor(PermanentInstructorRequestDTO permanentInstructorRequestDTO) {
        if(instructorRepository.existsByNameAndPhoneNumber(permanentInstructorRequestDTO.getName(),permanentInstructorRequestDTO.getPhoneNumber())){
            throw new InstructorIsAlreadyExistException(MessageConstants.ENTITY_ALREADY_EXIST);
        }else {
            PermanentInstructor permanentInstructor =
                    instructorMapper.mapFromPermanentInstructorRequestDTOtoPermanentInstructor(permanentInstructorRequestDTO);


            instructorRepository.save(permanentInstructor);

            PermanentInstructorResponseDTO permanentInstructorResponseDTO
                    = instructorMapper.mapFromPermanentInstructortoPermanentInstructorResponseDTO(permanentInstructor);


            return permanentInstructorResponseDTO;
        }

    }

    /** save VisitingResearcher using VisitingResearcherRequestDTO
     *
     * @param visitingResearcherRequestDTO
     * @return VisitingResearcherDTO
     */
    public VisitingResearcherResponseDTO saveVisitingResearcher(VisitingResearcherRequestDTO visitingResearcherRequestDTO) {
        if(instructorRepository.existsByNameAndPhoneNumber(visitingResearcherRequestDTO.getName(),visitingResearcherRequestDTO.getPhoneNumber())){
            throw new InstructorIsAlreadyExistException(MessageConstants.ENTITY_ALREADY_EXIST);
        }else {
            VisitingResearcher visitingResearcher =
                    instructorMapper.mapFromVisitingResearcherRequestDTOtoVisitingResearcher(visitingResearcherRequestDTO);


            instructorRepository.save(visitingResearcher);

            VisitingResearcherResponseDTO visitingResearcherResponseDTO =
                    instructorMapper.mapFromVisitingResearchertoVisitingResearcherResponseDTO(visitingResearcher);

            return visitingResearcherResponseDTO;

        }

    }


    /** Delete instructor using id
     *
     * @param id
     */
    @Override
    public void deleteById(long id) {
        if(!instructorRepository.findById(id).isPresent()){
            throw new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND);
        }else {
            instructorRepository.deleteById(id);
            System.out.println(MessageConstants.ENTITY_DELETED);
        }
    }

    /** Update Permanent Instructor using PermanentInstructorResponseDTO.We use PermanentInstructorResponseDTO
     * for id
     *
     * @param permanentInstructorResponseDTO
     * @return
     */

    public PermanentInstructorResponseDTO updatePermanentInstructor(@RequestBody @Valid PermanentInstructorResponseDTO permanentInstructorResponseDTO) {
        PermanentInstructor permanentInstructor
                = (PermanentInstructor) instructorRepository.findById(permanentInstructorResponseDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND));
        PermanentInstructor mappedPermanentInstructor = instructorMapper.mapFromPermanentInstructorResponseDTOtoPermanentInstructor(permanentInstructorResponseDTO);
        mappedPermanentInstructor.setId(permanentInstructorResponseDTO.getId());
        instructorRepository.save(mappedPermanentInstructor);
        return permanentInstructorResponseDTO;
    }

    /** Update Visiting Researcher using VisitingResearcherResponseDTO. We use VisitingResearcherResponseDTO
     * for id
     *
     * @param visitingResearcherResponseDTO
     * @return
     */
    public VisitingResearcherResponseDTO updateVisitingResearcher(@RequestBody @Valid VisitingResearcherResponseDTO visitingResearcherResponseDTO){
        VisitingResearcher visitingResearcher =
                (VisitingResearcher) instructorRepository.findById(visitingResearcherResponseDTO.getId())
                        .orElseThrow(() -> new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND));
        VisitingResearcher mappedVisitingResearcher = instructorMapper.mapFromVisitingResearherResponseDTOtoVisitingResearcher(visitingResearcherResponseDTO);
        mappedVisitingResearcher.setId(visitingResearcherResponseDTO.getId());
        instructorRepository.save(mappedVisitingResearcher);
        return visitingResearcherResponseDTO;
    }

    /** Represent instructor by id
     *
     * @param id
     * @return Instructor
     */
    public Instructor findById(long id){
        return instructorRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MessageConstants.ENTITY_NOT_FOUND));
    }


}
