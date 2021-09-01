package dev.patika.fourthhomework.exception;


import dev.patika.fourthhomework.model.ApplicationErrorResponse;
import dev.patika.fourthhomework.service.ApplicationErrorResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ApplicationErrorResponseService applicationErrorResponseService;

    @ExceptionHandler({CourseIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApplicationErrorResponse> handleException(CourseIsAlreadyExistException exc) {
        ApplicationErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InstructorIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  ResponseEntity<ApplicationErrorResponse> handleException(InstructorIsAlreadyExistException exc){
        ApplicationErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentAgeNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApplicationErrorResponse> handleException(StudentAgeNotValidException exc){
        ApplicationErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentNumberForOneCourseExceedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApplicationErrorResponse> handleException(StudentNumberForOneCourseExceedException exc){
        ApplicationErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApplicationErrorResponse> handleException(EntityNotFoundException exc){
        ApplicationErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({StudentIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApplicationErrorResponse> handleException(StudentIsAlreadyExistException exc){
        ApplicationErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }


    private ApplicationErrorResponse prepareErrorResponse(HttpStatus httpStatus, String message) {

        ApplicationErrorResponse response = new ApplicationErrorResponse();
        response.setStatus(httpStatus.value());
        response.setMessage(message);

        applicationErrorResponseService.save(response);
        return response;

    }


}
