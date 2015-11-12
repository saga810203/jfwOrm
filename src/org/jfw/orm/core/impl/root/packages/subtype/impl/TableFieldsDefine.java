package org.jfw.orm.core.impl.root.packages.subtype.impl;

import org.jfw.orm.core.impl.RootNode;
import org.jfw.orm.core.impl.root.packages.subtype.DataQueryMethod;

public class TableFieldsDefine extends PersistentObjectFieldsDefine{
	private transient String table;
	
	@Override
	public void init(DataQueryMethod dqm, RootNode rn) {
		super.init(dqm, rn);
		this.table = rn.getTable(this.poid).getCode().trim();
	}
	@Override
	public String getFromSentence() {
		return this.table;
	}

}
