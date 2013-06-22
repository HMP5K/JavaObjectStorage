package de.mineorea.jos.array;

public interface JOSFloatArray extends JOSArray<Float , JOSFloatArray>{

	public abstract float[] getPrimitiveValue();
	public abstract float[] setPrimitiveValue(float[] value);
	
	public static final Class<?> NATIVE_TYPE = float[].class;
	public static final Class<?> HOSTED_TYPE = Float[].class;
	
	public static final byte CONST = 0x15;
	
}
