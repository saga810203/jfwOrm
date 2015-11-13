package org.jfw.orm.core.impl.root.packages.subtype.impl;

import java.util.ArrayList;
import java.util.List;

import org.jfw.orm.core.impl.root.packages.CalcColumn;
import org.jfw.orm.core.impl.root.packages.Column;

public class SingleColumnFieldsDefine extends AbstractFieldsDefine {
	protected String fieldSentence;
	protected String fromSentence;
	protected String dataElementId;

	
	@Override 
	protected void initIntenal(){
		this.fieldSentence = this.readAttr("fieldSentence");
		this.fromSentence = this.readAttr("fromSentence");
		this.dataElementId = this.readAttr("dataElementId");

	}
	

	@Override
	public String getResultSetType() {
		return this.rn.getDataElement(this.dataElementId).getTypeHandlerNode(rn).getOrmHandler(rn).supportsClass().getName();
	}

	@Override
	public List<Column> getColumns() {
		List<Column> list = new ArrayList<Column>();
		CalcColumn col = new CalcColumn();
		col.setExpression(this.fieldSentence.trim());
		list.add(col);
		return list;
	}

	@Override
	public String getFromSentence() {
		return this.fromSentence;
	}


	@Override
	protected void stroeAttributes() {
		this.saveAttr("fieldSentence",this.fieldSentence);
		this.saveAttr("fromSentence",this.fromSentence);
		this.saveAttr("dataElementId",this.fromSentence);
	}

	

}
