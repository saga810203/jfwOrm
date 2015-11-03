package org.jfw.orm.core.impl.root.packages;

import java.util.LinkedList;
import java.util.List;

import org.jfw.orm.core.Node;
import org.jfw.orm.core.impl.AbstractNode;

public class ExtendTable extends AbstractNode{
	private String baseTableId;
	private String comment;
	private List<CalcColumn> columns = new LinkedList<CalcColumn>();	
	
	public String getBaseTableId() {
		return baseTableId;
	}
	public void setBaseTableId(String baseTableId) {
		this.baseTableId = baseTableId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<CalcColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<CalcColumn> columns) {
		this.columns = columns;
	}
	@Override
	public List<Node> children() {
		// TODO Auto-generated method stub
		return null;
	}
}
