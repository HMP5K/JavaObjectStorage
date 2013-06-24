package de.mineorea.src.jos;

import java.io.File;

import de.mineorea.jos.JOSTokener;
import de.mineorea.jos.JOSTokener.JOSFormat;
import de.mineorea.jos.object.JOSCompound;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JOSTokener tokener = JOSTokener.newTokener(JOSFormat.JOS);
		JOSCompound comp = JOSTokener.newCompound();
		
		comp.setStringArray("test", "My, Value!" , "Hannes");
		tokener.save(comp, new File("test.jos"));
		comp = tokener.load(new File("test.jos"));
		System.out.println(comp.getStringArray("test").get(1));

	}

}
