package org.jfw.orm.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Utils {
	private static Map<Class<?>,Class<?>> wrapClass=new HashMap<Class<?>,Class<?>>();
	private static Map<String,String> wrapClassName=new HashMap<String,String>();
	
	
	public static boolean isPrimitive(String className){
		return wrapClassName.containsKey(className);
	}
	public static boolean isPrimitive(Class<?> clazz){
		return wrapClass.containsKey(clazz);
	}
	public static String getWrapClass(String className){
		return wrapClassName.get(className);
	}
	
	public static void appendFieldNameToMethodName(String name ,StringBuilder sb){
		if(name.length()>1){
			sb.append(name.substring(0,1).toUpperCase(Locale.ENGLISH)).append(name.substring(1));
		}else if(name.length()>0){
			sb.append(name.substring(0,1).toUpperCase(Locale.ENGLISH));
		}
	}
	public static byte[] toByteArray(InputStream in) throws IOException{
		byte[] buf = new byte[4096];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len=0;
		while((len=in.read(buf))>=0){
			baos.write(buf,0, len);
		}
		return baos.toByteArray();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static{
	    wrapClass.put(int.class,Integer.class);
	    wrapClass.put(byte.class,Byte.class);
	    wrapClass.put(short.class,Short.class);
	    wrapClass.put(float.class,Float.class);
	    wrapClass.put(double.class, Double.class);
	    wrapClass.put(boolean.class,Boolean.class);
	    wrapClass.put(char.class,Character.class);
	    wrapClass.put(long.class, Long.class);
	    for(Map.Entry<Class<?>,Class<?>> en:wrapClass.entrySet()){
	    	wrapClassName.put(en.getKey().getName(), en.getValue().getName());
	    }
	}
}
