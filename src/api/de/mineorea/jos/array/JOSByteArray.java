package de.mineorea.jos.array;

//TODO Documentation
public interface JOSByteArray extends JOSArray<Byte , JOSByteArray>{

	public abstract byte[] getPrimitiveValue();
	public abstract byte[] setPrimitiveValue(byte[] value);
	
	public static final Class<?> NATIVE_TYPE = byte[].class;
	public static final Class<?> HOSTED_TYPE = Byte[].class;
	
	public static final byte CONST = 0x11;
	
}
