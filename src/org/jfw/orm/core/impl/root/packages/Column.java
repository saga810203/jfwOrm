package org.jfw.orm.core.impl.root.packages;

import java.util.List;

import org.jfw.orm.core.Node;
import org.jfw.orm.core.impl.AbstractNode;

public class Column extends AbstractNode{
	private String dataEleId;
	private boolean nullable;
	private String comment;
	private boolean defaultQuery;

	public boolean isDefaultQuery() {
		return defaultQuery;
	}

	public void setDefaultQuery(boolean defaultQuery) {
		this.defaultQuery = defaultQuery;
	}

	public String getDataEleId() {
		return dataEleId;
	}

	public void setDataEleId(String dataEleId) {
		this.dataEleId = dataEleId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	@Override
	public List<Node> children() {
		// TODO Auto-generated method stub
		return null;
	} 
	
}
