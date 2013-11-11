package com.gmail.hmp5kdev.jos;

import com.gmail.hmp5kdev.jos.object.JOSObject;

//TODO Documentation
public class JOSException extends RuntimeException {
	
	private static final long serialVersionUID = -1950079013673296616L;
	
	private JOSObject<? , ?> object;
	
	public JOSException(){
		this(null , null , null);
	}
	
	public JOSException(String reason){
		this(reason , null , null);
	}
	
	public JOSException(String reason , Throwable parent){
		this(reason, parent , null);
	}
	
	public JOSException(String reason , JOSObject<? , ?> object){
		this(reason , null , object);
	}
	
	public JOSException(String reason , Throwable parent , JOSObject<? , ?> object){
		this(reason, parent, object, true, true);
	}

	
	JOSException(String reason, Throwable parent, JOSObject<?, ?> object, boolean enableSuppressed , boolean writeableTrace){
		super(reason, parent, enableSuppressed, writeableTrace);
		this.object = object;
	
	}
	
	public synchronized JOSException setObject(JOSObject<? , ?> obj){
		this.object = obj;
		return this;
	}
	
	@Override
	public synchronized JOSException initCause(Throwable cause) {
		super.initCause(cause);
		return this;
	}

	@Override
	public synchronized JOSException fillInStackTrace() {
		super.fillInStackTrace();
		return this;
	}


	public synchronized JOSObject<? , ?> getObject(){
		return object;
	}
}