package org.jfw.orm.core.impl.root.packages.subtype;

import java.util.Locale;

import org.jfw.orm.core.OrmHandler;
import org.jfw.orm.core.impl.RootNode;
import org.jfw.orm.core.impl.root.DataElementNode;

public  class FilterSQLParam extends SQLParam{
	private String valueExpression;
	private String paramName;
	private String attributeName;
	private boolean calcValue = true;
	private boolean fixValue = false;
	private String dataElementId;
	
	
	private String filterExpression;
	private String columnName;
	private String operator;
	
	private boolean inDynamicFilter= false;
	
	public  String getFilterExpression(RootNode rn){
	   if(this.filterExpression!=null&& this.filterExpression.trim().length()>0) return this.filterExpression.trim();
	   return this.columnName.trim()+" "+this.operator.trim()+" ?";
		   
	}

	@Override
	public String getValueExpression(RootNode rn) {
		if(this.valueExpression!=null&&this.valueExpression.trim().length()>0) return this.valueExpression.trim();
		if(this.fixValue) throw new RuntimeException("fixValue filterSQLParam must set valueExpression");
		
		if(this.attributeName==null&&this.attributeName.trim().length()==0){
			return this.paramName.trim();
		}else{
			StringBuilder sb =  new StringBuilder();
			sb.append(paramName.trim()).append(".");
			if(this.getDataElement(rn).getTypeHandlerNode(rn).getSupportClassName().equals("boolean")){
				sb.append("is");
			}else{
				sb.append("get");
			}
			String tmp = this.attributeName.trim();
			sb.append(tmp.substring(0,1).toUpperCase(Locale.ENGLISH));
			if(tmp.length()>1)sb.append(tmp.substring(1));
			sb.append("()");
			return sb.toString();
		}
	}

	@Override
	public DataElementNode getDataElement(RootNode rn) {
		return rn.getDataElement(dataElementId);
	}

	@Override
	public boolean useTempalteVariable(RootNode rn) {
		return this.calcValue || (this.attributeName!=null&& this.attributeName.trim().length()>0);
	}

	@Override
	public boolean isDbNullable(RootNode rn) {
		return false;
	}

	@Override
	public OrmHandler getOrmHandler(RootNode rn) {
		return this.getDataElement(rn).getTypeHandlerNode(rn).;
	}
}
