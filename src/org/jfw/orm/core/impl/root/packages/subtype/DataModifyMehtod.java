package org.jfw.orm.core.impl.root.packages.subtype;

import java.util.HashMap;
import java.util.Map;

import org.jfw.orm.core.impl.RootNode;

public class DataModifyMehtod extends DataOperateMethod {
	private String dataModifyDefineClass;
	private DataModifyDefine dmd = null;
	private Map<String, String> attributes = new HashMap<String, String>();
	

	

	@Override
	public String getReturnType() {
		return "int";
	}

	@Override
	public void prepare(StringBuilder sb, RootNode rn) {
		try {
			this.dmd = (DataModifyDefine) Class.forName(this.dataModifyDefineClass)
					.newInstance();
			this.dmd.init(this, rn);
		} catch (Exception e) {
			throw new RuntimeException("caeate fieldsDefine instance error:"
					+ this.dataModifyDefineClass, e);
		}
		this.dmd.init(this, rn);		
	}

	@Override
	public boolean isDynamicSql(RootNode rn) {
		return this.dmd.isDynamicSql();
	}

	@Override
	public void buildParams(StringBuilder sb, RootNode rn) {
		this.dmd.buildParams(sb);	
	}

	@Override
	public void buildHandleResult(StringBuilder sb, RootNode rn) {
		sb.append("return ps.executeUpdate();\r\n");		
	}

	@Override
	public void buildSql(StringBuilder sb, RootNode rn) {
		this.dmd.buildParams(sb);
	}

	@Override
	public void cleanAfterGenerated() {
		this.dmd = null;		
	}
	public String getDataModifyDefineClass() {
		return dataModifyDefineClass;
	}

	public void setDataModifyDefineClass(String dataModifyDefineClass) {
		this.dataModifyDefineClass = dataModifyDefineClass;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void stroeAttributes(DataModifyDefine dmd) {
		this.attributes.clear();
		this.attributes.putAll(dmd.getAttributes());
	}
}
