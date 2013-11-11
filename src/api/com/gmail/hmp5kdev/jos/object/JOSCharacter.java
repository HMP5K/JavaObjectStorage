package com.gmail.hmp5kdev.jos.object;

//TODO Documentation
public interface JOSCharacter extends JOSObject<Character , JOSCharacter> {

	public abstract char getPrimitiveValue();
	public abstract char setPrimitiveValue(char value);
	
	public static final Class<?> NATIVE_TYPE = char.class;
	public static final Class<?> HOSTED_TYPE = Character.class;

	
}
