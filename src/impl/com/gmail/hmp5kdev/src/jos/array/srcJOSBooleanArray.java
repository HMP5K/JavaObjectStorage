package com.gmail.hmp5kdev.src.jos.array;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
import com.gmail.hmp5kdev.jos.array.JOSBooleanArray;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.src.jos.object.srcJOSCompound;
import static com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst.*;

public final class srcJOSBooleanArray implements JOSBooleanArray {
	private static final long serialVersionUID = 1L;
	
	private srcJOSCompound comp;
	private Boolean[] value;
	
	public srcJOSBooleanArray(srcJOSCompound comp , Boolean[] value){
		this.value = value;
		this.comp = comp;
	}
	
	@Override
	public Boolean set(int index, Boolean value) {
		Boolean old = this.get(index);
		this.value[index] = value;
		return old;
	}

	@Override
	public Boolean get(int index) {
		return this.value[index];
	}

	@Override
	public int lenght() {
		return this.value.length;
	}

	@Override
	public void clear() {
		this.value = new Boolean[value.length];
	}

	@Override
	public JOSCompound getCompound() {
		return this.comp;
	}

	@Override
	public Boolean[] getValue() {
		return value;
	}

	@Override
	public Boolean[] setValue(Boolean[] value) {
		Boolean[] old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSBooleanArray copy() throws JOSException {
		return new srcJOSBooleanArray(comp , value);
	}

	@Override
	public void fill(JOSObject<Boolean[], JOSBooleanArray> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return BOOLEAN_ARRAY_NAME;
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!", this);
	}

	@Override
	public boolean[] getPrimitiveValue() {
		boolean[] bools = new boolean[value.length];
		for(int i = 0 ; i < value.length ; i++){
			if(value[i] == null){
				bools[i] = false;
			}else bools[i] = value[i];
		}
		return bools;
	}

	@Override
	public boolean[] setPrimitiveValue(boolean[] value) {
		boolean[] old = this.getPrimitiveValue();
		Boolean[] n = new Boolean[value.length];
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
