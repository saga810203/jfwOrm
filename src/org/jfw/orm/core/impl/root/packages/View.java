package org.jfw.orm.core.impl.root.packages;

import java.util.LinkedList;
import java.util.List;

public class View extends PackageNode{
	
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
}
