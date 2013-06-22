package de.mineorea.jos.object;



public interface JOSByte extends JOSObject<Byte , JOSByte>{

	public abstract byte getPrimitiveValue();
	public abstract byte setPrimitiveValue(byte b);
	
	public static final Class<?> NATIVE_TYPE = byte.class;
	public static final Class<?> HOSTED_TYPE = Byte.class;
	
	public static final byte CONST = 0x01;
	
}
