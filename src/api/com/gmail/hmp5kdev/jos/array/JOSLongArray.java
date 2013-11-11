package com.gmail.hmp5kdev.jos.array;

//TODO Documentation
public interface JOSLongArray extends JOSArray<Long , JOSLongArray>{

	public abstract long[] getPrimitiveValue();
	public abstract long[] setPrimitiveValue(long[] value);
	
	public static final Class<?> NATIVE_TYPE = long[].class;
	public static final Class<?> HOSTED_TYPE = Long[].class;
	
}
