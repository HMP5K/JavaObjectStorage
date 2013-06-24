package de.mineorea.src.jos.object;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.array.JOSBooleanArray;
import de.mineorea.jos.array.JOSByteArray;
import de.mineorea.jos.array.JOSDoubleArray;
import de.mineorea.jos.array.JOSFloatArray;
import de.mineorea.jos.array.JOSIntegerArray;
import de.mineorea.jos.array.JOSLongArray;
import de.mineorea.jos.array.JOSShortArray;
import de.mineorea.jos.array.JOSStringArray;
import de.mineorea.jos.object.JOSBoolean;
import de.mineorea.jos.object.JOSByte;
import de.mineorea.jos.object.JOSCharacter;
import de.mineorea.jos.object.JOSCompound;
import de.mineorea.jos.object.JOSDouble;
import de.mineorea.jos.object.JOSFloat;
import de.mineorea.jos.object.JOSInteger;
import de.mineorea.jos.object.JOSLong;
import de.mineorea.jos.object.JOSObject;
import de.mineorea.jos.object.JOSShort;
import de.mineorea.jos.object.JOSString;
import de.mineorea.src.jos.array.srcJOSBooleanArray;
import de.mineorea.src.jos.array.srcJOSByteArray;
import de.mineorea.src.jos.array.srcJOSDoubleArray;
import de.mineorea.src.jos.array.srcJOSFloatArray;
import de.mineorea.src.jos.array.srcJOSIntegerArray;
import de.mineorea.src.jos.array.srcJOSLongArray;
import de.mineorea.src.jos.array.srcJOSShortArray;
import de.mineorea.src.jos.array.srcJOSStringArray;

public class srcJOSCompound implements JOSCompound {
	
	public static final String BOOLEAN_NAME =   JOSBoolean.class.getSimpleName().toLowerCase();
	public static final String BYTE_NAME =      JOSByte.class.getSimpleName().toLowerCase();
	public static final String CHARACTER_NAME = JOSCharacter.class.getSimpleName().toLowerCase();
	public static final String COMPOUND_NAME =  JOSCompound.class.getSimpleName().toLowerCase();
	public static final String DOUBLE_NAME =    JOSCompound.class.getSimpleName().toLowerCase();
	public static final String FLOAT_NAME =     JOSFloat.class.getSimpleName().toLowerCase();
	public static final String INTEGER_NAME =   JOSInteger.class.getSimpleName().toLowerCase();
	public static final String LONG_NAME =      JOSLong.class.getSimpleName().toLowerCase();
	public static final String SHORT_NAME =     JOSShort.class.getSimpleName().toLowerCase();
	public static final String STRING_NAME =    JOSString.class.getSimpleName().toLowerCase();
	
	public static final String BOOLEAN_ARRAY_NAME = JOSBooleanArray.class.getSimpleName().toLowerCase();
	public static final String BYTE_ARRAY_NAME = JOSByteArray.class.getSimpleName().toLowerCase();
	public static final String DOUBLE_ARRAY_NAME = JOSDoubleArray.class.getSimpleName().toLowerCase();
	public static final String FLOAT_ARRAY_NAME = JOSFloatArray.class.getSimpleName().toLowerCase();
	public static final String INTEGER_ARRAY_NAME = JOSIntegerArray.class.getSimpleName().toLowerCase();
	public static final String LONG_ARRAY_NAME = JOSLongArray.class.getSimpleName().toLowerCase();
	public static final String SHORT_ARRAY_NAME = JOSShortArray.class.getSimpleName().toLowerCase();
	public static final String STRING_ARRAY_NAME = JOSStringArray.class.getSimpleName().toLowerCase();
	
	private static final long serialVersionUID = 1L;

	private srcJOSCompound root;
	public HashMap<String , JOSObject<?,?>> DATA = new HashMap<String , JOSObject<?,?>>();
	
	public srcJOSCompound(){
		this(null , null);
	}
	
	private srcJOSCompound(srcJOSCompound root, HashMap<String, JOSObject<? , ?>> DATA){
		this.root = root;
		if(DATA != null) this.DATA = DATA;
	}
	
	public srcJOSCompound(srcJOSCompound root) {
		this.root = root;
	}

	@Override
	public JOSCompound getCompound() {
		return this;
	}

