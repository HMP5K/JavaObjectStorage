package de.mineorea.jos.object;



public interface JOSInteger extends JOSObject<Integer , JOSInteger> {

	public abstract int getPrimitiveValue();
	public abstract int setPrimitiveValue(int value);
	
	public static final Class<?> NATIVE_TYPE = int.class;
	public static final Class<?> HOSTED_TYPE = Integer.class;
	
	public static final byte CONST = 0x03;
	
}
