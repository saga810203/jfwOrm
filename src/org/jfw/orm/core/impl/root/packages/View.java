package org.jfw.orm.core.impl.root.packages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jfw.orm.core.impl.RootNode;

public class View extends PersistentNode {

	private String tableId;
	private String fromSentence;
	private List<CalcColumn> columns = new LinkedList<CalcColumn>();

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getFromSentence() {
		return fromSentence;
	}

	public void setFromSentence(String fromSentence) {
		this.fromSentence = fromSentence;
	}

	public List<CalcColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<CalcColumn> columns) {
		this.columns = columns;
	}

	@Override
	public List<Column> getAllDefineColumn(RootNode rn) {
		List<Column> ret = new ArrayList<Column>();

		if (this.tableId != null && this.tableId.trim().length() > 0) {
			ret.addAll(rn.getTable(tableId).getAllDefineColumn(rn));
		}
		ret.addAll(this.columns);

		return Collections.unmodifiableList(ret);
	}

	@Override
	public String getFromSentence(RootNode rn) {
		return this.fromSentence;
	}


	@Override
	public String getParentPersistentNodeId(RootNode rn) {
		return this.tableId;
	}
}
