package com.gmail.hmp5kdev.jos.array;

//TODO Documentation
public interface JOSIntegerArray extends JOSArray<Integer , JOSIntegerArray>{

	public abstract int[] getPrimitiveValue();
	public abstract int[] setPrimitiveValue(int[] value);
	
	public static final Class<?> NATIVE_TYPE = int[].class;
	public static final Class<?> HOSTED_TYPE = Integer[].class;
		
}
