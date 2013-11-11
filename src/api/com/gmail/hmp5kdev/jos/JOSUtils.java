package com.gmail.hmp5kdev.jos;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.gmail.hmp5kdev.jos.JOSTokener.JOSFormat;
import com.gmail.hmp5kdev.jos.array.JOSArray;
import com.gmail.hmp5kdev.jos.object.JOSCompound;
import com.gmail.hmp5kdev.jos.object.JOSObject;
import com.gmail.hmp5kdev.jos.tools.JOSAccessor;
import com.gmail.hmp5kdev.jos.tools.JOSBinder;
import com.gmail.hmp5kdev.src.jos.lib.ByteUtils;
import com.gmail.hmp5kdev.src.jos.tokener.binary.JOSConst;

public final class JOSUtils {
	
	public static final void main(String[] args){
	}
	
	private static final JOSTokener tokener = JOSTokener.newTokener(JOSFormat.JAVA_SERIALIZATION)
			.gzip(true).verbose(false).version((byte)-JOSObject.VERSION_FORMAT);
	
	private static final JOSBinder binder = JOSTokener.newBinder();
	
	public static final boolean objectEquals(JOSObject<?, ?> a, JOSObject<?, ?> b){
		return(a == null && b == null) || (a.getClass().equals(b.getClass()) && 
			    ( (a.getValue() == null && b.getValue() == null ) 
			    		|| a.getValue().equals(b.getValue() ) || a.getValue().hashCode() == b.getValue().hashCode() || a.getValue() == b.getValue()) && 
			    (a.getCompound().equals(b.getCompound()) ) 
			  );
	}
	
	public static final int getClass(Class<? extends JOSObject<?,?>> obj){
		if(obj == null)
			return (char)0;
		char[] data = JOSConst.getNativeName(obj).toCharArray();
		return ByteUtils.compile((short)data[0], (short)data[1]);
	}
	
	public static final Class<? extends JOSObject<?,?>> getClass(int obj){
		char c0 = (char)(obj >> 16 & ByteUtils.CHAR_MASK);
		char c1 = (char)(obj & ByteUtils.CHAR_MASK);
		return JOSConst.getByNativeName(new String(new char[]{c0, c1}));
	}
	
	public static final void throwJOSException(String reason, Throwable cause, JOSObject<?,?> obj){
		throw new JOSException(reason, cause, obj, true, true);
	}
	
	public static final void throwJOSError(String reason, Throwable cause, JOSObject<?,?> obj){
		throw new JOSException(reason, cause, obj, false, false);
	}

	public static final <T> T[] toArray(JOSArray<T,?> array){
		return array == null ? null : array.getValue();
	}
	
	public static final <T> List<T> toList(JOSArray<T, ?> array){
		T[] data = toArray(array);
		return data == null ? null : Arrays.asList(data);
	}
	
	public static final void quickSave(JOSCompound comp, File file){
		if(comp == null || file == null)
			throw new NullPointerException();
		tokener.save(comp, file);
	}
	
	public static final JOSCompound quickLoad(File file){
		if(file == null)
			throw new NullPointerException();
		return tokener.load(file);
	}
	
	public static final <T extends JOSAccessor> T newInstance(JOSCompound comp, Class<T> type){
		return newInstance(comp, null, type);
	}
	
	public static final <T extends JOSAccessor> T newInstance(JOSCompound comp, String name, Class<T> type){
		JOSCompound data = name != null ? comp.createCompound(name) : comp;
		return binder.createWrapper(data, type);
	}
	
	public static final String toString(JOSObject<?,?> object){
		if(object == null)
			return "null";
		StringBuilder toString = new StringBuilder();
		toString.append(object.getClass().getName());
		toString.append("[type=");
		toString.append(object.getName());
		if(object instanceof JOSCompound){
			toString.append(", value=");
			toString.append(Arrays.toString(((JOSCompound) object).getKeys().toArray()));
		}else if(object instanceof JOSArray<?,?>){
			toString.append(", value=");
			toString.append(Arrays.toString(((JOSArray<?,?>) object).getValue()));
		}else{
			toString.append(", value=");
			toString.append(object.getValue());
		}
		toString.append("]");
		return toString.toString();
	}
	
	//TODO Utils
}
