package de.mineorea.src.jos.tools;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

import de.mineorea.jos.JOSException;
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
import de.mineorea.jos.tools.JOSAccess;
import de.mineorea.jos.tools.JOSAccess.AccessType;
import de.mineorea.jos.tools.JOSBinder;
import de.mineorea.src.jos.lib.reflect.ProxyGenerator;
import de.mineorea.src.jos.lib.reflect.ProxyGenerator.InvocationLogger;
import de.mineorea.src.jos.object.srcJOSCompound;
import de.mineorea.src.jos.tokener.JOSDefaultObjectConverter;

public final class srcJOSBinder implements JOSBinder {

	private ProxyGenerator proxy = ProxyGenerator.getGenerator();
	
	@Override
	public <T> T createWrapper(JOSCompound comp, Class<T> Interface) throws JOSException {
		if(Interface.isInterface() == false) throw new JOSException("Not an Interface!");
		return proxy.createProxy(Interface, new LinkedCompoundInvocationHandler((srcJOSCompound) comp));
	}


	@Override
	public <T> T createWrapperWithLogger(JOSCompound comp, Class<T> Interface) throws JOSException {
		if(Interface.isInterface() == false) throw new JOSException("Not an Interface!");
		InvocationLogger logger = proxy.newInvocationLogger()
				.invokeAfter(new LinkedCompoundInvocationHandler((srcJOSCompound) comp))
				.printAnnoations(true).printArguments(true);
		return proxy.createProxy(Interface, logger);
	}
	
	@Override
	public boolean isWrapper(Object o) {
		throw new JOSException("Not done yet. Sorry :(");
	}

	public static final class LinkedCompoundInvocationHandler implements InvocationHandler{

		private srcJOSCompound comp;
		
		public LinkedCompoundInvocationHandler(srcJOSCompound comp){
			this.comp = comp;
		}
		
		public HashMap<Method , Object[]> cache = new HashMap<Method , Object[]>();
		public JOSDefaultObjectConverter conv = new JOSDefaultObjectConverter();
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object[] info = cache.get(method);
			if(info == null){
				info = new Object[3];
				JOSAccess anno = method.getAnnotation(JOSAccess.class);
				if(anno == null) throw new JOSException("Missing JOSAccess Annotation!");
				info[0] = anno.name();
				info[1] = method.getReturnType();
				info[2] = anno.access();
				this.cache.put(method, info);
			}
			AccessType t = (AccessType) info[2];
			
			if(t == AccessType.GET){
				JOSObject<? , ?> data = comp.getObject(info[0].toString());
				if(data != null){
					Class<?> type = (Class<?>) info[1];
					if(type.isAssignableFrom(data.getClass())){
						return data;
					}else{
						if(data.getValue() == null){
							return ProxyGenerator.getGenerator().createReturn(method);
						}else{
							if(type.isAssignableFrom(data.getValue().getClass())){
								return data.getValue();
							}
						}
					}
				}
			}else if(t == AccessType.SET){
				Class<?> types[] = method.getParameterTypes();
				if(types.length != 1) throw new JOSException("Illegal Argument size!");
				Class<?> type = types[0];
				JOSObject<? , ?> obj = null;
				if(type.isAssignableFrom(JOSObject.class)){
					obj = (JOSObject<?, ?>) args[0];
				}else{
					Class<?> name = null;
					
					if(type == Boolean.class || type == boolean.class){
						name = JOSBoolean.class;
					}else if(type == Byte.class || type == byte.class){
						name = JOSByte.class;
					}else if(type == Character.class || type == char.class){
						name = JOSCharacter.class;
					}else if(type == Double.class || type == double.class){
						name = JOSDouble.class;
					}else if(type == Float.class || type == float.class){
						name = JOSFloat.class;
					}else if(type == Integer.class || type == int.class){
						name = JOSInteger.class;
					}else if(type == Long.class || type == long.class){
						name = JOSLong.class;
					}else if(type == Short.class || type == short.class){
						name = JOSShort.class;
					}else{
						name = JOSString.class;
					}
					obj = conv.doObject(name.getSimpleName().toLowerCase(), args[0] == null ? null : args[0].toString());
				}
				comp.setObject(info[0].toString(), obj);
				
			}
			
			return ProxyGenerator.getGenerator().createReturn(method);
		}
		
		
	}
}
