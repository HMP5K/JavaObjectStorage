package com.gmail.hmp5kdev.jos.object;

//TODO Documentation
public interface JOSLong extends JOSObject<Long ,  JOSLong>{

	public abstract long getPrimitiveValue();
	public abstract long setPrimitiveValue(long value);
	
	public static final Class<?> NATIVE_TYPE = long.class;
	public static final Class<?> HOSTED_TYPE = Long.class;
	
}
