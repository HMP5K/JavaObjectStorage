package com.gmail.hmp5kdev.jos.object;


//TODO Documentation
public interface JOSInteger extends JOSObject<Integer , JOSInteger> {

	public abstract int getPrimitiveValue();
	public abstract int setPrimitiveValue(int value);
	
	public static final Class<?> NATIVE_TYPE = int.class;
	public static final Class<?> HOSTED_TYPE = Integer.class;
	
}
