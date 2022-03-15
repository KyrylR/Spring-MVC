package ua.site.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE, ANNOTATION_TYPE})
@Constraint(validatedBy = SampleValueMatchValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SampleValueMatch {

    String message() default "Incorrect values!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String lon();

    String lan();

    String depth();

    @Target({TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        SampleValueMatch[] value();
    }
}