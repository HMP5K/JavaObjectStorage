package com.gmail.hmp5kdev.src.jos.tokener.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSTokener;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSObject;

public final class JavaSerJOSTokener extends JOSTokener {

	private Lock lock = new ReentrantLock();
	private boolean gzip;
	private byte version = JOSObject.VERSION_FORMAT;
	private boolean verbose;
	
	@Override
	public Lock getLock() {
		return lock;
	}

	@Override
	public byte version() {
		return version;
	}

	@Override
	public JOSTokener version(byte version) {
		this.version = version;
		return this;
	}

	@Override
	public boolean gzip() {
		return this.gzip;
	}

	@Override
	public JOSTokener gzip(boolean gzip) {
		this.gzip = gzip;
		return this;
	}

	@Override
	public boolean verbose() {
		return this.verbose;
	}

	@Override
	public JOSTokener verbose(boolean verbose) {
		this.verbose = verbose;
		return this;
	}

	@Override
	public boolean meta() {
		throw new JOSException("Not Supported!");
	}

	@Override
	public JOSTokener meta(boolean meta) {
		throw new JOSException("Not Supported!");
	}

	@Override
	public boolean intendention() {
		throw new JOSException("Not Supported!");
	}

	@Override
	public JOSTokener intendention(boolean intendention) {
		throw new JOSException("Not Supported!");
	}

	@Override
	public JOSFormat format() {
		return JOSFormat.JAVA_SERIALIZATION;
	}

	@Override
	public void save(JOSCompound data, File file) throws JOSException {
		try {
			this.save(data, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new JOSException("IO Failed!" , e);
		}
	}

	@Override
	public void save(JOSCompound data, OutputStream out1) throws JOSException {
		log("Saving <" + data.getName() + "> using: GZIP=" + this.gzip + " VERSION=" + this.version + " META=?");
		ObjectOutputStream out = null;
		try {
			if(this.gzip){
				out = new ObjectOutputStream(new GZIPOutputStream(out1));
			}else{
				out = new ObjectOutputStream(out1);
			}
			out.writeByte(version);
			out.writeObject(data);
			log("Done!");
		} catch (Exception e) {
			throw new JOSException("IO Failed!" , e);
		}finally{
			if(out != null)
				try {
					out.close();
				} catch (IOException e) {
					throw new JOSException("IO Failed!" , e);
				}
		}
	}
		


	@Override
	public JOSCompound load(File file) throws JOSException {
		try {
			return this.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new JOSException("IO Failed!" , e);
		}
	}

	@Override
	public JOSCompound load(InputStream in1) throws JOSException {
		ObjectInputStream in = null;
		
		try{
			if(this.gzip){
				in = new ObjectInputStream(new GZIPInputStream(in1));
			}else{
				in = new ObjectInputStream(in1);
			}
			if(this.version != in.readByte()){
				throw new JOSException("Version Conflict!");
			}
			
			return (JOSCompound) in.readObject();
			
		}catch(Exception e){
			throw new JOSException("IO Failed!" , e);
		}finally{
			if(in != null)
				try {
					in.close();
				} catch (IOException e) {
					throw new JOSException("IO Failed!" , e);
				}
		}
	}
	
	public void log(String message){
		if(this.verbose){
			super.log(message);
		}
	}

}
