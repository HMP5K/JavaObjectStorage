package com.gmail.hmp5kdev.src.jos.object;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
import com.gmail.hmp5kdev.jos.object.JOSCharacter;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst;

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
		return JOSConst.CHARACTER_NAME;
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
