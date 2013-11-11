package com.gmail.hmp5kdev.jos;

import static com.gmail.hmp5kdev.jos.JOSTokener.JOSFormat.DEFAULT;
import static com.gmail.hmp5kdev.jos.JOSTokener.JOSFormat.JAVA_SERIALIZATION;
import static com.gmail.hmp5kdev.jos.JOSTokener.JOSFormat.JOS;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;

import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.jos.tools.JOSBinder;
import com.gmail.hmp5kdev.src.jos.object.srcJOSCompound;
import com.gmail.hmp5kdev.src.jos.tokener.jos.JOSDefaultJOSTokener;
import com.gmail.hmp5kdev.src.jos.tokener.serialize.JavaSerJOSTokener;
import com.gmail.hmp5kdev.src.jos.tools.srcJOSBinder;

import static java.lang.System.*;

//TODO Documentation
public abstract class JOSTokener implements Cloneable {
	public static PrintStream logStream = System.out;
	
	private static HashMap<JOSFormat, JOSTokener> tokes = new HashMap<JOSFormat, JOSTokener>();

	static{
		try{
			setProperty("com.gmail.hmp5kdev.jos.name", "srcJOS");
			setProperty("com.gmail.hmp5kdev.jos.version", JOSObject.VERSION_SOURCE + "." + JOSObject.VERSION_FORMAT + "-spec" + JOSObject.VERSION_SPEC);
			setProperty("com.gmail.hmp5kdev.jos.classpath", "com.gmail.hmp5kdev.jos.*;com.gmail.hmp5kdev.src.jos.*;");
			setProperty("com.gmail.hmp5kdev.jos.tokener", "JOS_DEFAULT;JAVA_SERIALIZATION;");
			setProperty("com.gmail.hmp5kdev.jos.vendor", "Hannes103");
			setProperty("com.gmail.hmp5kdev.jos.vendor.url", "http://www.mcpluginsdev.com/");
		
		}catch(Throwable t){
			if(logStream != null){
				logStream.println("# JOSTokener<cinit>: Can't init Standard Proberties.");
			}
		};
		registerTokener(JOS, new JOSDefaultJOSTokener());
		registerTokener(JAVA_SERIALIZATION, new JavaSerJOSTokener());
		registerTokener(DEFAULT, tokes.get(JOS));
	}
	
	/**
	 * TODO: Make Current JOSTokeners useable for custom JOSObjects
	 */
	protected static final boolean registerTokener(JOSFormat format,  JOSTokener default_){
		if(tokes.get(format) != null && !DEFAULT.equals(format))
			return false;
		tokes.put(format, default_);
		return true;
	}
	
	public static final JOSTokener newTokener(){
		return newTokener(DEFAULT);
	}
	
	public static final JOSTokener newTokener(JOSFormat format) throws JOSTokenerNotFoundException{
		JOSTokener tokener = tokes.get(format);
		if(tokener == null){
			throw new JOSTokenerNotFoundException("Tokener <" + format + "> not found!", format);
		}
		try {
			return (JOSTokener) tokener.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new JOSTokenerNotFoundException("Error while Cloning: " + tokener.getName(), e,format);
		}
	}
	
	public static final JOSBinder newBinder(){
		return new srcJOSBinder();
	}
	
	public static final JOSCompound newCompound(){
		return new srcJOSCompound(null);
	}

	
	public abstract Lock getLock();
	
	public String getName(){
		return this.format().name() + ":" + this.version() + "@" + this.getClass().getName();
	}
	
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
	
	public static final class JOSFormat {
		/**
		 * TODO: Make Current JOSTokeners useable for custom JOSObjects
		 */
		protected static final JOSFormat get(String name){
			return new JOSFormat(name);
		}
		
		public static final JOSFormat DEFAULT = new JOSFormat("DEFAULT");
		
		//deprecated: only for Backwards compability
		@Deprecated() public static final JOSFormat JOS_MINI = new JOSFormat("deprecated-JOS_MINI");
		
		public static final JOSFormat JOS = new JOSFormat("JOS");
		public static final JOSFormat JAVA_SERIALIZATION = new JOSFormat("JAVA_SERIALIZATION");
		
		
		private final String name;
		private JOSFormat(String name){
			this.name = name.toUpperCase();
		}
		public final String name(){
			return name;
		}
		
		@Override
		public String toString() {
			return name;
		}
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof JOSFormat){
				return obj.toString().equalsIgnoreCase(this.name);
			}
			return false;
		}	
	};
	
	protected void log(String message){
		if(logStream != null){
			logStream.println("# " + this.getClass().getSimpleName()+  ": " + message);
		}
	}
}
