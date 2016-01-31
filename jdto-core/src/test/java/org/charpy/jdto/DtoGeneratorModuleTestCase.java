package org.charpy.jdto;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import org.junit.Test;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;
import com.thoughtworks.qdox.model.JavaType;

public class DtoGeneratorModuleTestCase {

	//	/* Creating java code model classes */
	//    JCodeModel jCodeModel = new JCodeModel();
	//
	//    /* Adding packages here */
	//    JPackage jp = jCodeModel._package(factroyPackage);
	//
	//    /* Giving Class Name to Generate */
	//    JDefinedClass jc = jp._class("GeneratedFactory");
	//
	//    /* Adding annotation for the Class */
	//    jc.annotate(com.myannotation.AnyXYZ.class);
	//
	//    /* Adding class level coment */
	//    JDocComment jDocComment = jc.javadoc();
	//    jDocComment.add("Class Level Java Docs");
	//
	//
	//    /* Adding method to the Class which is public static and returns com.somclass.AnyXYZ.class */
	//    String mehtodName = "myFirstMehtod";
	//    JMethod jmCreate = jc.method(JMod.PUBLIC | JMod.STATIC, com.somclass.AnyXYZ.class, "create" + mehtodName);
	//
	//    /* Addign java doc for method */
	//    jmCreate.javadoc().add("Method Level Java Docs");
	//
	//    /* Adding method body */
	//    JBlock jBlock = jmCreate.body();
	//
	//    /* Defining method parameter */
	//     JType jt = getTypeDetailsForCodeModel(jCodeModel, "Unsigned32");
	//     if (jt != null) {
	//         jmCreate.param(jt, "data");
	//     } else {
	//        jmCreate.param(java.lang.String.class, "data");
	//     }
	//
	//    /* Defining some class Variable in mthod body */
	//    JClass jClassavpImpl = jCodeModel.ref(com.somclass.AnyXYZ.class);
	//    jvarAvpImpl = jBlock.decl(jClassavpImpl, "varName");
	//    jvarAvpImpl.init(JExpr._new(jClassavpImpl));
	//
	//    
	//    /* Adding some direct statement */             
	//    jBlock.directStatement("varName.setCode(100);");
	//
	//    /* returning varibalbe */        
	//    jBlock._return(jvarAvpImpl);
	//
	//    /* Building class at given location */
	//    jCodeModel.build(new File("generated/src"));

	@Test
	public void testProcessing() throws Exception {
		DtoGeneratorModule module = new DtoGeneratorModule();
		module.process(new File("./src/test/java/org/charpy"),
				new File("./target/classes-dto"));
		
		listFilesForFolder(new File("./target/classes-dto"));
	}
	

	public void listFilesForFolder(final File folder) throws IOException {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				System.out.println(fileEntry.getName());
				for (String s : Files.readAllLines(fileEntry.toPath())) {
					System.out.println(s);
				}
			}
		}
	}

}
