package de.mineorea.test.jos;

import de.mineorea.jos.tools.JOSAccess;
import de.mineorea.jos.tools.JOSAccess.AccessType;

public interface User {

	@JOSAccess(name = "name", access = AccessType.GET)
	public abstract String getName();
	
	@JOSAccess(name = "name" , access = AccessType.SET)
	public abstract void setName(String name);
	
	@JOSAccess(name = "level", access = AccessType.GET)
	public abstract Long getLevel();
	
	@JOSAccess(name = "level" , access = AccessType.SET)
	public abstract void setLevel(Long level);
	
}
