package dev.patika.fourthhomework.service;


import dev.patika.fourthhomework.model.ApplicationErrorResponse;
import dev.patika.fourthhomework.repository.ApplicationErrorResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationErrorResponseService {

    private final ApplicationErrorResponseRepository applicationErrorResponseRepository;

    public ApplicationErrorResponse save(ApplicationErrorResponse applicationErrorResponse){
        return applicationErrorResponseRepository.save(applicationErrorResponse);
    }


}
