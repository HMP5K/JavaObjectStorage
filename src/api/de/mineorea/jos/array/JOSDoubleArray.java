package de.mineorea.jos.array;

//TODO Documentation
public interface JOSDoubleArray extends JOSArray<Double , JOSDoubleArray>{

	public abstract double[] getPrimitiveValue();
	public abstract double[] setPrimitiveValue(double[] value);
	
	public static final Class<?> NATIVE_TYPE = double[].class;
	public static final Class<?> HOSTED_TYPE = Double[].class;
	
	public static final byte CONST = 0x16;
	
}
