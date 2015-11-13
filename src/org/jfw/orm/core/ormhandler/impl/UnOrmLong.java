package org.jfw.orm.core.ormhandler.impl;

public class UnOrmLong extends OrmLong{

	@Override
	public Class<?> supportsClass() {
		return long.class;
	}

}
