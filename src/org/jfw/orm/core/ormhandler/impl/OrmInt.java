package org.jfw.orm.core.ormhandler.impl;


public class OrmInt extends AbstractOrmHandler{

	@Override
	public Class<?> supportsClass() {
		return java.lang.Integer.class;
	}

	@Override
	public String getReadMethod() {
		return "getInt";
	}

	@Override
	public String getWriteMethod() {
		return "setInt";
	}

	@Override
	protected int getSqlType() {
		return java.sql.Types.INTEGER;
	}
}
