package dev.patika.fourthhomework.exception;

public class StudentIsAlreadyExistException extends RuntimeException{

    public StudentIsAlreadyExistException(String message){
        super(message);
    }

}
