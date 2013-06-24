package de.mineorea.src.jos.tokener;

import de.mineorea.jos.JOSException;
import de.mineorea.jos.array.JOSArray;
import de.mineorea.jos.array.JOSBooleanArray;
import de.mineorea.jos.array.JOSByteArray;
import de.mineorea.jos.array.JOSDoubleArray;
import de.mineorea.jos.array.JOSFloatArray;
import de.mineorea.jos.array.JOSIntegerArray;
import de.mineorea.jos.array.JOSShortArray;
import de.mineorea.jos.array.JOSStringArray;
import de.mineorea.jos.object.JOSBoolean;
import de.mineorea.jos.object.JOSByte;
import de.mineorea.jos.object.JOSCharacter;
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
import de.mineorea.src.jos.object.srcJOSBoolean;
import de.mineorea.src.jos.object.srcJOSByte;
import de.mineorea.src.jos.object.srcJOSCharacter;
import de.mineorea.src.jos.object.srcJOSCompound;
import de.mineorea.src.jos.object.srcJOSDouble;
import de.mineorea.src.jos.object.srcJOSFloat;
import de.mineorea.src.jos.object.srcJOSInteger;
import de.mineorea.src.jos.object.srcJOSLong;
import de.mineorea.src.jos.object.srcJOSShort;
import de.mineorea.src.jos.object.srcJOSString;

public final class JOSDefaultObjectConverter implements ObjectConverter {

	private String NULL = "<null>";
	
	private String ESQ = "\\";
	
	private srcJOSCompound comp;
	
	public srcJOSCompound comp(srcJOSCompound c){
		if(c != null) this.comp = c;
		return comp;
	}
	
	public String encodeArray(String in){
		if(in == null) return null;
		in = in.trim();
		in = in.replace("," , ESQ + ",");
		in = in.replace("\n", this.ESQ + "n");
		return in;
	}
	
	public String[] decodeArray(String[] in){
		for(int i = 0 ; i < in.length ; i++){
			if(in[i] != null){
				in[i] = in[i].trim().replace(ESQ + "," , ",").replace(ESQ + "n", "\n");
			}
		}
		return in;
	}
	
	@Override
	public String doObject(JOSObject<?, ?> object) {
		/*String ret = NULL;
		if(object.getValue() != null) ret = object.getValue().toString();
		return ret;*/
		
		if(isObject(object , JOSBoolean.class)){
			Boolean v = (Boolean) object.getValue();
			if(v == null) return NULL;
			return v ? "true" : "false";
			
		}else if(isObject(object, JOSByte.class)){
			Byte v = (Byte) object.getValue();
			if(v == null) return NULL;
			return v.toString();
		
		}else if(isObject(object, JOSCharacter.class)){
			Character v = (Character) object.getValue();
			if(v == null) return NULL;
			return v.toString();
		
		}else if(isObject(object , JOSDouble.class)){
			Double v = (Double) object.getValue();
			if(v == null) return NULL;
			return v.toString();
			
		}else if(isObject(object , JOSFloat.class)){
			Float v = (Float) object.getValue();
			if(v == null) return NULL;
			return v.toString();
			
		}else if(isObject(object ,JOSInteger.class)){
			Integer v = (Integer) object.getValue();
			if(v == null) return NULL;
			return v.toString();
			
		}else if(isObject(object , JOSLong.class)){
			Long v = (Long) object.getValue();
			if(v == null) return NULL;
			return v.toString();
			
		}else if(isObject(object, JOSShort.class)){
			Short v = (Short) object.getValue();
			if(v == null) return NULL;
			return v.toString();
			
		}else if(isObject(object, JOSString.class)){
			String v = (String) object.getValue();
			if(v == null) return NULL;
			return v;
		}else{
			String v = "";
			String[] s = this.doArray((JOSArray<?, ?>) object);
			if(s == null) return NULL;
			for(String str : s){
				v += this.encodeArray(str) + ",";
			}
			return v.substring(0, v.length() - 1);
		}
	}
	
	public boolean isObject(JOSObject<? , ?> obj , Class<?> clazz){
		return clazz.isAssignableFrom(obj.getClass());
	}
	
	public boolean isObject(String name , Class<?> clazz){
		return clazz.getSimpleName().toLowerCase().equals(name);
	}
	
