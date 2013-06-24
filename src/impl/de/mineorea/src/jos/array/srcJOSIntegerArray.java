package de.mineorea.src.jos.array;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.array.JOSIntegerArray;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.object.JOSObject;
import de.mineorea.src.jos.object.srcJOSCompound;

public final class srcJOSIntegerArray implements JOSIntegerArray {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private Integer[] value;
	
	public srcJOSIntegerArray(srcJOSCompound comp , Integer[] value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public Integer set(int index, Integer value) {
		Integer old = this.value[index];
		this.value[index] = value;
		return old;
	}

	@Override
	public Integer get(int index) {
		return this.value[index];
	}

	@Override
	public int lenght() {
		return this.value.length;
	}

	@Override
	public void clear() {
		this.value = new Integer[value.length];
	}

	@Override
	public JOSCompound getCompound() {
		return this.comp;
	}

	@Override
	public Integer[] getValue() {
		return this.value;
	}

	@Override
	public Integer[] setValue(Integer[] value) {
		Integer[] old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSIntegerArray copy() throws JOSException {
		return new srcJOSIntegerArray(comp , value);
	}

	@Override
	public void fill(JOSObject<Integer[], JOSIntegerArray> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSIntegerArray.class.getSimpleName().toLowerCase();
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!", this);
	}

	@Override
	public int[] getPrimitiveValue() {
		int[] n = new int[this.value.length];
		for(int i = 0; i < value.length ; i++){
			if(value[i] == null){
				n[i] = 0;
			}else n[i] = value[i];
		}
		return n;
	}

	@Override
	public int[] setPrimitiveValue(int[] value) {
		int[] old = this.getPrimitiveValue();
		Integer[] n = new Integer[value.length];
		for(int i = 0 ; i < value.length ; i++){
			n[i] = value[i];
		}
		this.value = n;
		return old;
	}
}