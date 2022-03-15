package ua.site.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SampleValueMatchValidator
        implements ConstraintValidator<SampleValueMatch, Object> {

    private String lat;
    private String lon;
    private String depth;

    public void initialize(SampleValueMatch constraintAnnotation) {
        this.lat = constraintAnnotation.lon();
        this.lon = constraintAnnotation.lan();
        this.depth = constraintAnnotation.depth();
    }

    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {
        try {
            Double lat = (Double) new BeanWrapperImpl(value)
                    .getPropertyValue(this.lat);
            Double lon = (Double) new BeanWrapperImpl(value)
                    .getPropertyValue(this.lon);
            Double depth = (Double) new BeanWrapperImpl(value)
                    .getPropertyValue(this.depth);

            if (lat != null && lon != null && depth != null)
                return true;
        } catch (Exception ex) {
            return false;
        }

        return false;
    }
}