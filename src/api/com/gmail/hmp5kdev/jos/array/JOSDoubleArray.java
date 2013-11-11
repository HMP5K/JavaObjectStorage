package com.gmail.hmp5kdev.jos.array;

//TODO Documentation
public interface JOSDoubleArray extends JOSArray<Double , JOSDoubleArray>{

	public abstract double[] getPrimitiveValue();
	public abstract double[] setPrimitiveValue(double[] value);
	
	public static final Class<?> NATIVE_TYPE = double[].class;
	public static final Class<?> HOSTED_TYPE = Double[].class;

}