	@Override
	public JOSObject<?, ?> doObject(String name, String value) {
		if(isObject(name , JOSBoolean.class)){
			Boolean v = false;
			if(value == null || value.equalsIgnoreCase(NULL)) 
				v = null;
			else if(value.equalsIgnoreCase("true"))
				v = true;
			else if(value.equalsIgnoreCase("false"))
				v = false;
			else report("Illegal Value <" + value + "> for Type Boolean!");	
			return new srcJOSBoolean(comp , v);
			
		}else if(isObject(name , JOSByte.class)){
			Byte v = 0;
			if(value == null || value.equalsIgnoreCase(NULL)){
				v = null;
			}else{
				if(value.startsWith("0x")){
					value = value.substring(2);
					v = Byte.parseByte(value, 16);
				}else{
					v = Byte.parseByte(value);
				}
			}
			return new srcJOSByte(comp , v);	
			
		}else if(isObject(name , JOSCharacter.class)){
			Character v = '\0';
			if(value == null || value.equalsIgnoreCase(NULL)){
				v = null;
			}else{
				if(name.length() > 1) report("Illegal Character Lenght: " + name.length());
				v = name.charAt(0);
			}
			return new srcJOSCharacter(comp , v);
			
		}else if(isObject(name , JOSDouble.class)){
			Double v = 0.0D;
			if(value == null || value.equalsIgnoreCase(NULL)){
				v = null;
			}else{
				v = Double.parseDouble(name);
			}
			return new srcJOSDouble(comp , v);
			
		}else if(isObject(name , JOSFloat.class)){
			Float v = 0F;
			if(value == null || value.equalsIgnoreCase(NULL)){
				v = null;
			}else{
				v = Float.parseFloat(value);
			}
			return new srcJOSFloat(comp , v);
					
		}else if(isObject(name ,JOSInteger.class)){
			Integer v = 0;
			if(value == null || value.equalsIgnoreCase(NULL)){
				v = null;
			}else{
			   if(value.startsWith("0x")){
					value = value.substring(2);
					v = Integer.parseInt(value, 16);
				}else{
					v = Integer.parseInt(value);
				}
			}
			return new srcJOSInteger(comp , v);	
			
		}else if(isObject(name , JOSLong.class)){
			Long v = 0L;
			if(value == null || value.equalsIgnoreCase(NULL)){
				v = null;
			}else{
				if(value.startsWith("0x")){
					value = value.substring(2);
					v = Long.parseLong(value, 16);
				}else{
					v = Long.parseLong(value);
				}
			return new srcJOSLong(comp , v);	
			}
		}else if(isObject(name , JOSShort.class)){
			Short v = 0;
			if(value == null || value.equalsIgnoreCase(NULL)){
				v = null;
			}else{
				if(value.startsWith("0x")){
					value = value.substring(2);
					v = Short.parseShort(value , 16);
				}else{
					v = Short.parseShort(value);
				}
			}
			return new srcJOSShort(comp , v);
		}else if(isObject(name , JOSString.class)){
			String v = "";
			if(value == null || value.equalsIgnoreCase(NULL)){
				v = null;
			}else{
				v = value;
			}
			return new srcJOSString(comp , v);
		}else{
			String[] data = split(value , this.ESQ);
			data = this.decodeArray(data);
			return this.doArray(name, data);
		}
		
		report("Illegal Type: " + name);
		return null;
	}

	@Override
	public String[] doArray(JOSArray<?, ?> array) {
		String[] strings = new String[array.lenght()]; // TODO Better Converter
		Object[] data = array.getValue();
		if(data == null) return null;
		for(int i = 0 ; i < data.length ; i++){
			if(data[i] == null){
				strings[i] = NULL;
			}else{
				strings[i] = data[i].toString();
			}
		}
		return strings;
	}

