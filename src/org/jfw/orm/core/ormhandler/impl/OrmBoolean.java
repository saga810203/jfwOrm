package org.jfw.orm.core.ormhandler.impl;

import java.util.Map;

public final class OrmBoolean extends BaseOrmHandler{
	protected String valueEl;
	protected boolean userTempalteVar;
	protected boolean dbNullable;
	protected boolean useFilter;
	protected boolean dynamicFilter;
	protected Map<String, Object> localVarInMethod;
	protected String isNullVariable;
	protected String cacheValueVariable;
	
	@Override
	public Class<?> supportsClass() {
		return Boolean.class;
	}

	@Override
	public void readValue(StringBuilder sb, String beforCode, String afterCode,
			int colIndex, boolean dbNullable,
			Map<String, Object> localVarInMethod) {
		
		String localVar = this.getTempalteVariableName(localVarInMethod);
		sb.append("Boolean ").append(localVar).append(" =\"0\".equals(rs.getString(").append(colIndex)
				.append("));\r\n")
				.append("if(rs.wasNull()){\r\n")
				.append(localVar).append(" = null;\r\n}\r\n")
				.append(beforCode == null ? "" : beforCode)
				.append(localVar)
				.append(afterCode == null ? "" : afterCode);		
	}

	@Override
	public void init(String valueEl, boolean userTempalteVar,
			boolean dbNullable, boolean useFilter, boolean dynamicFilter,
			Map<String, Object> localVarInMethod) {
		this.valueEl = valueEl;
		this.useFilter = useFilter;
		this.userTempalteVar = userTempalteVar;
		this.dbNullable = dbNullable;
		this.localVarInMethod = localVarInMethod;
		this.dynamicFilter = dynamicFilter;
	}

	@Override
	public void prepare(StringBuilder sb) {
		this.checkParamIndex(sb, this.localVarInMethod);
		this.cacheValueVariable = null;
		this.isNullVariable = null;
		if (this.supportsClass().isPrimitive())
			return;
		if ((!this.useFilter) && (!this.dbNullable))
			return;
		if(!this.dynamicFilter) return;

		this.isNullVariable = this
				.getTempalteVariableName(this.localVarInMethod);
		if (this.userTempalteVar) {
			this.cacheValueVariable = this
					.getTempalteVariableName(localVarInMethod);
			sb.append(this.supportsClass().getName()).append(" ")
					.append(this.cacheValueVariable).append(" = ")
					.append(this.valueEl).append(";\r\n");
		}
		sb.append("boolean ").append(this.isNullVariable).append(" = ");
		if (null == this.cacheValueVariable) {
			sb.append(" null == ").append(this.valueEl).append(";\r\n");
		} else {
			sb.append("null == ").append(this.cacheValueVariable)
					.append(";\r\n");
		}
		
	}

	@Override
	public void checkNull(StringBuilder sb) {
		if(null == this.isNullVariable) throw new UnsupportedOperationException();
		sb.append(this.isNullVariable);	
	}

	@Override
	public void writeValue(StringBuilder sb) {
		if(null==this.isNullVariable){
			sb.append("ps.setString(paramIndex++,");
			if(null == cacheValueVariable){
				sb.append(this.valueEl);
			}else{
				sb.append(this.cacheValueVariable);
			}
			sb.append(".booleanValue()?\"1\":\"0\");\r\n");
		}else{
			sb.append("if(").append(this.isNullVariable).append("){\r\n")
			.append("ps.setNull(paramIndex++,").append(java.sql.Types.CHAR).append(");\r\n")
			.append("}else{\r\n")
			.append("ps.setString(paramIndex++,");
			if(null == cacheValueVariable){
				sb.append(this.valueEl);
			}else{
				sb.append(this.cacheValueVariable);
			}
			sb.append(".booleanValue()?\"1\":\"0\");\r\n}\r\n");
		}
		
	}

	@Override
	public boolean isReplaceResource() {
		return false;
	}

	@Override
	public void replaceResource(StringBuilder sb) {
	}

}