	@Override
	public JOSObject<?, ?> getValue() {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public JOSObject<?, ?> setValue(JOSObject<?, ?> value) {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public JOSCompound copy() throws JOSException {
		return new srcJOSCompound(root , DATA);
	}

	@Override
	public void fill(JOSObject<JOSObject<?, ?>, JOSCompound> hosted) throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public String getName() {
		return JOSCompound.class.getSimpleName().toLowerCase();
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public Iterator<Entry<String, JOSObject<?, ?>>> iterator() {
		return this.DATA.entrySet().iterator();
	}

	@Override
	public JOSCompound getParent() {
		return this.root;
	}

	@Override
	public void clear() {
		this.DATA.clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, JOSObject<?, ?>> getObjects() {
		return (HashMap<String, JOSObject<?, ?>>) DATA.clone();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Native, Hosted extends JOSObject<Native, Hosted>> JOSObject<Native, Hosted> _get(String name, Class<Native> NativeType, Class<Hosted> ReturnType) {
		JOSObject<? , ?> raw = this.DATA.get(name);
		if(raw == null) return null;
		try{
			return (JOSObject<Native , Hosted>) raw;
		}catch(ClassCastException e){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> type){
		JOSObject<?, ?> raw = DATA.get(key);
		if(raw == null) return null;
		if(type.isAssignableFrom(raw.getClass()) == false) return null; 
		
		return (T) raw;

	}

	@Override
	public JOSObject<?, ?> getObject(String name) {
		return this.DATA.get(name);
	}

	@Override
	public boolean setObject(String name, JOSObject<?, ?> value) {
		boolean n = this.getObject(name) == null;
		this.DATA.put(name, value);
		return n;
	}

	@Override
	public boolean containsObject(String name) {
		return DATA.containsKey(name);
	}

	@Override
	public JOSObject<?, ?> deleteObject(String name) {
		return this.DATA.remove(name);
	}

	@Override
	public JOSCompound getCompound(String name) {
		return this.get(name, JOSCompound.class);
	}

	@Override
	public boolean setCompound(String name) {
		Boolean n = this.containsCompound(name);
		this.setObject(name, new srcJOSCompound(this , null));
		return n;
	}

	@Override
	public boolean containsCompound(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(COMPOUND_NAME)) return true;
		return false;
	}

	@Override
	public JOSByte getByte(String name) {
		return this.get(name, JOSByte.class);
	}

	@Override
	public boolean setByte(String name, JOSByte value) {
		Boolean n = this.containsBoolean(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setByte(String name, Byte value) {
		Boolean n = this.containsBoolean(name);
		this.setObject(name, new srcJOSByte(this , value));
		return n;
	}

	@Override
	public boolean containsByte(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(BYTE_NAME)) return true;
		return false;
	}

	@Override
	public JOSShort getShort(String name) {
		return get(name, JOSShort.class);
	}

	@Override
	public boolean setShort(String name, JOSShort value) {
		Boolean n = this.containsShort(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setShort(String name, Short value) {
		Boolean n = this.containsShort(name);
		this.setObject(name, new srcJOSShort(this , value));
		return n;
	}

	@Override
	public boolean containsShort(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(SHORT_NAME)) return true;
		return false;
	}

	@Override
	public JOSInteger getInteger(String name) {
		return get(name, JOSInteger.class);
	}

	@Override
	public boolean setInteger(String name, JOSInteger value) {
		Boolean n = this.containsInteger(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setInteger(String name, Integer value) {
		Boolean n = this.containsInteger(name);
		this.setObject(name, new srcJOSInteger(this ,value));
		return n;
	}

	@Override
	public boolean containsInteger(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(INTEGER_NAME)) return true;
		return false;
	}

	@Override
	public JOSLong getLong(String name) {
		return get(name, JOSLong.class);
	}

	@Override
	public boolean setLong(String name, JOSLong value) {
		Boolean n = this.containsLong(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setLong(String name, Long value) {
		Boolean n = this.containsLong(name);
		this.setObject(name, new srcJOSLong(this ,value));
		return n;		
	}

	@Override
	public boolean containsLong(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(LONG_NAME)) return true;
		return false;
	}

	@Override
	public JOSFloat getFloat(String name) {
		return get(name , JOSFloat.class);
	}

	@Override
	public boolean setFloat(String name, JOSFloat value) {
		Boolean n = this.containsFloat(name);
		this.setFloat(name, value);
		return n;
	}

	@Override
	public boolean setFloat(String name, Float value) {
		return this.setFloat(name, new srcJOSFloat(this , value));
	}

	@Override
	public boolean containsFloat(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(FLOAT_NAME)) return true;
		return false;
	}

	@Override
	public JOSDouble getDouble(String name) {
		return get(name , JOSDouble.class);
	}

	@Override
	public boolean setDouble(String name, JOSDouble value) {
		Boolean n = this.containsDouble(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setDouble(String name, Double value) {
		return this.setDouble(name, new srcJOSDouble(this ,value));
	}

	@Override
	public boolean containsDouble(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(DOUBLE_NAME)) return true;
		return false;
	}

	@Override
	public JOSBoolean getBoolean(String name) {
		return get(name , JOSBoolean.class);
	}

	@Override
	public boolean setBoolean(String name, JOSBoolean value) {
		Boolean n = this.containsBoolean(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setBoolean(String name, Boolean value) {
		return this.setBoolean(name, new srcJOSBoolean(this ,value));
	}

	@Override
	public boolean containsBoolean(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(BOOLEAN_NAME)) return true;
		return false;
	}

	@Override
	public JOSCharacter getCharacter(String name) {
		return get(name , JOSCharacter.class);
	}

	@Override
	public boolean setCharacter(String name, JOSCharacter value) {
		Boolean n = this.containsCharacter(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setCharacter(String name, Character value) {
		return this.setCharacter(name, new srcJOSCharacter(this ,value));
	}

	@Override
	public boolean containsCharacter(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(CHARACTER_NAME)) return true;
		return false;
	}

	@Override
	public JOSString getString(String name) {
		return get(name, JOSString.class);
	}

	@Override
	public boolean setString(String name, JOSString value) {
		Boolean n = this.containsString(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setString(String name, String value) {
		return this.setString(name, new srcJOSString(this , value));
	}

	@Override
	public boolean containsString(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(STRING_NAME)) return true;
		return false;
	}

	@Override
	public JOSByteArray getByteArray(String name) {
		return get(name , JOSByteArray.class);
	}

	@Override
	public boolean setByteArray(String name, JOSByteArray value) {
		boolean n = this.containsByteArray(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setByteArray(String name, Byte... value) {
		boolean n = this.containsByteArray(name);
		this.setObject(name, new srcJOSByteArray(this, value));
		return n;
	}

	@Override
	public boolean containsByteArray(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(BYTE_ARRAY_NAME)) return true;
		return false;
	}

	@Override
	public JOSShortArray getShortArray(String name) {
		return get(name, JOSShortArray.class);
	}

	@Override
	public boolean setShortArray(String name, JOSShortArray value) {
		boolean n = this.containsShortArray(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setShortArray(String name, Short... value) {
		boolean n = this.containsShortArray(name);
		this.setObject(name, new srcJOSShortArray(this ,value));
		return n;
	}

	@Override
	public boolean containsShortArray(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(SHORT_ARRAY_NAME)) return true;
		return false;
	}

	@Override
	public JOSIntegerArray getIntegerArray(String name) {
		return get(name , JOSIntegerArray.class);
	}

	@Override
	public boolean setIntegerArray(String name, JOSIntegerArray value) {
		boolean n = this.containsIntegerArray(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setIntegerArray(String name, Integer... value) {
		boolean n = this.containsIntegerArray(name);
		this.setObject(name, new srcJOSIntegerArray(this ,value));
		return n;
	}

	@Override
	public boolean containsIntegerArray(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(INTEGER_ARRAY_NAME)) return true;
		return false;
	}

	@Override
	public JOSLongArray getLongArray(String name) {
		return this.get(name , JOSLongArray.class);
	}

	@Override
	public boolean setLongArray(String name, JOSLongArray value) {
		boolean n = this.containsLongArray(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setLongArray(String name, Long... value) {
		return this.setLongArray(name, new srcJOSLongArray(this, value));
	}

	@Override
	public boolean containsLongArray(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(LONG_ARRAY_NAME)) return true;
		return false;
	}

	@Override
	public JOSFloatArray getFloatArray(String name) {
		return get(name , JOSFloatArray.class);
	}

	@Override
	public boolean setFloatArray(String name, JOSFloatArray value) {
		boolean n = this.containsFloatArray(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setFloatArray(String name, Float... value) {
		return this.setFloatArray(name, new srcJOSFloatArray(this , value));
	}

	@Override
	public boolean containsFloatArray(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(FLOAT_ARRAY_NAME)) return true;
		return false;
	}

	@Override
	public JOSDoubleArray getDoubleArray(String name) {
		return get(name , JOSDoubleArray.class);
	}

	@Override
	public boolean setDoubleArray(String name, JOSDoubleArray value) {
		boolean n = this.containsDoubleArray(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setDoubleArray(String name, Double... value) {
		return this.setDoubleArray(name, new srcJOSDoubleArray(this ,value));
	}

	@Override
	public boolean containsDoubleArray(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(DOUBLE_ARRAY_NAME)) return true;
		return false;
	}

	@Override
	public JOSBooleanArray getBooleanArray(String name) {
		return get(name , JOSBooleanArray.class);
	}

	@Override
	public boolean setBooleanArray(String name, JOSBooleanArray value) {
		boolean n = this.containsBooleanArray(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setBooleanArray(String name, Boolean... value) {
		return this.setBooleanArray(name, new srcJOSBooleanArray(this , value));
	}

	@Override
	public boolean containsBooleanArray(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(BOOLEAN_ARRAY_NAME)) return true;
		return false;
	}

	@Override
	public JOSStringArray getStringArray(String name) {
		return get(name , JOSStringArray.class);
	}

	@Override
	public boolean setStringArray(String name, JOSStringArray value) {
		boolean n  = this.containsStringArray(name);
		this.setObject(name, value);
		return n;
	}

	@Override
	public boolean setStringArray(String name, String... value) {
		return this.setStringArray(name, new srcJOSStringArray(this ,value));
	}

	@Override
	public boolean containsStringArray(String name) {
		JOSObject<? , ?> raw = this.getObject(name);
		if(raw == null) return false;
		if(raw.getName().equalsIgnoreCase(STRING_ARRAY_NAME)) return true;
		return false;
	}

}