	@Override
	public JOSArray<?, ?> doArray(String name, String[] value) {
		if(isObject(name , JOSBooleanArray.class)){
			if(value == null) return new srcJOSBooleanArray(comp , null);
			Boolean[] v = new Boolean[value.length];
			for(int i = 0 ; i < value.length ; i++){
				if(value[i] == null || value[i].equalsIgnoreCase(NULL)){
					v[i] = null;
				}else{
					if(value[i].equalsIgnoreCase("true"))
						v[i] = true;
					else if(value[i].equalsIgnoreCase("false"))
						v[i] = false;
					else report("Illega Value for Boolean: " + value[i]);
				}
			}
			return new srcJOSBooleanArray(comp , v);
		}else if(isObject(name , JOSByteArray.class)){
			if(value == null) return new srcJOSByteArray(comp , null);
			Byte[] v = new Byte[value.length];
			for(int i = 0; i < value.length ; i++ ){
				String str = value[i];
				if(str == null || str.equalsIgnoreCase(NULL)){
					v[i] = null;
				}
				if(str.startsWith("0x")){
					str = str.substring(2);
					v[i] = Byte.parseByte(str, 16);
				}else{
					v[i] = Byte.parseByte(str);
				}
				
			}
			return new srcJOSByteArray(comp , v);
		}else if(isObject(name, JOSDoubleArray.class)){
			if(value == null) return new srcJOSDoubleArray(comp , null);
			Double[] v = new Double[value.length];
			for(int i = 0 ; i < value.length ; i++){
				String str = value[i];
				if(str == null || str.equalsIgnoreCase(NULL)){
					v[i] = null;
				}else{
					v[i] = Double.parseDouble(str);
				}
			}
			return new srcJOSDoubleArray(comp , v);
		}else if(isObject(name , JOSFloatArray.class)){
			if(value == null) return new srcJOSFloatArray(comp , null);
			Float[] v = new Float[value.length];
			for(int i = 0 ; i < value.length ; i++){
				String str = value[i];
				if(str == null || str.equalsIgnoreCase(NULL)){
					v[i] = null;
				}else{
					v[i] = Float.parseFloat(str);
				}
			}
			return new srcJOSFloatArray(comp , v);
		}else if(isObject(name, JOSIntegerArray.class)){
			if(value == null) return new srcJOSIntegerArray(comp , null);
			Integer[] v = new Integer[value.length];
			for(int i = 0; i < value.length ; i++ ){
				String str = value[i];
				if(str == null || str.equalsIgnoreCase(NULL)){
					v[i] = null;
				}else  if(str.startsWith("0x")){
					str = str.substring(2);
					v[i] = Integer.parseInt(str, 16);
				}else{
					v[i] = Integer.parseInt(str);
				}
				
			}
			return new srcJOSIntegerArray(comp , v);
		}else if(isObject(name , JOSLong.class)){
			if(value == null) return new srcJOSLongArray(comp , null);
			Long[] v = new Long[value.length];
			for(int i = 0; i < value.length ; i++ ){
				String str = value[i];
				if(str == null || str.equalsIgnoreCase(NULL)){
					v[i] = null;
				}else
				if(str.startsWith("0x")){
					str = str.substring(2);
					v[i] = Long.parseLong(str, 16);
				}else{
					v[i] = Long.parseLong(str);
				}
				
			}
			return new srcJOSLongArray(comp , v);
		}else if(isObject(name, JOSShortArray.class)){
			if(value == null) return new srcJOSShortArray(comp , null);
			Short[] v = new Short[value.length];
			for(int i = 0; i < value.length ; i++ ){
				String str = value[i];
				if(str == null || str.equalsIgnoreCase(NULL)){
					v[i] = null;
				}else
				if(str.startsWith("0x")){
					str = str.substring(2);
					v[i] = Short.parseShort(str, 16);
				}else{
					v[i] = Short.parseShort(str);
				}
				
			}
			return new srcJOSShortArray(comp , v);
		}else if(isObject(name, JOSStringArray.class)){
			return new srcJOSStringArray(comp , value);
		}else{
			report("Illegal Type: " + name);
		}
		
		return null;
	}
	
	
	public String[] split(String str, String escapeChar) {
	    String regularExpressionSpecialChars = "/.*+?|()[]{}\\";

	    String escapedEscapeChar = escapeChar;

	    if(regularExpressionSpecialChars.indexOf(escapeChar) != -1) 
	        escapedEscapeChar = "\\" + escapeChar;

	    String[] temp = str.split("(?<!" + escapedEscapeChar + "),", -1);

	    String[] result = new String[temp.length];
	    for(int i=0; i<temp.length; i++) {
	        result[i] = temp[i].replaceAll(escapedEscapeChar + ",", ",");
	    }

	    return result;
	}
	
	
	public void report(String message){
		throw new JOSException(message);
	}

}
