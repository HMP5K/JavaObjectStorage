package de.mineorea.src.jos.object;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.object.JOSObject;
import de.mineorea.jos.object.JOSShort;

public final class srcJOSShort implements JOSShort {
	private static final long serialVersionUID = 1L;
	
	private srcJOSCompound comp;
	private Short value;
	
	public srcJOSShort(srcJOSCompound comp , Short value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Short getValue() {
		return value;
	}

	@Override
	public Short setValue(Short value) {
		Short old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSShort copy() throws JOSException {
		return new srcJOSShort(comp , value);
	}

	@Override
	public void fill(JOSObject<Short, JOSShort> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSShort.class.getName();
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public short getPrimitiveValue() {
		if(this.value == null) return 0;
		return value;
	}

	@Override
	public short setPrimitiveValue(short value) {
		short old = this.getPrimitiveValue();
		this.value = value;
		return old;
	}

}
