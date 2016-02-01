package org.charpy.jdto.dto;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.charpy.jdto.annotations.DtoField;
import org.charpy.jdto.annotations.GenerateDto;
import org.charpy.jdto.annotations.IncludeMethodToDTO;

@GenerateDto
public class ExampleObject {

	@DtoField
	private int number;

	// Generate only a setter, not getter
	@DtoField(getter = false)
	private Date date;

	@DtoField
	private List<String> listOfStrings;

	@DtoField
	private Collection<Integer> listOfInts;

	@DtoField
	private Collection<Date> listOfDates;

	/**
	 * Example of a utility method that could be included in the DTO.
	 * All the parameters, exception thrown, and return type will be kept when generating method
	 * @param c 
	 * @return
	 */
	@IncludeMethodToDTO
	public void sortStringList() throws NullPointerException {
		Collections.sort(listOfStrings);
	}

	/**
	 * Example of a utility method that could be included in the DTO
	 * All the parameters, exception thrown, and return type will be kept when generating method
	 * @return
	 */
	@IncludeMethodToDTO
	public void sortListOfInts() throws NullPointerException {
		Collections.sort(listOfStrings);
	}

}
