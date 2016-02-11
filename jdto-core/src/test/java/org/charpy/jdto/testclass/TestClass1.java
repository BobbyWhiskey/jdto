package org.charpy.jdto.testclass;

import java.sql.Date;

import org.charpy.jdto.annotations.DtoField;
import org.charpy.jdto.annotations.GenerateDto;
import org.charpy.jdto.annotations.IncludeMethodToDTO;
import org.charpy.jdto.annotations.MethodAccessModifier;

@GenerateDto
public class TestClass1 {
 
	@DtoField(getter = false, setter = false, fieldModifier = MethodAccessModifier.Public)
	int leet;

	@DtoField(getterModifier = MethodAccessModifier.Protected, setterModifier = MethodAccessModifier.Protected)
	float omg;

	@DtoField
	Date when;

	@IncludeMethodToDTO
	public boolean testMethod(Date date, String string) throws NullPointerException {
		System.out.println("method called");
		return true;
	}
}
