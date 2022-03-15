package ua.site.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SampleValueMatchValidator
        implements ConstraintValidator<SampleValueMatch, Object> {

    private String lat;
    private String lon;

    public void initialize(SampleValueMatch constraintAnnotation) {
        this.lat = constraintAnnotation.field();
        this.lon = constraintAnnotation.fieldMatch();
    }

    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {
        try {
            Double fieldValue = (Double) new BeanWrapperImpl(value)
                    .getPropertyValue(lat);
            Double fieldMatchValue = (Double) new BeanWrapperImpl(value)
                    .getPropertyValue(lon);

            if (fieldValue != null && fieldMatchValue != null)
                return true;
        } catch (Exception ex) {
            return false;
        }

        return false;
    }
}