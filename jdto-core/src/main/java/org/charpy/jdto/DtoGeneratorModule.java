package org.charpy.jdto;

import java.io.File;
import java.io.IOException;

import org.charpy.jdto.annotations.DtoField;
import org.charpy.jdto.annotations.GenerateDto;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JType;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaSource;

public class DtoGeneratorModule {

	// JCodeModel to generate the output source code
	private JCodeModel jcodeModel;
	private String generationToken = "DTO";
	private TokenPosition tokenPosition = TokenPosition.POSTFIX;

	public DtoGeneratorModule() throws Exception {
		jcodeModel = new JCodeModel();
	}

	public void process(File sourceFolder, File outputFolder) throws ClassNotFoundException, JClassAlreadyExistsException, IOException {
		JavaProjectBuilder builder = new JavaProjectBuilder();
		builder.addSourceTree(sourceFolder);

		for (JavaSource js : builder.getSources()) {
			for (JavaClass javaClass : js.getClasses()) {
				processJavaClass(javaClass, outputFolder);
				JavaPackage pkg = javaClass.getPackage();
				String name = javaClass.getName();
				String toString = pkg.toString();
				System.out.println("pkg name : " + name);
				System.out.println("pkg to String : " + toString);
			}
		}
		outputFolder.mkdirs();
		jcodeModel.build(outputFolder);
	}

	public JavaAnnotation getAnnotationFromClass(Class<?> ja, JavaClass jc) {
		for (JavaAnnotation annotation : jc.getAnnotations()) {
			if (annotation.getType().getFullyQualifiedName().equals(ja.getName())) {
				return annotation;
			}
		}
		return null;
	}

	public void processJavaClass(JavaClass jc, File outputFolder) throws JClassAlreadyExistsException, ClassNotFoundException, IOException {
		JavaAnnotation classAnnotation = getAnnotationFromClass(GenerateDto.class, jc);
		if (classAnnotation != null) {
			DtoWriter writer = null;
			for (JavaField field : jc.getFields()) {
				if (getAnnotationFromField(DtoField.class, field) != null) {
					if (writer == null)
						if (tokenPosition == TokenPosition.POSTFIX) {
							writer = new DtoWriter(jcodeModel, jc.getName() + generationToken);
						} else {
							writer = new DtoWriter(jcodeModel, generationToken + jc.getName());
						}
					try {
						// this throws exception if its a primitive
						//writer.addField(JType.parse(jcodeModel, field.getType().getGenericFullyQualifiedName()), field.getName(), true, true);
						
						writer.addField(jcodeModel.parseType(field.getType().getGenericFullyQualifiedName()),  field.getName(), true, true);
					} catch (IllegalArgumentException e) {
						writer.addField(Class.forName(field.getType().getGenericFullyQualifiedName()), field.getName(), true, true);
					}
				}
			}
			if (writer != null) {
				writer.build(outputFolder);
			}
		}
	}

	private Object getAnnotationFromField(Class<?> class1, JavaField jc) {
		for (JavaAnnotation annotation : jc.getAnnotations()) {
			if (annotation.getType().getFullyQualifiedName().equals(class1.getName())) {
				return annotation;
			}
		}
		return null;
	}
}
