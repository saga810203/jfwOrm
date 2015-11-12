package org.jfw.orm.core.ormhandler.impl;

/**
 * 非空 int 
 * @author pengjia
 *
 */
public class UnOrmInt extends OrmInt {

	@Override
	public Class<?> supportsClass() {
		return int.class;
	}


}
