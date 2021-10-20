package by.scooter.exception;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class ValidationError extends RuntimeException {

    private final Map<String, String> fieldMessageMap;
    private final transient Object failedObject;

    public ValidationError(BindingResult bindingResult, Object failedObject) {
        this.failedObject = failedObject;
        fieldMessageMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach(f -> fieldMessageMap.put(f.getField(), f.getDefaultMessage()));
    }
}
