package de.mineorea.src.jos.array;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.array.JOSByteArray;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.object.JOSObject;
import de.mineorea.src.jos.object.srcJOSCompound;

public final class srcJOSByteArray implements JOSByteArray {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private Byte[] value;
	
	public srcJOSByteArray(srcJOSCompound comp , Byte[] value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public Byte set(int index, Byte value) {
		Byte old = this.value[index];
		this.value[index] = value;
		return old;
	}

	@Override
	public Byte get(int index) {
		return this.value[index];
	}

	@Override
	public int lenght() {
		return this.value.length;
	}

	@Override
	public void clear() {
		this.value = new Byte[value.length];
	}

	@Override
	public JOSCompound getCompound() {
		return this.comp;
	}

	@Override
	public Byte[] getValue() {
		return this.value;
	}

	@Override
	public Byte[] setValue(Byte[] value) {
		Byte[] old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSByteArray copy() throws JOSException {
		return new srcJOSByteArray(comp , value);
	}

	@Override
	public void fill(JOSObject<Byte[], JOSByteArray> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSByteArray.class.getSimpleName().toLowerCase();
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!", this);
	}

	@Override
	public byte[] getPrimitiveValue() {
		byte[] n = new byte[this.value.length];
		for(int i = 0; i < value.length ; i++){
			if(value[i] == null){
				n[i] = (byte) 0;
			}else n[i] = value[i];
		}
		return n;
	}

	@Override
	public byte[] setPrimitiveValue(byte[] value) {
		byte[] old = this.getPrimitiveValue();
		Byte[] n = new Byte[value.length];
		for(int i = 0 ; i < value.length ; i++){
			n[i] = value[i];
		}
		this.value = n;
		return old;
	}

}
