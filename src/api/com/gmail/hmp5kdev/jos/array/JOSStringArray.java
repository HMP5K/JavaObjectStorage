package com.gmail.hmp5kdev.jos.array;

//TODO Documentation
public interface JOSStringArray extends JOSArray<String , JOSStringArray>{
	
	public abstract int getEncoding();
	
	public static final Class<?> NATIVE_TYPE = char[][].class;
	public static final Class<?> HOSTED_TYPE = String[].class;
	
	public static final int UTF8 = 0;
	public static final int ASCII = 2;
	public static final int BIG_ENDIAN = 4;
	public static final int LITTLE_ENDIAN = 6;
	public static final int PACKED = 8;
	public static final int PLAIN_JAVA = 10;
	
}
