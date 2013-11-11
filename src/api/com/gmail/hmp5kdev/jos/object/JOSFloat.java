package com.gmail.hmp5kdev.jos.object;

//TODO Documentation
public interface JOSFloat extends JOSObject<Float , JOSFloat> {

	public abstract float getPrimitiveValue();
	public abstract float setPrimitiveValue(float value);
	
	public static final Class<?> NATIVE_TYPE = float.class;
	public static final Class<?> HOSTED_TYPE = Float.class;
	
}
