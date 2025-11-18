package org.example.medical_clinic_management_system.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.error.FieldErrorDto;
import org.example.medical_clinic_management_system.dto.error.MessageDto;
import org.example.medical_clinic_management_system.dto.error.ValidationErrorResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            MethodArgumentNotValidException ex, Locale locale
    ) {
        List<FieldErrorDto> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new FieldErrorDto(
                        err.getField(),
                        messageSource.getMessage(err, locale)
                ))
                .toList();

        String title = messageSource.getMessage("error.validation", null, locale);
        return ResponseEntity.badRequest().body(new ValidationErrorResponse(title, errors));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageDto> handleNotFound(EntityNotFoundException ex, Locale locale) {
        String msg = messageSource.getMessage("error.notFound", null, locale);
        return ResponseEntity.status(404).body(new MessageDto(msg));
    }
}
