package de.mineorea.jos;

import de.mineorea.jos.object.JOSObject;

public class JOSException extends RuntimeException {
	
	private static final long serialVersionUID = -1950079013673296616L;
	
	private String reason;
	private Throwable parent;
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
		this.reason = reason;
		this.parent = parent;
		this.object = object;
	}

	@Override
	public String getMessage() {
		return reason;
	}

	@Override
	public synchronized Throwable getCause() {
		return parent;
	}

	@Override
	public synchronized Throwable initCause(Throwable cause) {
		this.parent = cause;
		return cause;
	}
	
	public JOSObject<? , ?> setObject(JOSObject<? , ?> obj){
		this.object = obj;
		return object;
	}
	
	public JOSObject<? , ?> getObject(){
		return object;
	}
}