package dev.patika.fourthhomework.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.patika.fourthhomework.model.PermanentInstructor;
import dev.patika.fourthhomework.model.VisitingResearcher;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorRequestDTO {

    @ApiModelProperty(example = "Emre")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3,max = 50)
    private String name;

    @ApiModelProperty(example = "İstanbul")
    @NotBlank(message = "Address is mandatory")
    @Size(max = 100)
    private String address;

    @ApiModelProperty(example = "05XXXXXXXXX")
    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 10,max = 11)
    private String phoneNumber;
}
