package com.gmail.hmp5kdev.jos.array;

import com.gmail.hmp5kdev.jos.object.JOSObject;

//TODO Documentation
public interface JOSArray<Native , Hosted extends JOSArray<Native , Hosted>> extends JOSObject<Native[] , Hosted> {

	public Native set(int index , Native value);
	public Native get(int index);
	
	public int lenght();
	
	public void clear();
	
}
