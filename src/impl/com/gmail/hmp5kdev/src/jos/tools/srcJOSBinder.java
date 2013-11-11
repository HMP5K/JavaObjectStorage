package com.gmail.hmp5kdev.src.jos.tools;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.gmail.hmp5kdev.jos.JOSException;
import com.gmail.hmp5kdev.jos.JOSUtils;
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
import com.gmail.hmp5kdev.jos.tools.JOSAccess;
import com.gmail.hmp5kdev.jos.tools.JOSAccessor;
import com.gmail.hmp5kdev.jos.tools.JOSBinder;
import com.gmail.hmp5kdev.jos.tools.JOSAccess.AccessType;
import com.gmail.hmp5kdev.src.jos.lib.reflect.ProxyGenerator;
import com.gmail.hmp5kdev.src.jos.lib.reflect.ProxyGenerator.InvocationLogger;
import com.gmail.hmp5kdev.src.jos.object.srcJOSCompound;
import com.gmail.hmp5kdev.src.jos.tokener.jos.JOSDefaultObjectConverter;

public final class srcJOSBinder implements JOSBinder {

	private ProxyGenerator proxy = ProxyGenerator.getGenerator();
	
	@Override
	public <T extends JOSAccessor> T createWrapper(JOSCompound comp, Class<T> Interface) throws JOSException {
		if(Interface.isInterface() == false) throw new JOSException("Not an Interface!");
		return proxy.createProxyWithType(Interface, new LinkedCompoundInvocationHandler((srcJOSCompound) comp), VirtualCompound.class);
	}


	@Override
	public <T extends JOSAccessor> T createWrapperWithLogger(JOSCompound comp, Class<T> Interface) throws JOSException {
		if(Interface.isInterface() == false) throw new JOSException("Not an Interface!");
		InvocationLogger logger = proxy.newInvocationLogger()
				.invokeAfter(new LinkedCompoundInvocationHandler((srcJOSCompound) comp))
				.printAnnoations(true).printArguments(true);
		return proxy.createProxyWithType(Interface, logger, VirtualCompound.class);
	}
	
	@Override
	public boolean isWrapper(Object o) {
		return o instanceof VirtualCompound;
	}

	public static final class LinkedCompoundInvocationHandler implements InvocationHandler{

		private srcJOSCompound comp;
		
		public LinkedCompoundInvocationHandler(srcJOSCompound comp){
			this.comp = comp;
		}
		
		public HashMap<Method , Object[]> cache = new HashMap<Method , Object[]>();
		public JOSDefaultObjectConverter conv = new JOSDefaultObjectConverter();
		public ProxyGenerator proxy = ProxyGenerator.getGenerator();
		
		private boolean isTypeOK(Class<?> w0, Class<?> p0){
			if(p0 == Byte.TYPE && w0 == Byte.class){
				return true;
			}else if(p0 == Short.TYPE && w0 == Short.class){
				return true;
			}else if(p0 == Integer.TYPE && w0 == Integer.class){
				return true;
			}else if(p0 == Long.TYPE && w0 == Long.class){
			    return true;
			}else if(p0 == Float.TYPE && w0 == Float.class){
				return true;
			}else if(p0 == Double.TYPE && w0 == Double.class){
				return true;
			}else if(p0 == Character.TYPE && w0 == Character.class){
				return true;
			}else if(p0 == Boolean.TYPE && w0 == Boolean.class){
				return true;
			}else{
				return false;
			}
		}
		
		@SuppressWarnings("deprecation")
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			
			if(method.getName().equalsIgnoreCase("toString")){
				return JOSUtils.toString(comp);
			}else if(method.getName().equalsIgnoreCase("toCompound")){
				return comp;
			}
			
