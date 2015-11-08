package org.jfw.orm.core.impl.root.packages.subtype;


import java.util.List;

import org.jfw.orm.core.OrmHandler;
import org.jfw.orm.core.Utils;
import org.jfw.orm.core.impl.RootNode;

public abstract class DataQueryMethod extends DataOperateMethod {
	private boolean dynamicFilter = true;
	private String whereSql;
	//private Map<String,String> whereValue 
	
	
	
	
	
	@Override
	public boolean isDynamicSql(RootNode rn){
		if(!dynamicFilter) return false;
		List<SQLParam> params = this.getWhereParams();
		if(params==null || params.isEmpty()) return false;
		for(SQLParam param:params){
			if(!param.isPrimitive(rn)) return true;
		}
		return false;
	}
	public abstract void writeQueryFields(StringBuilder sb ,RootNode rn);
	
	public abstract void writeFromSentence(StringBuilder sb ,RootNode rn);
	
	public abstract void writeWhereSentence(StringBuilder sb ,RootNode rn);
	
	public abstract void writeOtherSentence(StringBuilder sb,RootNode rn);
	
	public abstract List<SQLParam> getWhereParams();
	
	
	public abstract void initReturnValue(StringBuilder sb,RootNode rn);
	public abstract boolean isMultiRows(RootNode rn);
	public abstract void buildResultSet(StringBuilder sb,RootNode rn) ;
	@Override
	public void buildHandleResult(StringBuilder sb, RootNode rn) {
		sb.append("java.sql.ResultSet rs = ps.executeQuery();");
		sb.append("try{");
		this.initReturnValue(sb, rn);
		if(this.isMultiRows(rn)){
			sb.append("while(rs.next()){");
		}else{
			sb.append("if(rs.next(){");
		}
		this.buildResultSet(sb, rn);
		sb.append("}return ret;");
		sb.append("}finally{try{rs.close();}catch(Exception e){}}");		
	}

	@Override
	public void buildParams(StringBuilder sb, RootNode rn) {
		List<SQLParam> params = this.getWhereParams();
		if(params==null||params.isEmpty())return;
		sb.append("int paramIndex = 1;");
		for(int i = 0 ; i < params.size() ; ++i ){
			SQLParam sp = params.get(i);			
			OrmHandler oh = sp.getOrmHandler();
			if(this.isDynamicSql(rn)){
				if(Utils.isPrimitive(oh.supportsClass())){
					oh.writeValue(sb);
				}else{
					sb.append("if(!");
					oh.checkNull(sb);
					sb.append("){");
					oh.writeValue(sb);
					sb.append("}");			
				}
			}else{
				oh.writeValue(sb);
			}
		}
	}
	@Override
	public void buildSql(StringBuilder sb, RootNode rn) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	


}
