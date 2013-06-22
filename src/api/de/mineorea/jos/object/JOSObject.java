package de.mineorea.jos.object;

import java.io.Serializable;

import de.mineorea.jos.JOSException;


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
	
	public static Class<?> NATIVE_TYPE = Object.class;
	public static Class<?> HOSTED_TYPE = Object.class;
	
}
