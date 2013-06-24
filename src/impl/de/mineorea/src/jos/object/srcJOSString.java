package de.mineorea.src.jos.object;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.object.JOSObject;
import de.mineorea.jos.object.JOSString;

public final class srcJOSString implements JOSString {
	private static final long serialVersionUID = 1L;

	private srcJOSCompound comp;
	private String value;
	
	public srcJOSString(srcJOSCompound comp , String value){
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public JOSCompound getCompound() {
		return comp;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String setValue(String value) {
		String old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public JOSString copy() throws JOSException {
		return new srcJOSString(comp , value);
	}

	@Override
	public void fill(JOSObject<String, JOSString> hosted) throws JOSException {
		this.comp = (srcJOSCompound) hosted.getCompound();
		this.value = hosted.getValue();
	}

	@Override
	public String getName() {
		return JOSString.class.getSimpleName().toLowerCase();
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public char[] getChars() {
		if(value == null) return new char[]{'\0'};
		return this.value.toCharArray();
	}

	@Override
	public char[] setChars(char[] chars) {
		char[] old = this.getChars();
		this.value = new String(chars);
		return old;
	}

	@Override
	public int getEncoding() {
		return JOSString.UTF8;
	}

}
