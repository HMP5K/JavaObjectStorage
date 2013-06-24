package de.mineorea.jos.array;

public interface JOSShortArray extends JOSArray<Short , JOSShortArray>{

	public abstract short[] getPrimitiveValue();
	public abstract short[] setPrimitiveValue(short[] value);
	
	public static final Class<?> NATIVE_TYPE = short[].class;
	public static final Class<?> HOSTED_TYPE = Short[].class;
	
	public static final byte CONST = 0x12;
	
}
