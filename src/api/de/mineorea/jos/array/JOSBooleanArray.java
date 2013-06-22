package de.mineorea.jos.array;

public interface JOSBooleanArray extends JOSArray<Integer , JOSBooleanArray>{

	public abstract boolean[] getPrimitiveValue();
	public abstract boolean[] setPrimitiveValue(boolean[] value);
	
	public static final Class<?> NATIVE_TYPE = boolean[].class;
	public static final Class<?> HOSTED_TYPE = Boolean[].class;
	
	public static final byte CONST = 0x17;
	
}

