package de.mineorea.src.jos.tokener;

import de.mineorea.jos.array.JOSArray;
import de.mineorea.jos.object.JOSObject;

public interface ObjectConverter {

	public abstract String doObject(JOSObject<? , ?> object);
	public abstract JOSObject<? , ?> doObject(String name , String value);
	
	public abstract String[] doArray(JOSArray<? , ?> array);
	public abstract JOSArray<? , ?> doArray(String name , String[] value);
	
}
