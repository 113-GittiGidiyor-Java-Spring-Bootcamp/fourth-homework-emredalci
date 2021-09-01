package dev.patika.fourthhomework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ApplicationErrorResponse extends AbstractBaseEntity{

    private int status;
    private String message;


}
