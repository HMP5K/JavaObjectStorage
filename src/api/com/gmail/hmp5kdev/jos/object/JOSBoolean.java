package com.gmail.hmp5kdev.jos.object;

//TODO Documentation
public interface JOSBoolean extends JOSObject<Boolean, JOSBoolean> {

	public abstract boolean getPrimitiveValue();
	public abstract boolean setPrimitiveValue(boolean value);
	
	public static final Class<?> NATIVE_TYPE = boolean.class;
	public static final Class<?> HOSTED_TYPE = Boolean.class;
	
}
