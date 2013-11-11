package com.gmail.hmp5kdev.src.jos.tokener.binary;

import com.gmail.hmp5kdev.jos.array.JOSArray;
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

public final class JOSConst {

	private JOSConst(){
		throw new Error("Illegal Access Error: No Instances allowed!");
	}
	
	/* Object Names */
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
	
	/* Native Object Names */
	public static final char   BOOL_NATIVE_NAME = 'Z';
	public static final char   BYTE_NATIVE_NAME = 'B';
	public static final char   CHAR_NATIVE_NAME = 'C';
	public static final char  SHORT_NATIVE_NAME = 'S';
	public static final char    INT_NATIVE_NAME = 'I';
	public static final char   LONG_NATIVE_NAME = 'J';
	public static final char  FLOAT_NATIVE_NAME = 'F';
	public static final char DOUBLE_NATIVE_NAME = 'D';
	
	public static final char   COMP_NATIVE_NAME = 'K';
	public static final char STRING_NATIVE_NAME = 'T';
	
	public static final char  ARRAY_NATIVE_NAME_PREFIX = '[';
	public static final char OBJECT_NATIVE_NAME_PREFIX = '.';
	
    public static final char UNKNOWN_NATIVE_NAME = '*';


    public static final Class<? extends JOSObject<?,?>> getByNativeName(String name){
    	if(name.length() != 2)
    		return null;
    	char obj = name.charAt(0);
    	char type = name.charAt(1);
    	if(obj == ARRAY_NATIVE_NAME_PREFIX){
    		if(type == BOOL_NATIVE_NAME){
    			return JOSBooleanArray.class;
    		}else if(type == BYTE_NATIVE_NAME){
    			return JOSByteArray.class;
    		}else if(type == STRING_NATIVE_NAME){
    			return JOSStringArray.class;
    		}else if(type == SHORT_NATIVE_NAME){
    			return JOSShortArray.class;
    		}else if(type == INT_NATIVE_NAME){
    			return JOSIntegerArray.class;
    		}else if(type == LONG_NATIVE_NAME){
    			return JOSLongArray.class;
    		}else if(type == FLOAT_NATIVE_NAME){
    			return JOSFloatArray.class;
    		}else if(type == DOUBLE_NATIVE_NAME){
    			return JOSDoubleArray.class;
    		}else{
    			return null;
    		}
    	}else if(obj == OBJECT_NATIVE_NAME_PREFIX){
    		if(type == BOOL_NATIVE_NAME){
    			return JOSBoolean.class;
    		}else if(type == BYTE_NATIVE_NAME){
    			return JOSByte.class;
    		}else if(type == STRING_NATIVE_NAME){
    			return JOSString.class;
    		}else if(type == SHORT_NATIVE_NAME){
    			return JOSShort.class;
    		}else if(type == INT_NATIVE_NAME){
    			return JOSInteger.class;
    		}else if(type == LONG_NATIVE_NAME){
    			return JOSLong.class;
    		}else if(type == FLOAT_NATIVE_NAME){
    			return JOSFloat.class;
    		}else if(type == DOUBLE_NATIVE_NAME){
    			return JOSDouble.class;
    		}else if(type == CHAR_NATIVE_NAME){
    			return JOSCharacter.class;
    		}else if(type == COMP_NATIVE_NAME){
    			return JOSCompound.class;
    		}else{
    			return null;
    		}
    	}else{
    		return null;
    	}
    }
    
    public static final String getNativeName(Class<? extends JOSObject<?,?>> clazz ){
    	String name = new String();
    	if(isObjectInstance(clazz, JOSArray.class)){
    		name += ARRAY_NATIVE_NAME_PREFIX;
    	}else{
    		name += OBJECT_NATIVE_NAME_PREFIX;
    	}
    	
    	if(isObjectInstance(clazz, JOSBoolean.class, JOSBooleanArray.class)){
    		name += BOOL_NATIVE_NAME;
    	}else if(isObjectInstance(clazz, JOSByte.class, JOSByteArray.class)){
    		name += BYTE_NATIVE_NAME;
    	}else if(isObjectInstance(clazz, JOSCharacter.class)){
    		name += CHAR_NATIVE_NAME;
    	}else if(isObjectInstance(clazz, JOSCompound.class)){
    		name += COMP_NATIVE_NAME;
    	}else if(isObjectInstance(clazz, JOSDouble.class, JOSDoubleArray.class)){
    		name += COMP_NATIVE_NAME;
    	}else if(isObjectInstance(clazz, JOSFloat.class , JOSFloatArray.class)){
    		name += FLOAT_NATIVE_NAME;
    	}else if(isObjectInstance(clazz, JOSInteger.class, JOSIntegerArray.class)){
    		name += INT_NATIVE_NAME;
    	}else if(isObjectInstance(clazz, JOSLong.class, JOSLongArray.class)){
    		name += LONG_NATIVE_NAME;
    	}else if(isObjectInstance(clazz, JOSShort.class, JOSShortArray.class)){
    		name += SHORT_NATIVE_NAME;
    	}else if(isObjectInstance(clazz, JOSString.class, JOSStringArray.class)){
    		name += STRING_NATIVE_NAME;
    	}else{
    		name += UNKNOWN_NATIVE_NAME;
    	}
    	return name;
    }
    
    public static final boolean isObjectInstance(Class<? extends JOSObject<?,?>> master,  Class<?>... childs){
    	if(master == null || childs == null || childs.length == 0)
    		return false;
    	/*Class<?>[] clazzes = master.getInterfaces();
    	for(Class<?> c : clazzes){
    		for(Class<?> cc : childs){
    			if(c == cc)
    				return true;
    		}
    	}
    	for(Class<?> c : childs){
    		if(c == master)
    			return true;
    	}*/
    	for(Class<?> c : childs){
    		if(c.isAssignableFrom(master)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public static final boolean isObjectInstance(Class<? extends JOSObject<?,?>> clazz, JOSObject<?,?> obj){
    	return isObjectInstance(clazz, obj.getClass());
    }
    
    public static final boolean isObjectInstance(Class<? extends JOSObject<?,?>> clazz, String name){
    	 return clazz.getSimpleName().toLowerCase().equals(name);
    }
}