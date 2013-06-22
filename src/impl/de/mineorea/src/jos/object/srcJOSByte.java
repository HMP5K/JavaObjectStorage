package de.mineorea.src.jos.object;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.object.JOSByte;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.object.JOSObject;

public final class srcJOSByte implements JOSByte {
	private static final long serialVersionUID = 1L;

	private Byte value;
	private srcJOSCompound comp;
	
	public srcJOSByte(srcJOSCompound comp , Byte value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Byte getValue() {
		return value;
	}

	@Override
	public Byte setValue(Byte value) {
		Byte old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSByte copy() throws JOSException {
		return new srcJOSByte(this.comp , this.value);
	}

	@Override
	public void fill(JOSObject<Byte, JOSByte> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSByte.class.getSimpleName().toLowerCase();
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public byte getPrimitiveValue() {
		if(value == null) return 0;
		return value;
	}

	@Override
	public byte setPrimitiveValue(byte b) {
		byte old = this.getPrimitiveValue();
		this.value = old;
		return old;
	}

}
