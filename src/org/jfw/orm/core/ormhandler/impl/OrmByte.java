package org.jfw.orm.core.ormhandler.impl;

public class OrmByte extends AbstractOrmHandler{

	@Override
	public Class<?> supportsClass() {
		return Byte.class;
	}

	@Override
	public String getReadMethod() {
		return "getByte";
	}

	@Override
	public String getWriteMethod() {
		return "setByte";
	}

	@Override
	protected int getSqlType() {
		return java.sql.Types.TINYINT;
	}

}
