package org.jfw.orm.core.impl.root.packages.subtype.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfw.orm.core.impl.RootNode;
import org.jfw.orm.core.impl.root.PackageNode;
import org.jfw.orm.core.impl.root.packages.Column;
import org.jfw.orm.core.impl.root.packages.PersistentNode;
import org.jfw.orm.core.impl.root.packages.subtype.DataQueryMethod;
import org.jfw.orm.core.impl.root.packages.subtype.FieldsDefine;

public abstract class PersistentObjectFieldsDefine implements FieldsDefine{
	
	
	protected String poid;
	protected transient RootNode rn;
	protected transient DataQueryMethod dqm;
	protected transient List<Column> cols;
	protected transient String resultSetType; 

	@Override
	public void init(DataQueryMethod dqm, RootNode rn) {
		this.rn = rn;
		this.dqm = dqm;
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
	public Map<String, String> getAttributes() {
		Map<String,String> map = new HashMap<String,String>();
		this.saveAttr("poid", this.poid, map);
		return map;
	}
	public String readAttr(String name){
		return this.dqm.getAttributes().get(this.getClass().getName()+"."+name);
	}
	public void saveAttr(String name,String val,Map<String,String> map){
		map.put(this.getClass().getName()+"."+name, val);
	}
}
