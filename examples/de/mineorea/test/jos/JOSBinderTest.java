package de.mineorea.test.jos;

import de.mineorea.jos.JOSTokener;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.tools.JOSBinder;

public class JOSBinderTest {

	public static void main(String[] args) {
		JOSCompound compound = JOSTokener.newCompound(); // create Compound to store the Data in
		JOSBinder binder = JOSTokener.newBinder(); // get the a JOSBinder instance
		
		User user = binder.createWrapper(compound, User.class); // create our Wrapper Class
		
		user.setLevel(10L); // set the Users Level to 10
		
		System.out.println("User Level: " + user.getLevel()); // get Level by Wrapper
		System.out.println("Compound Level: " + compound.getLong("level").getValue()); // get Level by Compound
	}

}
