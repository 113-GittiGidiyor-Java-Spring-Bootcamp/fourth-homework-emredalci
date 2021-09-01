package dev.patika.fourthhomework.exception;

public class CourseIsAlreadyExistException extends RuntimeException{

    public CourseIsAlreadyExistException(String message){
        super(message);
    }
}
