package com.gmail.hmp5kdev.src.jos.object;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSInteger;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst;

public final class srcJOSInteger implements JOSInteger {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private Integer value;
	
	public srcJOSInteger(srcJOSCompound comp , Integer value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public Integer setValue(Integer value) {
		Integer old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSInteger copy() throws JOSException {
		return new srcJOSInteger(comp , value);
	}

	@Override
	public void fill(JOSObject<Integer, JOSInteger> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSConst.INTEGER_NAME;
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public int getPrimitiveValue() {
		if(this.value == null) return 0;
		return value;
	}

	@Override
	public int setPrimitiveValue(int value) {
		int old = this.getPrimitiveValue();
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
