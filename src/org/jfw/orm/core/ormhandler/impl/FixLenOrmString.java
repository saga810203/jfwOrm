package org.jfw.orm.core.ormhandler.impl;

public class FixLenOrmString extends OrmString{
	@Override
	protected int getSqlType() {
		return java.sql.Types.CHAR;
	}

}
