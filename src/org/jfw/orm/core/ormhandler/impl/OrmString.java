package org.jfw.orm.core.ormhandler.impl;

public class OrmString extends AbstractOrmHandler{

	@Override
	public Class<?> supportsClass() {
		return String.class;
	}

	@Override
	public String getReadMethod() {
		return "getString";
	}

	@Override
	public String getWriteMethod() {
		return "setString";
	}

	@Override
	protected int getSqlType() {
		return java.sql.Types.VARCHAR;
	}

}
