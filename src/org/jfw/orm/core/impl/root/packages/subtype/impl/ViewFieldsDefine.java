package org.jfw.orm.core.impl.root.packages.subtype.impl;

import java.util.Map;

import org.jfw.orm.core.impl.RootNode;
import org.jfw.orm.core.impl.root.packages.subtype.DataQueryMethod;

public class ViewFieldsDefine extends PersistentObjectFieldsDefine{
	private transient String fromSentence;

	@Override
	public String getFromSentence() {
		return this.fromSentence;
	}

	@Override
	public void init(DataQueryMethod dqm, RootNode rn) {
		super.init(dqm, rn);		
		this.fromSentence = rn.getView(this.poid).getFromSentence();
	}
}

