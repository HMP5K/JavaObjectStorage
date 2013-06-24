package de.mineorea.src.jos.object;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.object.JOSFloat;
import de.mineorea.jos.object.JOSObject;

public final class srcJOSFloat implements JOSFloat {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private Float value;
	
	public srcJOSFloat(srcJOSCompound comp , Float value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Float getValue() {
		return value;
	}

	@Override
	public Float setValue(Float value) {
		Float old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSFloat copy() throws JOSException {
		return new srcJOSFloat(this.comp , this.value);
	}

	@Override
	public void fill(JOSObject<Float, JOSFloat> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSFloat.class.getSimpleName().toLowerCase();
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public float getPrimitiveValue() {
		if(this.value == null) return 0.0F;
		return value;
	}

	@Override
	public float setPrimitiveValue(float value) {
		float old = this.getPrimitiveValue();
		this.value = value;
		return old;
	}

}
