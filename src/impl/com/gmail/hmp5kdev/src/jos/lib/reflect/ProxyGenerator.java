package com.gmail.hmp5kdev.src.jos.lib.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public final class ProxyGenerator {

	private static ProxyGenerator gen;
	
	public static ProxyGenerator getGenerator(){
		if(gen == null) gen = new ProxyGenerator();
		return gen;
	}
	
	private ProxyGenerator(){
		if(gen != null) throw new IllegalStateException();
	}
	
	
	public <T extends Object> T createProxy(Class<T> Interface , Object implementor){
		return this.createProxy(Interface, implementor, false);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T createProxy(Class<T> Interface , Object implementor , boolean with_logger){
		if(Interface.isInterface() == false) throw new RuntimeException("Class is not an Interface");
		ClassLoader sysLoader = ClassLoader.getSystemClassLoader();
		Class<T>[] Interfaces = new Class[]{ Interface };
		if(with_logger){
			return (T) 
					Proxy.newProxyInstance(sysLoader, Interfaces, 
							this.newInvocationLogger().printArguments(true).printAnnoations(true).invokeAfter(
									new InvocationImplementor(implementor)));
		}
		return (T) (Proxy.newProxyInstance(sysLoader, Interfaces, new InvocationImplementor(implementor)));
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T createProxyWithType(Class<T> Interface, InvocationHandler handler, Class<?> TYPE){
		if(Interface.isInterface() == false || TYPE.isInterface() == false) throw new RuntimeException("Class is not an Interface");
		ClassLoader sysLoader = ClassLoader.getSystemClassLoader();
		Class<?>[] Interfaces = new Class[]{ Interface, TYPE };
		return (T) Proxy.newProxyInstance(sysLoader, Interfaces, handler);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T createProxy(Class<T> Interface, InvocationHandler handler){
		if(Interface.isInterface() == false) throw new RuntimeException("Class is not an Interface");
		ClassLoader sysLoader = ClassLoader.getSystemClassLoader();
		Class<T>[] Interfaces = new Class[]{ Interface };
		return (T) Proxy.newProxyInstance(sysLoader, Interfaces, handler);
	}
	
	public Object createReturn(Method method){
		Class<?> return_type = method.getReturnType();
		if(return_type.isPrimitive()){
			if(return_type == Byte.TYPE){
				return (byte) 0;
			}else if(return_type == Short.TYPE){
				return (short) 0;
			}else if(return_type == Integer.TYPE){
				return (int) 0;
			}else if(return_type == Long.TYPE){
				return 0L;
			}else if(return_type == Float.TYPE){
				return 0F;
			}else if(return_type == Double.TYPE){
				return 0D;
			}else if(return_type == Character.TYPE){
				return '\0';
			}else if(return_type == Boolean.TYPE){
				return false;
			}
		}
		return null;
	}
	
	public InvocationLogger newInvocationLogger(){
		return new InvocationLogger();
	}
	
	public InvocationImplementor newInvokationImplentor(Object master){
		return new InvocationImplementor(master);
	}
	
	public final class InvocationImplementor implements InvocationHandler {

		private HashMap<String, Method> cache = new HashMap<String , Method>();
		private Object obj;
		
		public boolean SAVE_NAME = false;
		
		public InvocationImplementor(Object implementor){
			this.obj =  implementor;
		}
		
		private String getName(Method m){
			String name = m.getName() + ";";
			for(Class<?> t : m.getParameterTypes()){
				name += t.getName() + ";";
			}
			return name;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			boolean save = this.SAVE_NAME;
			Method m1 = cache.get(save ? getName(method) : method.getName());
			if(m1 == null){
				try{
					m1 = obj.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
					cache.put(save ? getName(method) :  method.getName() , m1);
				}catch(Exception e){
					throw new RuntimeException("Error Invoking Virtual: " + method.getName() + " in " + method.getDeclaringClass() , e);
				}
			}
			if(save){
				if(m1.getReturnType().equals(method.getReturnType()) == false || m1.getTypeParameters().equals(method.getTypeParameters()) == false){
					throw new RuntimeException("Error Invoking Virtual: " + method.getName() + " in " + method.getDeclaringClass() , new ClassCastException(m1.getName() + "cannot be cast to " + method.getReturnType()));
				}
			}
			
			return m1.invoke(this.obj, args);
		}
		
	}
	
	public final class InvocationLogger implements InvocationHandler{
		
		private boolean print_args = false;
		private boolean print_anno = false;
		private InvocationHandler handler = null;
		
		public InvocationLogger printArguments(boolean flag){
			this.print_args = flag;
			return this;
		}
		
		public InvocationLogger printAnnoations(boolean flag){
			this.print_anno = flag;
			return this;
		}
		
		public InvocationLogger invokeAfter(InvocationHandler handler){
			this.handler = handler;
			return this;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			StringBuilder out = new StringBuilder();
			String class_name = method.getDeclaringClass().getName();
			String method_name = method.getName();
			String hash = Integer.toHexString(System.identityHashCode(proxy)).toUpperCase();
			String method_hash =  Integer.toHexString(System.identityHashCode(method)).toUpperCase();
			
			out.append("0x" +  hash + ": interrupt 0x" + method_hash +  " ["   + class_name + "::" + method_name + "(");
			int index = 0;
			Class<?>[] types = method.getParameterTypes();
			
			for(Class<?> arg : types){
				index++;
				out.append(arg.getName());
				if(index < types.length){
					out.append(", ");
				}
			}
			out.append("): " + method.getReturnType().getName() + "]\n");
			if(this.print_args){
				if(args == null || args.length == 0){
					out.append("   args[]: null\n");
				}else{
					int i = 0;
					for(Object arg : args){
					    if(args == null){
							out.append("   args[" + i + "]: 0x0000000 { null }\n");
						}else{
							String hash_arg = arg == null ? "0x0000000" : Integer.toHexString(System.identityHashCode(arg)).toUpperCase();
							out.append("   args[" + i + "]: 0x" + hash_arg + " { " + types[i].getName() + "= " + ( arg == null ? "null" : arg.toString() ) + " }\n" );
						}
						i++;
					}
				}
			}
			
			if(this.print_anno){
				int index_anno = 0;
				for(Annotation anno : method.getAnnotations()){
					out.append("   anno[" + index_anno + "]: " + anno.annotationType().getName() + "\n");
					index_anno++;
				}
				if(index_anno == 0){
					out.append("   anno[]: null\n");
				}
			}
			
			
			if(this.handler != null){	
				Long start = System.nanoTime();
				Object ret = this.handler.invoke(proxy, method, args);
				Long delay = (System.nanoTime() - start);
				out.append(" delay: " + delay + "ns\n");
				out.append(" return: " + (ret == null ? null : ret.toString() ) + "\n");
				System.out.println(out.toString());
				return ret;
			}
			
			//return null
			Class<?> return_type = method.getReturnType();
			if(return_type.isPrimitive()){
				if(return_type == Byte.TYPE){
					out.append(" return: 0\n");
					System.out.println(out.toString());
					return (byte) 0;
				}else if(return_type == Short.TYPE){
					out.append(" return: 0\n");
					System.out.println(out.toString());
					return (short) 0;
				}else if(return_type == Integer.TYPE){
					out.append(" return: 0\n");
					System.out.println(out.toString());
					return (int) 0;
				}else if(return_type == Long.TYPE){
					out.append(" return: 0\n");
					System.out.println(out.toString());
					return 0L;
				}else if(return_type == Float.TYPE){
					out.append(" return: 0.0\n");
					System.out.println(out.toString());
					return 0F;
				}else if(return_type == Double.TYPE){
					out.append(" return: 0.0\n");
					System.out.println(out.toString());
					return 0D;
				}else if(return_type == Character.TYPE){
					out.append(" return: \"\0\" (\\0)\n");
					System.out.println(out.toString());
					return '\0';
				}else if(return_type == Boolean.TYPE){
					out.append(" return: false\n");
					System.out.print(out.toString());
					return false;
				}
			}
			if(return_type == Void.TYPE){
				out.append(" return: void\n");
				System.out.println(out.toString());
				return null;
			}
			out.append(" return: null\n");
			
			System.out.println(out.toString());
			return null;		
		}	
	}
}