package dev.patika.fourthhomework.mapper;

import dev.patika.fourthhomework.dto.ErrorResponseDTO;
import dev.patika.fourthhomework.model.ErrorResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ErrorResponseMapper {

    ErrorResponseDTO mapFromErrorResponsetoErrorResponseDTO(ErrorResponse errorResponse);
}
