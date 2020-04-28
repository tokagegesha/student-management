package ge.gov.tsu.studentmanagement.apiutils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
    String INSERT = "INSERT";
    String UPDATE = "UPDATE";
    String DELETE = "DELETE";
    String SELECT = "SELECT";
    String[] on() default {};

    /**
     * შეამოწმებს ნულოვანი სიგრძის და სფეისებით შევსებულ სტრიქონებსაც.
     * შეამოწმებს ცარიელ კოლექციებსაც
     * @return
     */
    boolean allowEmptyValue() default true;
    boolean deepCheck() default false;
}