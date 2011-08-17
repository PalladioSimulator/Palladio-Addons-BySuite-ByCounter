package de.uka.ipd.sdq.ByCounter.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import org.junit.Test;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.test.helpers.ClassLoadTime2;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

public class TestClassLoadTime {
	
	public static void main(String[] args) {
		try {
			new TestClassLoadTime().testClassLoadTime();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This instruments a method and writes the 
	 * instrumented .class file. 
	 * @throws IOException When writing fails.
	 */
	@Test
	public void testClassLoadTime() throws IOException {
		//1. Set up a BytecodeCounter instance to use ByCounter, using a parameterless constructor. 
		BytecodeCounter counter = new BytecodeCounter();

		//2. Specify the method to be instrumented (several methods are supported as well)
		String className = ClassLoadTime2.class.getCanonicalName();
		MethodDescriptor myMethod = new MethodDescriptor(
				className,
				"public static void main(java.lang.String[] args)"); //$NON-NLS-1$
		
		
		//3. now tell ByCounter to instrument the specified method
		counter.instrument(myMethod);
		

		byte[] bytes = counter.getInstrumentedBytes();
		
		File file = new File("instrumented_" + className + ".class");
		FileOutputStream fos = new FileOutputStream(
				file);
		fos.write(bytes);
		Logger.getAnonymousLogger().info("Wrote " + file.getAbsolutePath());
		fos.close();
	}
}
