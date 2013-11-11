package com.gmail.hmp5kdev.jos.array;

//TODO Documentation
public interface JOSShortArray extends JOSArray<Short , JOSShortArray>{

	public abstract short[] getPrimitiveValue();
	public abstract short[] setPrimitiveValue(short[] value);
	
	public static final Class<?> NATIVE_TYPE = short[].class;
	public static final Class<?> HOSTED_TYPE = Short[].class;
	
}
