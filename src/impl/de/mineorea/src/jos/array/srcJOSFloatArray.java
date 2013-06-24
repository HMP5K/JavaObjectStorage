package de.mineorea.src.jos.array;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.array.JOSDoubleArray;
import de.mineorea.jos.array.JOSFloatArray;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.object.JOSObject;
import de.mineorea.src.jos.object.srcJOSCompound;

public final class srcJOSFloatArray implements JOSFloatArray {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private Float[] value;
	
	public srcJOSFloatArray(srcJOSCompound comp , Float[] value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public Float set(int index, Float value) {
		Float old = this.value[index];
		this.value[index] = value;
		return old;
	}

	@Override
	public Float get(int index) {
		return value[index];
	}

	@Override
	public int lenght() {
		return value.length;
	}

	@Override
	public void clear() {
		value = new Float[value.length];
	}

	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Float[] getValue() {
		return value;
	}

	@Override
	public Float[] setValue(Float[] value) {
		Float[] old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSFloatArray copy() throws JOSException {
		return new srcJOSFloatArray(this.comp , value);
	}

	@Override
	public void fill(JOSObject<Float[], JOSFloatArray> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSDoubleArray.class.getSimpleName().toLowerCase();
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public float[] getPrimitiveValue() {
		float[] n = new float[this.value.length];
		for(int i = 0 ; i < value.length ; i++){
			if(value[i] == null){
				n[i] = 0f;
			}else n[i] = value[i];
		}
		return n;
	}

	@Override
	public float[] setPrimitiveValue(float[] value) {
		float[] old = this.getPrimitiveValue();
		Float[] n = new Float[value.length];
		for(int i = 0 ; i < value.length ; i++){
			n[i] = value[i];
		}
		this.value = n;
		return old;
	}

}
