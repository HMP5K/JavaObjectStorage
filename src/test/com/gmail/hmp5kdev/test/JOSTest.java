package com.gmail.hmp5kdev.test;

import java.io.File;

import com.gmail.hmp5kdev.jos.JOSTokener;
import com.gmail.hmp5kdev.jos.JOSTokener.JOSFormat;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.tools.JOSAccess;
import com.gmail.hmp5kdev.jos.tools.JOSAccessor;
import com.gmail.hmp5kdev.jos.tools.JOSBinder;

public class JOSTest {

	public static final void main(String[] args){
		JOSCompound com0 = JOSTokener.newCompound(); //create Compound
		com0.setString("name", "Hannes103");
		
		JOSBinder binder = JOSTokener.newBinder(); // create Binder
		User user = binder.createWrapper(com0, User.class); 

		System.out.println(user.getName());
		
		JOSTokener tokener = JOSTokener.newTokener(JOSFormat.JOS);
		tokener.save(com0, new File("test.txt"));
		
	}
	
	public static abstract interface User extends JOSAccessor {
		
		@JOSAccess(name = "name")
		public abstract String getName();
		
		@JOSAccess(name = "name")
		public abstract User setName(String name); //user or void as return type
	}
	
}
