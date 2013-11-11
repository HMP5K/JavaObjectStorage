package com.gmail.hmp5kdev.src.jos.object;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.jos.object.JOSShort;
import com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst;

public final class srcJOSShort implements JOSShort {
	private static final long serialVersionUID = 1L;
	
	private srcJOSCompound comp;
	private Short value;
	
	public srcJOSShort(srcJOSCompound comp , Short value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Short getValue() {
		return value;
	}

	@Override
	public Short setValue(Short value) {
		Short old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSShort copy() throws JOSException {
		return new srcJOSShort(comp , value);
	}

	@Override
	public void fill(JOSObject<Short, JOSShort> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSConst.SHORT_NAME;
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public short getPrimitiveValue() {
		if(this.value == null) return 0;
		return value;
	}

	@Override
	public short setPrimitiveValue(short value) {
		short old = this.getPrimitiveValue();
		this.value = value;
		return old;
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
