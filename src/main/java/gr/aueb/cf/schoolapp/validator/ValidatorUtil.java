package gr.aueb.cf.schoolapp.validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidatorUtil {
    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public ValidatorUtil() {
    }

    public static  <T> List<String> validateDTO(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        List<String> errors = new ArrayList<>();
        if (!violations.isEmpty()) {
            for (ConstraintViolation<T> violation : violations) {
                errors.add(violation.getMessage());
            }
        }
        return errors;
    }
}
