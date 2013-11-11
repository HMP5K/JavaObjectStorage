package com.gmail.hmp5kdev.jos.array;

//TODO Documentation
public interface JOSFloatArray extends JOSArray<Float , JOSFloatArray>{

	public abstract float[] getPrimitiveValue();
	public abstract float[] setPrimitiveValue(float[] value);
	
	public static final Class<?> NATIVE_TYPE = float[].class;
	public static final Class<?> HOSTED_TYPE = Float[].class;

}

