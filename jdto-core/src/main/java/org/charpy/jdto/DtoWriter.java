package org.charpy.jdto;

import java.io.File;
import java.io.IOException;

import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JType;
import com.thoughtworks.qdox.model.JavaType;

public class DtoWriter {

	JCodeModel cm;
	JDefinedClass dc;

	public DtoWriter(JCodeModel cm, String className) throws JClassAlreadyExistsException {
		dc = cm._class(className);
		this.cm = cm;
	}

	public void addField(JType javaType, String name, boolean getter, boolean setter) {
		JFieldVar field = dc.field(1, javaType, name);

		if (getter) {
			// TODO capitalizeName
			JMethod m = dc.method(0, javaType, "get" + name);
			m.body()._return(field);
		}

		if (setter) {
			JMethod m = dc.method(0, javaType, "set" + name);
			// Set the param
			m.param(javaType, name);

			m.body().directStatement("this." + name + " = " + name);
		}
	}

	public void build(File file) throws IOException {
		// write to disk
		file.mkdirs();
		cm.build(file);
	}

	public void addField(Class<?> javaType, String name, boolean getter, boolean setter) {
		JFieldVar field = dc.field(1, javaType, name);

		if (getter) {
			// TODO capitalizeName
			JMethod m = dc.method(0, javaType, "get" + name);
			m.body()._return(field);
		}

		if (setter) {
			JMethod m = dc.method(0, javaType, "set" + name);
			// Set the param
			m.param(javaType, name);

			m.body().directStatement("this." + name + " = " + name);
		}
	}
}
