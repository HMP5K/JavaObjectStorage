package de.mineorea.jos.object;

import java.io.Serializable;

import de.mineorea.jos.JOSException;

//TODO Documentation
public interface JOSObject<Native extends Object , Hosted extends JOSObject<Native , Hosted>> extends Serializable {
	
	public abstract JOSCompound getCompound();
	
	public abstract Native getValue();
	public abstract Native setValue(Native value);
	
	public abstract Hosted copy() throws JOSException;
	public abstract void fill(JOSObject<Native , Hosted> hosted) throws JOSException;
	
	public abstract String getName();
	
	public abstract int hashCode();
	public abstract String toString();
	
	public abstract byte[] asArray() throws JOSException;
	
	public static final Class<?> NATIVE_TYPE = Object.class;
	public static final Class<?> HOSTED_TYPE = Object.class;

	public static final byte CONST = 0x00;
	
	public static final byte VERSION_SPEC = 14;
	public static final byte VERSION_SOURCE = 14;
	public static final byte VERSION_FORMAT = 12;
	
}
