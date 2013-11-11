package com.gmail.hmp5kdev.jos.tools;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.object.JOSCompound;

public interface JOSBinder {
	
	public abstract <T extends JOSAccessor> T createWrapper(JOSCompound comp, Class<T> Inteface) throws JOSException;
	public abstract <T extends JOSAccessor> T createWrapperWithLogger(JOSCompound comp , Class<T> Interface) throws JOSException;
	public abstract boolean isWrapper(Object o);
	
}
