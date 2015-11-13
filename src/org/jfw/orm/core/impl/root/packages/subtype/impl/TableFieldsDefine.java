package org.jfw.orm.core.impl.root.packages.subtype.impl;


public class TableFieldsDefine extends PersistentObjectFieldsDefine{
	private transient String table;
	
	@Override
	public void initIntenal() {
		super.initIntenal();
		this.table = rn.getTable(this.poid).getCode().trim();
	}
	@Override
	public String getFromSentence() {
		return this.table;
	}

}
