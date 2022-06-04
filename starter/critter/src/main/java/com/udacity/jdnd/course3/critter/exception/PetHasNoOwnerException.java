package com.udacity.jdnd.course3.critter.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "Pet has no ower")
@NoArgsConstructor
public class PetHasNoOwnerException extends RuntimeException{
    public PetHasNoOwnerException(String message) {
        super(message);
    }
}
