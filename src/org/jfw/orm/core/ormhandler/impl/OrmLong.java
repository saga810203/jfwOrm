package org.jfw.orm.core.ormhandler.impl;

public class OrmLong extends AbstractOrmHandler{

	@Override
	public Class<?> supportsClass() {
		return Long.class;
	}

	@Override
	public String getReadMethod() {
		return "getLong";
	}

	@Override
	public String getWriteMethod() {
		return "setLong";
	}

	@Override
	protected int getSqlType() {
		return java.sql.Types.BIGINT;
	}
}
