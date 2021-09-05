package dev.patika.fourthhomework.controller;

import dev.patika.fourthhomework.dto.ErrorResponseDTO;
import dev.patika.fourthhomework.service.ErrorResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/errors")
@RequiredArgsConstructor
public class ErrorResponseController {

    private final ErrorResponseService errorResponseService;

    @GetMapping("/order-by-created-date")
    public List<ErrorResponseDTO> orderByCreatedDataDesc() {
        return errorResponseService.orderByCreatedDataDesc();
    }

    @GetMapping("/find-all-by-status")
    public List<ErrorResponseDTO> findAllByStatus(int status) {
        return errorResponseService.findAllByStatus(status);
    }

}
