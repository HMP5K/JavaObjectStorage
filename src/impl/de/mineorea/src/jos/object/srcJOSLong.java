package de.mineorea.src.jos.object;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.object.JOSLong;
import de.mineorea.jos.object.JOSObject;

public final class srcJOSLong implements JOSLong {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private Long value;
	
	public srcJOSLong(srcJOSCompound comp, Long value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Long getValue() {
		return value;
	}

	@Override
	public Long setValue(Long value) {
		Long old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSLong copy() throws JOSException {
		return new srcJOSLong(this.comp , this.value);
	}

	@Override
	public void fill(JOSObject<Long, JOSLong> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSLong.class.getSimpleName().toLowerCase();
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public long getPrimitiveValue() {
		if(this.value == null) return 0L;
		return value;
	}

	@Override
	public long setPrimitiveValue(long value) {
		long old = this.getPrimitiveValue();
		this.value = value;
		return old;
	}
}