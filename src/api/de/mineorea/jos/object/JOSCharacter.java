package de.mineorea.jos.object;


public interface JOSCharacter extends JOSObject<Character , JOSCharacter> {

	public abstract char getPrimitiveValue();
	public abstract char setPrimitiveValue(char value);
	
	public static final Class<?> NATIVE_TYPE = char.class;
	public static final Class<?> HOSTED_TYPE = Character.class;
	
	public static final byte CONST = 0x08;
	
}
