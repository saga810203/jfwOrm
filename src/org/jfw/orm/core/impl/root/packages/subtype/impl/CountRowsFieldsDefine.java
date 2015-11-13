package org.jfw.orm.core.impl.root.packages.subtype.impl;

import java.util.ArrayList;
import java.util.List;

import org.jfw.orm.core.impl.root.packages.CalcColumn;
import org.jfw.orm.core.impl.root.packages.Column;

public class CountRowsFieldsDefine extends AbstractFieldsDefine{
	protected String entryId;
	protected boolean isTable;
	protected String dataElementId;

	@Override
	public String getResultSetType() {
		return this.rn.getDataElement(dataElementId).getTypeHandlerNode(rn).getOrmHandler(rn).supportsClass().getName();
	}

	@Override
	public List<Column> getColumns() {
		List<Column> cols = new ArrayList<Column>();
		CalcColumn col = new CalcColumn();
		col.setExpression("COUNT(1)");
		cols.add(col);
		return cols;
	}

	@Override
	public String getFromSentence() {
		if(this.isTable)return this.rn.getTable(entryId).getCode();
		return this.rn.getView(this.entryId).getFromSentence();
	}

	@Override
	protected void initIntenal() {
		this.dataElementId=this.readAttr("dataElementId");
		this.entryId = this.readAttr("entryId");
		this.isTable = "1".equals(this.readAttr("isTable"));
		
	}

	@Override
	protected void stroeAttributes() {
		this.saveAttr("dataElementId", this.dataElementId);
		this.saveAttr("entryId",this.entryId);
		this.saveAttr("isTable",this.isTable?"1":"0");		
	}

}
