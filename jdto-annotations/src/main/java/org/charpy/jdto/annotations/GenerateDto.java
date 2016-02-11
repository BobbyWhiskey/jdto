package org.charpy.jdto.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Add this to a class to generate a DTO based on the class
 *
 */
@Documented
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GenerateDto {
	// TODO Add : GenerateConstructor with all params
}
