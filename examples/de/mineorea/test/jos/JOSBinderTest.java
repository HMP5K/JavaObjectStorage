package de.mineorea.test.jos;

import de.mineorea.jos.JOSTokener;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.tools.JOSBinder;

public class JOSBinderTest {


	public static void main(String[] args) throws InterruptedException {
		JOSCompound com = JOSTokener.newCompound(); // create Compound to store the Data in
		JOSBinder binder = JOSTokener.newBinder(); // get the a JOSBinder instance
		
		User user = binder.createWrapper(com, User.class); // create our Wrapper Class
		user.setLevel(10L);
		System.out.println(user.getClass().getName());
		
	}

}
