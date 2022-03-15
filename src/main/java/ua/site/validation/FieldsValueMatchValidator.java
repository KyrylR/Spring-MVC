package ua.site.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsValueMatchValidator
        implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;

    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {
        try {
            Double[] fieldValue = (Double[]) new BeanWrapperImpl(value)
                    .getPropertyValue(field);
            Double[] fieldMatchValue = (Double[]) new BeanWrapperImpl(value)
                    .getPropertyValue(fieldMatch);

            if (fieldValue != null && fieldMatchValue != null) {
                return fieldValue.length == fieldMatchValue.length;
            }
        } catch (Exception ex) {
            return false;
        }

        return false;
    }
}