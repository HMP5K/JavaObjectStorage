package com.gmail.hmp5kdev.src.jos.array;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
import com.gmail.hmp5kdev.jos.array.JOSStringArray;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.jos.object.JOSString;
import com.gmail.hmp5kdev.src.jos.object.srcJOSCompound;

import static com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst.*;

public final class srcJOSStringArray implements JOSStringArray {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private String[] value;
	
	public srcJOSStringArray(srcJOSCompound comp , String[] value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public String set(int index, String value) {
		String old = this.value[index];
		this.value[index] = value;
		return old;
	}

	@Override
	public String get(int index) {
		return this.value[index];
	}

	@Override
	public int lenght() {
		return this.value.length;
	}

	@Override
	public void clear() {
		this.value = new String[value.length];
	}

	@Override
	public JOSCompound getCompound() {
		return this.comp;
	}

	@Override
	public String[] getValue() {
		return this.value;
	}

	@Override
	public String[] setValue(String[] value) {
		String[] old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSStringArray copy() throws JOSException {
		return new srcJOSStringArray(comp , value);
	}

	@Override
	public void fill(JOSObject<String[], JOSStringArray> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return STRING_ARRAY_NAME;
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!", this);
	}

	@Override
	public int getEncoding() {
		return JOSString.UTF8;
	}
	
	@Override()
	public String toString(){
		return JOSUtils.toString(this);
	}

	@Override
	public Class<?> getNativeType() {
		return NATIVE_TYPE;
	}

	@Override
	public Class<?> getHostedType() {
		return HOSTED_TYPE;
	}
	
}
