package ge.gov.tsu.studentmanagement.apiutils.annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface OriginalEntity {
    Class value();
    String fieldName();
}
