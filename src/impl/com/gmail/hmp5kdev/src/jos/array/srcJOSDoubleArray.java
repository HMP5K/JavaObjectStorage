package com.gmail.hmp5kdev.src.jos.array;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
import com.gmail.hmp5kdev.jos.array.JOSDoubleArray;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.src.jos.object.srcJOSCompound;
import static com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst.*;

public final class srcJOSDoubleArray implements JOSDoubleArray {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private Double[] value;
	
	public srcJOSDoubleArray(srcJOSCompound comp , Double[] value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public Double set(int index, Double value) {
		Double old = this.value[index];
		this.value[index] = value;
		return old;
	}

	@Override
	public Double get(int index) {
		return value[index];
	}

	@Override
	public int lenght() {
		return value.length;
	}

	@Override
	public void clear() {
		value = new Double[value.length];
	}

	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Double[] getValue() {
		return value;
	}

	@Override
	public Double[] setValue(Double[] value) {
		Double[] old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSDoubleArray copy() throws JOSException {
		return new srcJOSDoubleArray(this.comp , value);
	}

	@Override
	public void fill(JOSObject<Double[], JOSDoubleArray> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return DOUBLE_ARRAY_NAME;
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public double[] getPrimitiveValue() {
		double[] n = new double[this.value.length];
		for(int i = 0 ; i < value.length ; i++){
			if(value[i] == null){
				n[i] = 0D;
			}else n[i] = value[i];
		}
		return n;
	}

	@Override
	public double[] setPrimitiveValue(double[] value) {
		double[] old = this.getPrimitiveValue();
		Double[] n = new Double[value.length];
		for(int i = 0 ; i < value.length ; i++){
			n[i] = value[i];
		}
		this.value = n;
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
