package org.jfw.orm.core.ormhandler.impl;

public class OrmDouble extends AbstractOrmHandler{

	@Override
	public Class<?> supportsClass() {
		return Double.class;
	}

	@Override
	public String getReadMethod() {
		return "getDouble";
	}

	@Override
	public String getWriteMethod() {
		return "setDouble";
	}

	@Override
	protected int getSqlType() {
		return java.sql.Types.DOUBLE;
	}

}
