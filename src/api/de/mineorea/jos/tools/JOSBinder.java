package de.mineorea.jos.tools;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.object.JOSCompound;

public interface JOSBinder {

	public abstract <T> T createWrapper(JOSCompound comp, Class<T> Inteface) throws JOSException;
	public abstract <T> T createWrapperWithLogger(JOSCompound comp , Class<T> Interface) throws JOSException;
	public abstract boolean isWrapper(Object o);
	
}
