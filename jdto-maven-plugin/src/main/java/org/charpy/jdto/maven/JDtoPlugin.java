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
	TokenPosition tokenPosition;

	/**
	 * @parameter
	 */
	String outputPackage;

	public void execute() throws MojoExecutionException, MojoFailureException {
		System.out.println("output dir" + outputDir);
		// TODO Add some validation for params 
		for (String src : sourceDirs) {
			try {
				DtoGeneratorModule module = new DtoGeneratorModule();

				if (generationToken != null)
					module.setGenerationToken(generationToken);
				if (tokenPosition != null)
					module.setTokenPosition(tokenPosition);

				module.process(new File(src), new File(outputDir), outputPackage);
			} catch (Exception e) {
				throw new MojoExecutionException("Execution error", e);
			}
		}
	}

}
