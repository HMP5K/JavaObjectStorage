package com.gmail.hmp5kdev.src.jos.object;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSDouble;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst;

public class srcJOSDouble implements JOSDouble {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private Double value;
	
	public srcJOSDouble(srcJOSCompound comp , Double value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Double getValue() {
		return this.value;
	}

	@Override
	public Double setValue(Double value) {
		Double old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSDouble copy() throws JOSException {
		return new srcJOSDouble(comp , value);
	}

	@Override
	public void fill(JOSObject<Double, JOSDouble> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSConst.DOUBLE_NAME;
	}
	
	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public double getPrimitiveValue() {
		if(this.value == null) return 0.0D;
		return value;
	}

	@Override
	public double setPrimitiveValue(double value) {
		double old = this.getPrimitiveValue();
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