			Object[] info = cache.get(method);
			if(info == null){
				info = new Object[4];
				JOSAccess anno = method.getAnnotation(JOSAccess.class);
				if(anno == null){
					return this.proxy.createReturn(method);
				}
				info[0] = anno.name();
				info[1] = method.getReturnType();
				
				AccessType access = null;
				if(anno.access() == AccessType.AUTO){
					String n = method.getName().toLowerCase();
					if(n.startsWith("set") || (args != null && args.length == 1)){
						access = AccessType.SET;
					}else if(n.startsWith("get") || n.startsWith("is") || args == null || args.length == 0){
						access = AccessType.GET;
					}else{
						throw new JOSException("Can't Auto Detect Type!");
					}
				}else{
					access = anno.access();
				}
				info[2] = access;
				info[3] = anno.returnInstance();
				this.cache.put(method, info);
			}
			AccessType t = (AccessType) info[2];
			
			
			String path_raw = info[0].toString();
			if(path_raw.startsWith("\\")){
				path_raw = path_raw.substring(1);
			}
			if(path_raw.endsWith("\\")){
				path_raw = path_raw.substring(0, path_raw.length() - 1);
			}
			
			if(t == AccessType.GET){
				
				String[] path = path_raw.split("\\\\");
				JOSObject<? , ?> data = null;
				if(path != null && path.length >= 1){
					srcJOSCompound ccomp = comp;
					for(int i = 0 ; i < path.length - 1 ; i++){
						ccomp = (srcJOSCompound) ccomp.getCompound(path[i]);
						if(ccomp == null){
							return this.proxy.createReturn(method);
						}
					}
					data = ccomp.getObject(path[path.length - 1]);
				}else{
					data = comp.getObject(path_raw);
				}
				if(data != null){
					Class<?> type = (Class<?>) info[1];
					
					if(type.isAssignableFrom(data.getClass())){
						return data;
					}else{
						if(data.getValue() == null){
							return this.proxy.createReturn(method);
						}else{
							if(type.isAssignableFrom(data.getValue().getClass()) || type == Object.class || isTypeOK(data.getValue().getClass() , type)){
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
				
				srcJOSCompound ccomp = comp;
				String[] path = path_raw.split("\\\\");
				if(path != null && path.length >= 1){
					for(int i = 0 ; i < path.length - 1 ; i++){
						srcJOSCompound old = ccomp;
						ccomp = (srcJOSCompound) ccomp.getCompound(path[i]);
						if(ccomp == null){
						  old.setCompound(path[i]);
						  ccomp = (srcJOSCompound) old.getCompound(path[i].toString());
						}
					}
					
				}else{
					ccomp = comp;
				}
				
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
					}else if(type == String.class){
						name = JOSString.class;
						
					//Array
					}else if(type == Boolean[].class || type == boolean[].class){
						name = JOSBooleanArray.class;
					}else if(type == Byte[].class || type == byte[].class){
						name = JOSByteArray.class;
						
					//Char[]
					}else if(type == char[].class){
						name = JOSString.class;
						args[0] = new String((char[]) args[0]);
					}else if(type == Character[].class){
						name = JOSString.class;
						Character[] old = (Character[]) args[0];
						char[] cold = new char[old.length];
						for(int i = 0 ; i< old.length ; i++){
							cold[i] = (char) old[i];
						}
						args[0] = new String(cold);
					}
					//End Char[]
					else if(type == Double[].class || type == double[].class){
						name = JOSDoubleArray.class;
					}else if(type == Float[].class || type == float[].class){
						name = JOSFloatArray.class;
					}else if(type == Integer[].class || type == int[].class){
						name =  JOSIntegerArray.class;
					}else if(type == Long[].class || type == long[].class){
						name = JOSLongArray.class;
					}else if(type == Short[].class || type == short[].class){
						name = JOSShortArray.class;
					}else if(type == String[].class){
						name = JOSStringArray.class;
					}else{
						throw new JOSException("Can't set Object!" , new IllegalArgumentException("No JOS Compatible Type!"));
					}
					if(JOSArray.class.isAssignableFrom(name)){
						Object[] raw = (Object[]) args[0];
						String[] data = new String[raw.length];
						for(int i = 0 ; i< raw.length ; i++){
							data[i] = raw[i] == null ? conv.NULL : raw[i].toString();
						}
						
						obj = conv.doArray(name.getSimpleName().toLowerCase(), data);
					}else{
						obj = conv.doObject(name.getSimpleName().toLowerCase(), args[0] == null ? null : args[0].toString());
					}
				}
				ccomp.setObject(path[path.length - 1].toString(), obj);
			}
			if(method.getReturnType().isAssignableFrom(proxy.getClass()) && info[3].equals(true)){
				return proxy;
			}

			return this.proxy.createReturn(method);
		}		
	}
}