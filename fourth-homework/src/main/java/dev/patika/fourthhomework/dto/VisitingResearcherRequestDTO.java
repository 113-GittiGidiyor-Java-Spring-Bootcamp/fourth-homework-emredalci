package dev.patika.fourthhomework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitingResearcherRequestDTO extends InstructorRequestDTO {

    @ApiModelProperty(example = "100.0")
    @NotNull(message = "Hourly salary is mandatory")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private double hourlySalary;
}
