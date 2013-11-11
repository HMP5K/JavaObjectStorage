package com.gmail.hmp5kdev.jos.array;

//TODO Documentation
public interface JOSBooleanArray extends JOSArray<Boolean , JOSBooleanArray>{

	public abstract boolean[] getPrimitiveValue();
	public abstract boolean[] setPrimitiveValue(boolean[] value);
	
	public static final Class<?> NATIVE_TYPE = boolean[].class;
	public static final Class<?> HOSTED_TYPE = Boolean[].class;
	
	
}

