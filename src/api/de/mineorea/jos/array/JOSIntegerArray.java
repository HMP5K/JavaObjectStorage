package de.mineorea.jos.array;

public interface JOSIntegerArray extends JOSArray<Integer , JOSIntegerArray>{

	public abstract int[] getPrimitiveValue();
	public abstract int[] setPrimitiveValue(int[] value);
	
	public static final Class<?> NATIVE_TYPE = int[].class;
	public static final Class<?> HOSTED_TYPE = Integer[].class;
	
	public static final byte CONST = 0x13;
	
}
