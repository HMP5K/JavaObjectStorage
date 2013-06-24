package de.mineorea.src.jos.object;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.object.JOSCharacter;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.object.JOSObject;

public final class srcJOSCharacter implements JOSCharacter {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private Character value;
	
	public srcJOSCharacter(srcJOSCompound comp , Character value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public Character getValue() {
		return this.value;
	}

	@Override
	public Character setValue(Character value) {
		Character old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSCharacter copy() throws JOSException {
		return new srcJOSCharacter(comp , value);
	}

	@Override
	public void fill(JOSObject<Character, JOSCharacter> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSCharacter.class.getSimpleName().toLowerCase();
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public char getPrimitiveValue() {
		if(this.value == 0) return '\0';
		return this.value;
	}

	@Override
	public char setPrimitiveValue(char value) {
		char old = this.getPrimitiveValue();
		this.value = value;
		return old;
	}

}
