package com.gmail.hmp5kdev.src.jos.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
import com.gmail.hmp5kdev.jos.array.JOSBooleanArray;
import com.gmail.hmp5kdev.jos.array.JOSByteArray;
import com.gmail.hmp5kdev.jos.array.JOSDoubleArray;
import com.gmail.hmp5kdev.jos.array.JOSFloatArray;
import com.gmail.hmp5kdev.jos.array.JOSIntegerArray;
import com.gmail.hmp5kdev.jos.array.JOSLongArray;
import com.gmail.hmp5kdev.jos.array.JOSShortArray;
import com.gmail.hmp5kdev.jos.array.JOSStringArray;
import com.gmail.hmp5kdev.jos.object.JOSBoolean;
import com.gmail.hmp5kdev.jos.object.JOSByte;
import com.gmail.hmp5kdev.jos.object.JOSCharacter;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSDouble;
import com.gmail.hmp5kdev.jos.object.JOSFloat;
import com.gmail.hmp5kdev.jos.object.JOSInteger;
import com.gmail.hmp5kdev.jos.object.JOSLong;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.jos.object.JOSShort;
import com.gmail.hmp5kdev.jos.object.JOSString;
import com.gmail.hmp5kdev.src.jos.array.srcJOSBooleanArray;
import com.gmail.hmp5kdev.src.jos.array.srcJOSByteArray;
import com.gmail.hmp5kdev.src.jos.array.srcJOSDoubleArray;
import com.gmail.hmp5kdev.src.jos.array.srcJOSFloatArray;
import com.gmail.hmp5kdev.src.jos.array.srcJOSIntegerArray;
import com.gmail.hmp5kdev.src.jos.array.srcJOSLongArray;
import com.gmail.hmp5kdev.src.jos.array.srcJOSShortArray;
import com.gmail.hmp5kdev.src.jos.array.srcJOSStringArray;
import com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst;

import static com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst.*;

public class srcJOSCompound implements JOSCompound {
	
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
		return JOSConst.COMPOUND_NAME;
	}

	@Override
	public byte[] asArray() throws JOSException {
		throw new JOSException("Not Supported!" , this);
	}

	@Override
	public Iterator<Entry<String, JOSObject<?, ?>>> iterator() {
		return this.getObjects().entrySet().iterator();
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
	
	@Override()
	public List<String> getKeys(){
		return new ArrayList<String>(DATA.keySet());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Native, Hosted extends JOSObject<Native, Hosted>> JOSObject<Native, Hosted> _get(String name, Class<Native> NativeType, Class<Hosted> ReturnType) {
		JOSObject<? , ?> raw = this.get(name, JOSObject.class);
		if(raw == null) return null;
		try{
			return (JOSObject<Native , Hosted>) raw;
		}catch(ClassCastException e){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends JOSObject<?,?>> T get(String key, Class<T> type){
		srcJOSCompound comp = this;
		String[] path = key.split("\\\\");
		JOSObject<? , ?> data = null;
		if(path != null && path.length >= 1){
			srcJOSCompound ccomp = comp;
			for(int i = 0 ; i < path.length - 1 ; i++){
				ccomp = (srcJOSCompound) ccomp.getCompound(path[i]);
				if(ccomp == null){
					return null;
				}
			}
			data = ccomp.DATA.get(path[path.length - 1]);
		}else{
			data = comp.DATA.get(key);
		}
		if(data == null) return null;
		if(type.isAssignableFrom(data.getClass()) == false) return null; 
		
		return (T) data;

	}

	@Override
	public JOSObject<?, ?> getObject(String name) {
		return this.get(name, JOSObject.class);
	}

	@Override
	public boolean setObject(String name, JOSObject<?, ?> value) {
		boolean n = this.getObject(name) == null;
		srcJOSCompound comp = this;
		String[] path = name.split("\\\\");
		if(path != null && path.length >= 1){
			srcJOSCompound ccomp = comp;
			srcJOSCompound parrent = comp;
			for(int i = 0 ; i < path.length - 1 ; i++){
				ccomp = (srcJOSCompound) ccomp.getCompound(path[i]);
				if(ccomp == null && parrent != null){
					ccomp = new srcJOSCompound(parrent);
					parrent.DATA.put(path[i], ccomp);
				}
				parrent = ccomp;
			}
			ccomp.DATA.put(path[path.length - 1], value);
		}else{
			comp.DATA.put(name, value);
		}
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
	public JOSCompound createCompound(String name) {
		if(this.containsCompound(name) == false){
			this.setCompound(name);
		}
		return this.getCompound(name);
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