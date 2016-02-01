package org.charpy.jdto.annotations;

public @interface DtoField {

	boolean getter() default true;

	boolean setter() default true;

}
