package org.charpy.jdto.annotations;


/**
 * Tells the DTO generator engine to copy this method to the DTO.
 * <b>WARNING: Make sure that every class used in this method is referenced by full class name (i.e "java.sql.Date" ) to avoid compilation problem</b> 
 */
public @interface IncludeMethodToDTO {
}
