package org.jfw.orm.core.impl.root.packages.subtype.impl;


public class ViewFieldsDefine extends PersistentObjectFieldsDefine{
	private String fromSentence;

	@Override
	public String getFromSentence() {
		return this.fromSentence;
	}

	@Override
	public void initIntenal() {
		super.initIntenal();		
		this.fromSentence = rn.getView(this.poid).getFromSentence();
	}
}

