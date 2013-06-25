package de.mineorea.jos.object;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.annotation.Generated;

import de.mineorea.jos.array.JOSBooleanArray;
import de.mineorea.jos.array.JOSByteArray;
import de.mineorea.jos.array.JOSDoubleArray;
import de.mineorea.jos.array.JOSFloatArray;
import de.mineorea.jos.array.JOSIntegerArray;
import de.mineorea.jos.array.JOSLongArray;
import de.mineorea.jos.array.JOSShortArray;
import de.mineorea.jos.array.JOSStringArray;

//TODO Documentation
public interface JOSCompound extends JOSObject<JOSObject<? , ?> , JOSCompound>, Iterable<Entry<String , JOSObject<? , ?>>>{

	public abstract JOSCompound getParent();
	public abstract void clear();
	public abstract HashMap<String , JOSObject<? , ?>> getObjects();
	
	public abstract String toString();
	
	@Generated({ "de.mineorea.tools.generator.UtilGenerator"})
	abstract <Native, Hosted extends JOSObject<Native, Hosted>> JOSObject<Native , Hosted> 
		_get( String name , Class<Native> NativeType, Class<Hosted> ReturnType );
	
	//Object
	public abstract JOSObject<? , ?> getObject(String name);
	public abstract boolean setObject(String name , JOSObject<?, ?> value);
	public abstract boolean containsObject(String name);
	public abstract JOSObject<? , ?> deleteObject(String name);
	
	//Compound
	public abstract JOSCompound getCompound(String name);
	public abstract boolean     setCompound(String name);
	public abstract boolean     containsCompound(String name);
	
	//Byte
	public abstract JOSByte getByte(String name);
	public abstract boolean setByte(String name , JOSByte value);
	public abstract boolean setByte(String name , Byte value);
	public abstract boolean containsByte(String name);
	
	//Short
	public abstract JOSShort getShort(String name);
	public abstract boolean  setShort(String name , JOSShort value);
	public abstract boolean  setShort(String name , Short value);
	public abstract boolean  containsShort(String name);
	
	//Integer
	public abstract JOSInteger getInteger(String name);
	public abstract boolean    setInteger(String name, JOSInteger value);
	public abstract boolean    setInteger(String name, Integer value);
	public abstract boolean    containsInteger(String name);
	
	//Long
	public abstract JOSLong getLong(String name);
	public abstract boolean setLong(String name , JOSLong value);
	public abstract boolean setLong(String name , Long value);
	public abstract boolean containsLong(String name);
	
	//Float
	public abstract JOSFloat getFloat(String name);
	public abstract boolean  setFloat(String name , JOSFloat value);
	public abstract boolean  setFloat(String name , Float value);
	public abstract boolean  containsFloat(String name);
	
	//Double
	public abstract JOSDouble getDouble(String name);
	public abstract boolean   setDouble(String name, JOSDouble value);
	public abstract boolean   setDouble(String name , Double value);
	public abstract boolean   containsDouble(String name);
	
	//Boolean
	public abstract JOSBoolean getBoolean(String name);
	public abstract boolean    setBoolean(String name , JOSBoolean value);
	public abstract boolean    setBoolean(String name , Boolean value);
	public abstract boolean    containsBoolean(String name);
	
	//Character
	public abstract JOSCharacter getCharacter(String name);
	public abstract boolean      setCharacter(String name, JOSCharacter value);
	public abstract boolean      setCharacter(String name, Character value);
	public abstract boolean      containsCharacter(String name);
	
	//String 
	public abstract JOSString getString(String name);
	public abstract boolean   setString(String name, JOSString value);
	public abstract boolean   setString(String name, String value);
	public abstract boolean   containsString(String name);
	
	//Byte Array
	public abstract JOSByteArray getByteArray(String name);
	public abstract boolean      setByteArray(String name, JOSByteArray value);
	public abstract boolean      setByteArray(String name, Byte... value);
	public abstract boolean      containsByteArray(String name);
	
	//Short Array
	public abstract JOSShortArray getShortArray(String name);
	public abstract boolean       setShortArray(String name , JOSShortArray value);
	public abstract boolean       setShortArray(String name , Short...value);
	public abstract boolean       containsShortArray(String name);
	
	//Integer Array
	public abstract JOSIntegerArray getIntegerArray(String name);
	public abstract boolean         setIntegerArray(String name, JOSIntegerArray value);
	public abstract boolean         setIntegerArray(String name, Integer...value);
	public abstract boolean         containsIntegerArray(String name);
	
	//Long Array
	public abstract JOSLongArray getLongArray(String name);
	public abstract boolean      setLongArray(String name, JOSLongArray value);
	public abstract boolean      setLongArray(String name, Long...value);
	public abstract boolean      containsLongArray(String name);
	
	//Float Array
	public abstract JOSFloatArray getFloatArray(String name);
	public abstract boolean       setFloatArray(String name, JOSFloatArray value);
	public abstract boolean       setFloatArray(String name, Float...value);
	public abstract boolean       containsFloatArray(String name);
	
	//Double Array
	public abstract JOSDoubleArray getDoubleArray(String name);
	public abstract boolean        setDoubleArray(String name, JOSDoubleArray value);
	public abstract boolean        setDoubleArray(String name, Double...value);
	public abstract boolean        containsDoubleArray(String name);
	
	//Boolean Array
	public abstract JOSBooleanArray getBooleanArray(String name);
	public abstract boolean         setBooleanArray(String name, JOSBooleanArray value);
	public abstract boolean         setBooleanArray(String name, Boolean...value);
	public abstract boolean         containsBooleanArray(String name);
	
	//String Array
	public abstract JOSStringArray getStringArray(String name);
	public abstract boolean        setStringArray(String name, JOSStringArray value);
	public abstract boolean        setStringArray(String name, String...value);
	public abstract boolean        containsStringArray(String name);
}