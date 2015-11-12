package org.jfw.orm.core.impl.root.packages.subtype.impl;

import java.util.Map;

import org.jfw.orm.core.impl.RootNode;
import org.jfw.orm.core.impl.root.packages.subtype.DataQueryMethod;

public class EntryFieldsDefine extends PersistentObjectFieldsDefine{
	private String tableid;
	private transient String table;

	@Override
	public String getFromSentence() {
		return this.table;
	}

	@Override
	public void init(DataQueryMethod dqm, RootNode rn) {
		super.init(dqm, rn);
		this.tableid =readAttr("tableid");
		this.table = rn.getTable(this.tableid).getCode().trim();
	}

	@Override
	public Map<String, String> getAttributes() {
		Map<String, String> map = super.getAttributes();
		this.saveAttr("tableid",this.tableid, map);
		return map;
	}

}
