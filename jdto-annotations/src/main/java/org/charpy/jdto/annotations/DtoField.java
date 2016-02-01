package org.charpy.jdto.annotations;

/**
 * Generate this field in the DTO
 *
 */
public @interface DtoField {

	boolean getter() default true;

	boolean setter() default true;

}
