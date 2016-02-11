package org.charpy.jdto.annotations;

import com.sun.codemodel.JMod;

/**
 * Generate this field in the DTO
 *
 */
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
    int getterModifier() default JMod.PUBLIC;

    /**
     * Should generate setter method?
     * @return - true by default
     */
    boolean setter() default true;

    /**
     * Instance member setter access modifier
     * @return - public by default
     */
    int setterModifier() default JMod.PUBLIC;
    
    /**
     * Instance member access modifier
     * @return - private by default
     */
    int modifier() default JMod.PRIVATE;

    
}
