package org.jfw.orm.core.impl.root.packages.subtype;

import java.util.ArrayList;
import java.util.List;

import org.jfw.orm.core.Node;
import org.jfw.orm.core.impl.AbstractNode;
import org.jfw.orm.core.impl.RootNode;

public abstract class DataOperateMethod extends AbstractNode {
	private String persistentNodeId;
	private List<MethodParam> params;
	

	public String getPersistentNodeId() {
		return persistentNodeId;
	}

	public void setPersistentNodeId(String persistentNodeId) {
		this.persistentNodeId = persistentNodeId;
	}

	public String getJavaMethodName() {
		return this.code;
	}

	public abstract String getReturnType();

	
	public abstract  void prepare(StringBuilder sb,RootNode rn);
	public abstract boolean isDynamicSql(RootNode rn);
	public abstract void buildParams(StringBuilder sb, RootNode rn);
	public abstract void buildHandleResult(StringBuilder sb,RootNode rn);
	public abstract void buildSql(StringBuilder sb,RootNode rn);
	public abstract void clean();
	public void generateMethodCode(StringBuilder sb, RootNode rn){
		
		
		
		this.prepare(sb, rn);
		
		this.buildSql(sb, rn);
		sb.append("java.sql.PreparedStatement ps = con.prepareStatement(sql");
		if (this.isDynamicSql(rn)) {
			sb.append(".toString()");
		}
		sb.append(");");
		
		sb.append("try{");
		
		this.buildParams(sb, rn);
		
		this.buildHandleResult(sb, rn);
		
		sb.append("}finally{try{ps.close();}catch(Exception e){}");
		sb.append("}");	
		this.clean();
	}

	public List<MethodParam> getParams() {
		return params;
	}

	public void setParams(List<MethodParam> params) {
		this.params = params;
	}

	public void addParam(MethodParam mp) {
		if (this.params == null)
			this.params = new ArrayList<MethodParam>();
		this.params.add(mp);
	}

	public void removeParam(String name) {
		if (this.params == null)
			return;
		for (MethodParam mp : this.params) {
			if (mp.getName().equals(name)) {
				this.params.remove(mp);
				return;
			}
		}
	}

	

	@Override
	public List<Node> children() {
		return null;
	}

}
