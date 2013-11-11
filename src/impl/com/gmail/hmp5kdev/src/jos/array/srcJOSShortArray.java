package com.gmail.hmp5kdev.src.jos.array;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
import com.gmail.hmp5kdev.jos.array.JOSShortArray;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.src.jos.object.srcJOSCompound;
import static com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst.*;

public final class srcJOSShortArray implements JOSShortArray {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private Short[] value;
	
	public srcJOSShortArray(srcJOSCompound comp , Short[] value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public Short set(int index, Short value) {
		Short old = this.value[index];
		this.value[index] = value;
		return old;
	}

	@Override
	public Short get(int index) {
		return value[index];
	}

	@Override
	public int lenght() {
		return value.length;
	}

	@Override
	public void clear() {
		value = new Short[value.length];
	}

	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Short[] getValue() {
		return value;
	}

	@Override
	public Short[] setValue(Short[] value) {
		Short[] old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSShortArray copy() throws JOSException {
		return new srcJOSShortArray(this.comp , value);
	}

	@Override
	public void fill(JOSObject<Short[], JOSShortArray> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return SHORT_ARRAY_NAME;
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public short[] getPrimitiveValue() {
	    short[] n = new short[this.value.length];
		for(int i = 0 ; i < value.length ; i++){
			if(value[i] == null){
				n[i] = 0;
			}else n[i] = value[i];
		}
		return n;
	}

	@Override
	public short[] setPrimitiveValue(short[] value) {
		short[] old = this.getPrimitiveValue();
		Short[] n = new Short[value.length];
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
