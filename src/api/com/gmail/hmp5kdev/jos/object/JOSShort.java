package com.gmail.hmp5kdev.jos.object;

//TODO Documentation
public interface JOSShort extends JOSObject<Short , JOSShort> {

	public abstract short getPrimitiveValue();
	public abstract short setPrimitiveValue(short s);
	
	public static final Class<?> NATIVE_TYPE = short.class;
	public static final Class<?> HOSTED_TYPE = Short.class;
	
}
