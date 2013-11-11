package com.gmail.hmp5kdev.jos;

import com.gmail.hmp5kdev.jos.JOSTokener.JOSFormat;

// TODO Documentation
public final class JOSTokenerNotFoundException extends JOSException {

	private static final long serialVersionUID = -1256086304294386994L;
	private JOSFormat format;
	
	public JOSTokenerNotFoundException(String message , JOSFormat format){
		super(message , null , null);
	}
	
	public JOSTokenerNotFoundException(String message , Throwable t , JOSFormat format){
		super(message , t ,null);
		this.format = format;
	}
	
	public JOSFormat getFormat(){
		return format;
	}
	
}
