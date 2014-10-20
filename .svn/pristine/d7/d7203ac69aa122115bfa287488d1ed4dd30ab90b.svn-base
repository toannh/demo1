package vn.chodientu.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    @Autowired
    private javax.validation.Validator baseValidator;

    public <T> Map<String, String> validate(T object) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<T>> constraints = baseValidator.validate(object);
        for (ConstraintViolation<T> constraint : constraints) {
            errors.put(constraint.getPropertyPath().toString(), constraint.getMessage());
        }
        return errors; 
    }
}
