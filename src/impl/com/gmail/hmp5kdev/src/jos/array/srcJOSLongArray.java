package com.gmail.hmp5kdev.src.jos.array;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
import com.gmail.hmp5kdev.jos.array.JOSLongArray;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.src.jos.object.srcJOSCompound;
import static com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst.*;

public final class srcJOSLongArray implements JOSLongArray {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private Long[] value;
	
	public srcJOSLongArray(srcJOSCompound comp , Long[] value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public Long set(int index, Long value) {
		Long old = this.value[index];
		this.value[index] = value;
		return old;
	}

	@Override
	public Long get(int index) {
		return this.value[index];
	}

	@Override
	public int lenght() {
		return this.value.length;
	}

	@Override
	public void clear() {
		this.value = new Long[value.length];
	}

	@Override
	public JOSCompound getCompound() {
		return this.comp;
	}

	@Override
	public Long[] getValue() {
		return this.value;
	}

	@Override
	public Long[] setValue(Long[] value) {
		Long[] old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSLongArray copy() throws JOSException {
		return new srcJOSLongArray(comp , value);
	}

	@Override
	public void fill(JOSObject<Long[], JOSLongArray> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return LONG_ARRAY_NAME;
		
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!", this);
	}

	@Override
	public long[] getPrimitiveValue() {
		long[] n = new long[this.value.length];
		for(int i = 0; i < value.length ; i++){
			if(value[i] == null){
				n[i] = (long) 0;
			}else n[i] = value[i];
		}
		return n;
	}

	@Override
	public long[] setPrimitiveValue(long[] value) {
		long[] old = this.getPrimitiveValue();
		Long[] n = new Long[value.length];
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
