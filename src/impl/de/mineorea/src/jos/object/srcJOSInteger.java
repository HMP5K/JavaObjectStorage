package de.mineorea.src.jos.object;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.object.JOSInteger;
import de.mineorea.jos.object.JOSObject;

public final class srcJOSInteger implements JOSInteger {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private Integer value;
	
	public srcJOSInteger(srcJOSCompound comp , Integer value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public Integer setValue(Integer value) {
		Integer old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSInteger copy() throws JOSException {
		return new srcJOSInteger(comp , value);
	}

	@Override
	public void fill(JOSObject<Integer, JOSInteger> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSInteger.class.getSimpleName().toLowerCase();
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public int getPrimitiveValue() {
		if(this.value == null) return 0;
		return value;
	}

	@Override
	public int setPrimitiveValue(int value) {
		int old = this.getPrimitiveValue();
		this.value = value;
		return old;
	}

}
