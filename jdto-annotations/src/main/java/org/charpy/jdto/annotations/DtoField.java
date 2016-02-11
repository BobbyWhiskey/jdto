package org.charpy.jdto.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Generate this field in the DTO
 *
 */
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.SOURCE)
public @interface DtoField {
    /**
     * Should generate getter method?
     * @return - true by default
     */
    boolean getter() default true;
    
    /**
     * Instance member getter access modifier
     * @return - public by default
     */
    MethodAccessModifier getterModifier() default MethodAccessModifier.Public;

    /**
     * Should generate setter method?
     * @return - true by default
     */
    boolean setter() default true;

    /**
     * Instance member setter access modifier
     * @return - public by default
     */
    MethodAccessModifier setterModifier() default MethodAccessModifier.Public;
    
    /**
     * Instance member access modifier
     * @return - private by default
     */
    MethodAccessModifier fieldModifier() default MethodAccessModifier.Private;

    
}
