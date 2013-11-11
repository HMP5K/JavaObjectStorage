package com.gmail.hmp5kdev.jos.object;

import java.io.Serializable;

import com.gmail.hmp5kdev.jos.JOSException;

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
	
	public abstract Class<?> getNativeType();
	public abstract Class<?> getHostedType();
	
	public static final Class<?> NATIVE_TYPE = Object.class;
	public static final Class<?> HOSTED_TYPE = Object.class;
	
	public static final byte VERSION_SPEC = 14;
	public static final byte VERSION_SOURCE = 16;
	public static final byte VERSION_FORMAT = 12;
}