package org.charpy.jdto.testclass;

import java.sql.Date;

import org.charpy.jdto.annotations.DtoField;
import org.charpy.jdto.annotations.GenerateDto;
import org.charpy.jdto.annotations.IncludeMethodToDTO;

@GenerateDto
public class TestClass1 {

	@DtoField
	int leet;
	
	@DtoField
	float omg;
	
	@DtoField
	Date when;
	
	@IncludeMethodToDTO
	public boolean testMethod(Date date, String string) throws NullPointerException{
		System.out.println("method called");
		return true;
	}
}
