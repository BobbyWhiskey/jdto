package org.charpy.jdto;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JType;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;

public class DtoWriter {

	// TODO Test with cm.directClass(name) every where, that might mitigate ClassNotFound errors
	JCodeModel cm;
	JDefinedClass dc;

	public DtoWriter(JCodeModel cm, String className) throws JClassAlreadyExistsException {
		dc = cm._class(className);
		dc.javadoc().add("<b>WARNING : AUTO GENERATED FILE</b>");
		this.cm = cm;
		
	}

	public void addField(JType javaType, String name, boolean getter, boolean setter) {
		JFieldVar field = dc.field(1, javaType, name);

		if (getter) {
			JMethod m = dc.method(0, javaType, "get" + StringUtils.capitalize(name) );
			m.body()._return(field);
		}

		if (setter) {
			JMethod m = dc.method(0, javaType, "set" + StringUtils.capitalize(name) );
			// Set the param
			m.param(javaType, name);

			m.body().directStatement("this." + name + " = " + name+ ";");
		}
	}

	public void build(File file) throws IOException {
		// write to disk
		file.mkdirs();
		cm.build(file);
	}

	public void addMethod(JavaMethod sourceMethod) throws ClassNotFoundException {
		JMethod method = dc.method(1, cm.parseType(sourceMethod.getReturnType().getGenericFullyQualifiedName()), sourceMethod.getName());
		// Copy exception thrown
		for (JavaClass jc : sourceMethod.getExceptions()) {
			method._throws(cm.directClass(jc.getFullyQualifiedName()));
		}
		
		// Copy parameters
		for (JavaParameter jp : sourceMethod.getParameters()) {
			method.param(cm.parseType(jp.getType().getGenericFullyQualifiedName()), jp.getName());
		}
		
		// copy source code
		method.body().directStatement(sourceMethod.getSourceCode());
	}
}
