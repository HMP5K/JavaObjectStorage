package de.mineorea.jos.tools;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JOSAccess {

	public String name();
	public AccessType access();
	
	public static enum AccessType{
		GET,
		SET
	}
	
}
