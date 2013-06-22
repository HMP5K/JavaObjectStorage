package de.mineorea.jos;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import de.mineorea.jos.object.JOSCompound;

public abstract class JOSTokener {
	
	public static JOSTokener newTokener(JOSFormat format) throws JOSTokenerNotFoundException{
		return null; //TODO de.mineorea.src.jos.cJOSTokener
	}
	
	public abstract String getName();
	
	public abstract byte version();
	public abstract JOSTokener version(byte version);
	
	public abstract boolean gzpi();
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
		DEFAUTL,
		MINIFIED,
		MAXIMAL,
		BINARY,
		
		__PROTOTYPE_0x001, //XML
		__PROTOTYPE_0x002, //JSON
		__PROTOTYPE_0x003, //INI
		__PROTOTYPE_0x004, //Java Serialization
		__PROTOTYPE_0x005, //BINARY with Network Optimization
		;
		JOSFormat(){
			
		}
	};
}
