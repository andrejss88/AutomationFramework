package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
public @interface TestInfo {

    enum Priority {
        LOW, MEDIUM, HIGH
    }

    enum TestType {
        FUNCTIONAL, LOAD, SMOKE
    }

    TestType testType();

    Priority priority() default Priority.MEDIUM;

    String[] tags() default "";

    String description() default "";

}