package com.gmail.hmp5kdev.src.jos.object;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
import com.gmail.hmp5kdev.jos.object.JOSByte;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst;

public final class srcJOSByte implements JOSByte {
	private static final long serialVersionUID = 1L;

	private Byte value;
	private srcJOSCompound comp;
	
	public srcJOSByte(srcJOSCompound comp , Byte value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Byte getValue() {
		return value;
	}

	@Override
	public Byte setValue(Byte value) {
		Byte old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSByte copy() throws JOSException {
		return new srcJOSByte(this.comp , this.value);
	}

	@Override
	public void fill(JOSObject<Byte, JOSByte> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSConst.BYTE_NAME;
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public byte getPrimitiveValue() {
		if(value == null) return 0;
		return value;
	}

	@Override
	public byte setPrimitiveValue(byte b) {
		byte old = this.getPrimitiveValue();
		this.value = old;
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