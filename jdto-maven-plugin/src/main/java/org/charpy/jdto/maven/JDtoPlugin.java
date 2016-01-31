package org.charpy.jdto.maven;

import java.io.File;
import java.util.Collection;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.charpy.jdto.DtoGeneratorModule;
import org.charpy.jdto.TokenPosition;

/**
 * Start jDTO engine
 * @goal generate-dto
 * @phase package
 */
public class JDtoPlugin extends AbstractMojo {

	/**
	 * @parameter 
	 */
	String[] sourceDirs;
	/**
	 * @parameter
	 */
	String outputDir;

	/**
	 * @parameter
	 */
	String generationToken;

	/**
	 * @parameter
	 */
	private TokenPosition tokenPosition;

	public void execute() throws MojoExecutionException, MojoFailureException {
		
		System.out.println("output dir" + outputDir);
		// TODO Add some validation for params 
		for (String src : sourceDirs) {
			try {
				DtoGeneratorModule module = new DtoGeneratorModule();
				module.process(new File(src), new File(outputDir));
			} catch (Exception e) {
				throw new MojoExecutionException("Execution error" , e);
			}
		}

	}

}
