package de.mineorea.jos.test;

import java.io.File;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.JOSTokener;
import de.mineorea.jos.JOSTokener.JOSFormat;
import de.mineorea.jos.object.JOSCompound;

public class Test {

	public static void main(String[] args) {
		JOSTokener tokener = JOSTokener.newTokener(JOSFormat.DEFAUTL); // create Tokener
			tokener.gzip(false).version((byte) 22).meta(true); // set Parameters
			
		JOSCompound comp = null;
			
		try{
			comp = tokener.load(new File("./test.jos")); // load
		}catch(JOSException e){
			e.printStackTrace();
			return;
		}
		comp.setByte("myByte", (byte) 15);
		
		tokener.save(comp, new File("./test.jos"));
	}

}
