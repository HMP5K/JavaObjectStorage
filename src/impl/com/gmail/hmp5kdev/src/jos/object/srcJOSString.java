package com.gmail.hmp5kdev.src.jos.object;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.jos.object.JOSString;
import com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst;

public final class srcJOSString implements JOSString {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private String value;
	
	public srcJOSString(srcJOSCompound comp , String value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String setValue(String value) {
		String old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSString copy() throws JOSException {
		return new srcJOSString(comp , value);
	}

	@Override
	public void fill(JOSObject<String, JOSString> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSConst.STRING_NAME;
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public char[] getChars() {
		if(value == null) return new char[]{'\0'};
		return this.value.toCharArray();
	}

	@Override
	public char[] setChars(char[] chars) {
		char[] old = this.getChars();
		this.value = new String(chars);
		return old;
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
