package org.jfw.orm.core.impl.root.packages.subtype.impl;

import java.util.ArrayList;
import java.util.List;

import org.jfw.orm.core.impl.root.PackageNode;
import org.jfw.orm.core.impl.root.packages.Column;
import org.jfw.orm.core.impl.root.packages.PersistentNode;

public abstract class PersistentObjectFieldsDefine extends AbstractFieldsDefine{
	
	
	protected String poid;
	protected transient List<Column> cols;
	protected transient String resultSetType; 

	@Override
	protected void initIntenal() {
		this.poid = this.readAttr("poid");
		PersistentNode pn= this.rn.getPersistentNode(this.poid);
		PackageNode pgn = this.rn.getPackageNodeWithChildId(this.poid);
		this.resultSetType = pgn.getCode().trim()+"."+pn.getCode().trim();
		this.cols = new ArrayList<Column>();
		List<Column> list = pn.getAllDefineColumn(rn);
		for(int i = 0 ; i < list.size() ; ++i){
			Column col = list.get(i);
			if(col.isDefaultQuery()){
				this.cols.add(col);
			}
		}
	}

	@Override
	public String getResultSetType() {
		return this.resultSetType;
	}

	@Override
	public List<Column> getColumns() {
		return this.cols;
	}
	@Override
	protected void stroeAttributes() {
		this.saveAttr("poid", this.poid);
	}
}
