package com.gmail.hmp5kdev.src.jos.object;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
import com.gmail.hmp5kdev.jos.object.JOSBoolean;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst;

public final class srcJOSBoolean implements JOSBoolean {
	private static final long serialVersionUID = 1L;
	
	private Boolean value;
	private srcJOSCompound comp;
	
	public srcJOSBoolean(srcJOSBoolean src){
		this(src.comp , src.value);
	}
	
	public srcJOSBoolean(srcJOSCompound comp , Boolean value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public Boolean setValue(Boolean value) {
		Boolean old = this.value;
			this.value = old;
		return old;
	}

	@Override
	public JOSBoolean copy() throws JOSException {
		return new srcJOSBoolean(this);
	}

	@Override
	public void fill(JOSObject<Boolean, JOSBoolean> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSConst.BOOLEAN_NAME;
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public boolean getPrimitiveValue() {
		if(this.value == null) return false;
		return value;
	}

	@Override
	public boolean setPrimitiveValue(boolean value) {
		boolean old = this.getPrimitiveValue();
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
