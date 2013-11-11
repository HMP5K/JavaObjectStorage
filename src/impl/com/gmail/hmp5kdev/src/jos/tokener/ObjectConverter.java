package com.gmail.hmp5kdev.src.jos.tokener;

import com.gmail.hmp5kdev.jos.array.JOSArray;
import com.gmail.hmp5kdev.jos.object.JOSObject;

public interface ObjectConverter {

	
	public abstract String doObject(JOSObject<? , ?> object);
	public abstract JOSObject<? , ?> doObject(String name , String value);
	
	public abstract String[] doArray(JOSArray<? , ?> array);
	public abstract JOSArray<? , ?> doArray(String name , String[] value);
	
}
