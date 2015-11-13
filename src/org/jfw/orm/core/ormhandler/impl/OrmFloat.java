package org.jfw.orm.core.ormhandler.impl;

public class OrmFloat extends AbstractOrmHandler{

	@Override
	public Class<?> supportsClass() {
		return Float.class;
	}

	@Override
	public String getReadMethod() {
		return "getFloat";
	}

	@Override
	public String getWriteMethod() {
		return "setFloat";
	}

	@Override
	protected int getSqlType() {
		return java.sql.Types.FLOAT;
	}

}
