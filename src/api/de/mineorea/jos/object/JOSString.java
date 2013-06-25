package de.mineorea.jos.object;

//TODO Documentation
public interface JOSString extends JOSObject<String , JOSString> {

	public abstract char[] getChars();
	public abstract char[] setChars(char[] chars);
	
	public abstract int getEncoding();
	
	public static final Class<?> NATIVE_TYPE = char[].class;
	public static final Class<?> HOSTED_TYPE = String.class;
	
	public static final byte CONST = 0x09;
	
	public static final int UTF8 = 0;
	public static final int ASCII = 2;
	public static final int BIG_ENDIAN = 4;
	public static final int LITTLE_ENDIAN = 6;
	public static final int PACKED = 8;
	public static final int PLAIN_JAVA = 10;
	
}
