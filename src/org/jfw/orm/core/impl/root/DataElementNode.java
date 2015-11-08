package org.jfw.orm.core.impl.root;

import java.util.List;

import org.jfw.orm.core.Node;
import org.jfw.orm.core.impl.AbstractNode;
import org.jfw.orm.core.impl.RootNode;

public class DataElementNode extends AbstractNode {
	private String dbType;
	private int dbTypeLength=0;
	private int dbTypePrecision=0;
	private String typeHandlerId;
	private boolean nullable;
	private boolean inInsert;
	private String fixSqlValueWithInsert;
	private boolean inUpdate;
	private String fixSqlValueWithUpdate;
	private boolean searchable;
	

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public boolean isInInsert() {
		return inInsert;
	}

	public void setInInsert(boolean inInsert) {
		this.inInsert = inInsert;
	}

	public String getFixSqlValueWithInsert() {
		return fixSqlValueWithInsert;
	}

	public void setFixSqlValueWithInsert(String fixSqlValueWithInsert) {
		this.fixSqlValueWithInsert = fixSqlValueWithInsert;
	}

	public boolean isInUpdate() {
		return inUpdate;
	}

	public void setInUpdate(boolean inUpdate) {
		this.inUpdate = inUpdate;
	}

	public String getFixSqlValueWithUpdate() {
		return fixSqlValueWithUpdate;
	}

	public void setFixSqlValueWithUpdate(String fixSqlValueWithUpdate) {
		this.fixSqlValueWithUpdate = fixSqlValueWithUpdate;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public int getDbTypeLength() {
		return dbTypeLength;
	}

	public void setDbTypeLength(int dbTypeLength) {
		this.dbTypeLength = dbTypeLength;
	}

	public int getDbTypePrecision() {
		return dbTypePrecision;
	}

	public void setDbTypePrecision(int dbTypePrecision) {
		this.dbTypePrecision = dbTypePrecision;
	}

	public String getTypeHandlerId() {
		return typeHandlerId;
	}

	public void setTypeHandlerId(String typeHandlerId) {
		this.typeHandlerId = typeHandlerId;
	}
	
	public TypeHandlerNode getTypeHandlerNode(RootNode rn){
		return rn.getTypeHandlerNode(this.typeHandlerId);
	}

	@Override
	public List<Node> children() {
		return null;
	}
	

}
