package org.jfw.orm.core;

import java.util.Map;

public interface OrmHandler {
	//对应的Bean属性类
	Class<?> supportsClass();
	//read ResultSet rs;
	void readValue(StringBuilder sb,String beforCode,String afterCode,int colIndex,boolean dbNullable,Map<String,Object> localVarInMethod);

    //write   PreparedStatement ps;
	//        int paramIndex = 1;
	void init(StringBuilder sb,String valueEl,boolean userTempalteVar,boolean dbNullable,Map<String,Object> localVarInMethod);
	/*
	 * sb.append("if(");
	 * OrmHandler.checkNull(sb);
	 * sb.append("){");
	 * OrmHandler.writeNullValue(sb);
	 * sb.append("}else{");
	 * OrmHandler.writeValue(sb);
	 * sb.append("}");	 * 
	 */	
	void checkNull(StringBuilder sb);	
	//使用 "paramIndex++" 局部变量 
	void writeNullValue(StringBuilder sb);
	void writeValue(StringBuilder sb);
	boolean isReplaceResource();
	void replaceResource(StringBuilder sb);
	
}