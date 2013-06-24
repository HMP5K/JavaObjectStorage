package de.mineorea.jos;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.locks.Lock;

import de.mineorea.jos.object.JOSCompound;
import de.mineorea.src.jos.object.srcJOSCompound;
import de.mineorea.src.jos.tokener.JOSDefaultJOSTokener;
import de.mineorea.src.jos.tokener.JavaSerJOSTokener;

public abstract class JOSTokener {
	
	public static JOSTokener newTokener(JOSFormat format) throws JOSTokenerNotFoundException{
		switch(format){
		case JAVA_SERIALIZATION:
			return new JavaSerJOSTokener();
		case JOS:
			return new JOSDefaultJOSTokener();
		default:
			throw new JOSTokenerNotFoundException("JOSTokener not found!" , format);
		}
	}
	
	public static JOSCompound newCompound(){
		return new srcJOSCompound(null);
	}
	
	public abstract Lock getLock();
	
	public abstract String getName();
	
	public abstract byte version();
	public abstract JOSTokener version(byte version);
	
	public abstract boolean gzip();
	public abstract JOSTokener gzip(boolean gzip);
	
	public abstract boolean verbose();
	public abstract JOSTokener verbose(boolean verbose);
	
	public abstract boolean meta();
	public abstract JOSTokener meta(boolean meta);
	
	public abstract boolean intendention();
	public abstract JOSTokener intendention(boolean intendention);
	
	public abstract JOSFormat format();
	
	public abstract void save(JOSCompound data , File file) throws JOSException;
	public abstract void save(JOSCompound data , OutputStream out) throws JOSException;
	
	public abstract JOSCompound load(File file) throws JOSException;
	public abstract JOSCompound load(InputStream in) throws JOSException;
	
	public static enum JOSFormat
	{
		JAVA_SERIALIZATION,
		JOS
		// TODO Formats: JOS, MJOS, BINARY
		;
		JOSFormat(){
			
		}
	};
}
