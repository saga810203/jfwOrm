package org.jfw.orm.core.impl.root.packages.subtype;

import org.jfw.orm.core.OrmHandler;
import org.jfw.orm.core.impl.RootNode;
import org.jfw.orm.core.impl.root.DataElementNode;

public  class WhereSQLParam extends SQLParam{
	private String valueExpression;
	private String paramName;
	private String attributeName;
	
	private String filterExpression;
	private String columnName;
	private String operator;
	
	private boolean inDynamicFilter= false;
	
	private boolean calcValue = false;
	
	
	
	
	
	public  String getFilterExpression(){
	   if(this.filterExpression!=null&& this.filterExpression.trim().length()>0) return this.filterExpression.trim();
	   return this.columnName.trim()+" "+this.operator.trim()+" ?";
		   
	}

	@Override
	public String getValueExpression() {
//		if(this.valueExpression!=null&&this.valueExpression.trim().length()>0) return this.valueExpression.trim();
//		if(this.attributeName==null&&this.attributeName.trim().length()==0){
//			return this.paramName.trim();
//		}else{
//			
//		}
		return null;
	}

	@Override
	public DataElementNode getDataElement(RootNode rn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean useTempalteVariable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDbNullable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OrmHandler getOrmHandler() {
		// TODO Auto-generated method stub
		return null;
	}
}
