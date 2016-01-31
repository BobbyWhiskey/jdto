package org.charpy.jdto.testclass;

import java.sql.Date;

import org.charpy.jdto.annotations.DtoField;
import org.charpy.jdto.annotations.GenerateDto;

@GenerateDto
public class TestClass1 {

	@DtoField
	int leet;
	
	@DtoField
	float omg;
	
	@DtoField
	Date when;
}
