package com.gmail.hmp5kdev.jos.object;

//TODO Documentation
public interface JOSDouble extends JOSObject<Double, JOSDouble> {

	public abstract double getPrimitiveValue();
	public abstract double setPrimitiveValue(double value);
	
	public static final Class<?> NATIVE_TYPE = double.class;
	public static final Class<?> HOSTED_TYPE = Double.class;
	
}
