package org.charpy.jdto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;


public class DtoGeneratorModuleTestCase {

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
