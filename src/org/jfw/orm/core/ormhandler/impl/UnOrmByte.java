package org.jfw.orm.core.ormhandler.impl;

public class UnOrmByte extends OrmByte{
	@Override
	public Class<?> supportsClass() {
		return byte.class;
	}

}
