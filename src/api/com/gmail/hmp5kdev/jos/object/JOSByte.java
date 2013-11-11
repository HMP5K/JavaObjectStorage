package com.gmail.hmp5kdev.jos.object;

//TODO Documentation
public interface JOSByte extends JOSObject<Byte , JOSByte>{

	public abstract byte getPrimitiveValue();
	public abstract byte setPrimitiveValue(byte b);
	
	public static final Class<?> NATIVE_TYPE = byte.class;
	public static final Class<?> HOSTED_TYPE = Byte.class;

	
}
