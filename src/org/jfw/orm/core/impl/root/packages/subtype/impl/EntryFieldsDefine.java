package org.jfw.orm.core.impl.root.packages.subtype.impl;


public class EntryFieldsDefine extends PersistentObjectFieldsDefine{
	private String tableid;
	private String table;

	@Override
	public String getFromSentence() {
		return this.table;
	}

	@Override
	protected void initIntenal(){
		super.initIntenal();
		this.tableid =readAttr("tableid");
		this.table = rn.getTable(this.tableid).getCode().trim();
	}

	@Override
	protected void stroeAttributes() {
		super.stroeAttributes();
		this.saveAttr("tableid",this.tableid);
	}

}
