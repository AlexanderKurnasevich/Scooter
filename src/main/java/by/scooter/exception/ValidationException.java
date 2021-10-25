package by.scooter.exception;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {

    private final Map<String, String> fieldMessageMap;

    public ValidationException(BindingResult bindingResult) {
        fieldMessageMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach(f -> fieldMessageMap.put(f.getField(), f.getDefaultMessage()));
    }

    public Map<String, String> getFieldMessageMap(){
        return fieldMessageMap;
    }
}
