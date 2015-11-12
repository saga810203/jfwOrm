package org.jfw.orm.core.ormhandler.impl;

import java.util.Map;

public class UnOrmBoolean extends BaseOrmHandler {
	private Map<String,Object> lmap ;
	private String valueEl;

	@Override
	public Class<?> supportsClass() {
		return boolean.class;
	}

	@Override
	public void readValue(StringBuilder sb, String beforCode, String afterCode,
			int colIndex, boolean dbNullable,
			Map<String, Object> localVarInMethod) {
		sb.append(beforCode == null ? "" : beforCode)
		.append("!\"0\".equals(rs.getString(").append(colIndex).append("))")
		.append(afterCode == null ? "" : afterCode);
		
	}

	@Override
	public void init(String valueEl, boolean userTempalteVar,
			boolean dbNullable, boolean useFilter, boolean dynamicFilter,
			Map<String, Object> localVarInMethod) {
		lmap = localVarInMethod;
		this.valueEl =valueEl;
		
	}

	@Override
	public void prepare(StringBuilder sb) {
		this.checkParamIndex(sb, this.lmap);
		
		
	}

	@Override
	public void checkNull(StringBuilder sb) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void writeValue(StringBuilder sb) {
		sb.append("if(").append(this.valueEl).append("){\r\n")
		.append("ps")
		
	}

	@Override
	public boolean isReplaceResource() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void replaceResource(StringBuilder sb) {
		// TODO Auto-generated method stub
		
	}

}
