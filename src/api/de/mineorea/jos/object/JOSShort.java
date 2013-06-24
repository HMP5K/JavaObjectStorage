package de.mineorea.jos.object;



public interface JOSShort extends JOSObject<Short , JOSShort> {

	public abstract short getPrimitiveValue();
	public abstract short setPrimitiveValue(short s);
	
	public static final Class<?> NATIVE_TYPE = short.class;
	public static final Class<?> HOSTED_TYPE = Short.class;
	
	public static final byte CONST = 0x02;
	
	
}
