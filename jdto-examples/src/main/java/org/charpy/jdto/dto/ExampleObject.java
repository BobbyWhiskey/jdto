package org.charpy.jdto.dto;

import java.sql.Date;
import java.util.Collection;

import org.charpy.jdto.annotations.DtoField;
import org.charpy.jdto.annotations.GenerateDto;

@GenerateDto
public class ExampleObject {
	
	 @DtoField
	 private int number;
	 
	 @DtoField
	 private Date date;
	 
	 @DtoField
	 private Collection<String> listOfStrings;
	 
	 @DtoField
	 private Collection<Integer> listOfInts;
	 
	 @DtoField
	 private Collection<Date> listOfDates;

}
