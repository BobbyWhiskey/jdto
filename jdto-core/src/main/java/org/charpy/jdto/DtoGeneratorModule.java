package org.charpy.jdto;

import java.io.File;
import java.io.IOException;

import org.charpy.jdto.annotations.DtoField;
import org.charpy.jdto.annotations.GenerateDto;
import org.charpy.jdto.annotations.IncludeMethodToDTO;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaAnnotatedElement;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaSource;
import com.thoughtworks.qdox.model.impl.AbstractJavaEntity;

public class DtoGeneratorModule {

	// JCodeModel to generate the output source code
	private JCodeModel jcodeModel;
	private String generationToken = "DTO";
	private TokenPosition tokenPosition = TokenPosition.POSTFIX;

	public DtoGeneratorModule() throws Exception {
		jcodeModel = new JCodeModel();
	}

	/**
	 * Process all the java files recursively and generate the output source files in the output folder specified
	 * @param sourceFolder
	 * @param outputFolder
	 * @throws ClassNotFoundException
	 * @throws JClassAlreadyExistsException
	 * @throws IOException
	 */
	public void process(File sourceFolder, File outputFolder) throws ClassNotFoundException, JClassAlreadyExistsException, IOException {
		JavaProjectBuilder builder = new JavaProjectBuilder();
		builder.addSourceTree(sourceFolder);

		for (JavaSource js : builder.getSources()) {
			for (JavaClass javaClass : js.getClasses()) {
				processJavaClass(javaClass, outputFolder);
			}
		}
		outputFolder.mkdirs();
		jcodeModel.build(outputFolder);
	}

	/**
	 * Process a source java class and generate the output DTO file 
	 * @param jc
	 * @param outputFolder
	 * @throws JClassAlreadyExistsException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void processJavaClass(JavaClass jc, File outputFolder) throws JClassAlreadyExistsException, ClassNotFoundException, IOException {
		JavaAnnotation classAnnotation = getAnnotationFromEntity(GenerateDto.class, jc);
		if (classAnnotation != null) {
			
			// Iterate over fields and generate getter/setter for those with annotation DtoField
			DtoWriter writer = null;
			for (JavaField field : jc.getFields()) {
				JavaAnnotation fieldAnno = getAnnotationFromEntity(DtoField.class, field);
				if (fieldAnno != null) {
					if (writer == null) {
						if (tokenPosition == TokenPosition.POSTFIX) {
							writer = new DtoWriter(jcodeModel, jc.getName() + generationToken);
						} else {
							writer = new DtoWriter(jcodeModel, generationToken + jc.getName());
						}
					}

					boolean setter = true;
					boolean getter = true;

					Object setterObj = fieldAnno.getNamedParameter("setter");
					if (setterObj != null)
						setter = new Boolean(fieldAnno.getNamedParameter("setter").toString());
					
					Object getterObj = fieldAnno.getNamedParameter("getter");
					if (getterObj != null)
						getter = new Boolean(fieldAnno.getNamedParameter("getter").toString());

					writer.addField(jcodeModel.parseType(field.getType().getGenericFullyQualifiedName()), field.getName(), getter, setter);
				}
			}
			
			// Lets see which method need to be included
			for(JavaMethod m : jc.getMethods()){
				JavaAnnotation methodAnno = getAnnotationFromEntity(IncludeMethodToDTO.class, m);
				if(methodAnno != null){
					writer.addMethod(m);
				}
			}
			
			// Write dto to disk
			if (writer != null) {
				writer.build(outputFolder);
			}
		}
	}

	private JavaAnnotation getAnnotationFromEntity(Class<?> class1, JavaAnnotatedElement e) {
		for (JavaAnnotation annotation : e.getAnnotations()) {
			if (annotation.getType().getFullyQualifiedName().equals(class1.getName())) {
				return annotation;
			}
		}
		return null;
	}

}
