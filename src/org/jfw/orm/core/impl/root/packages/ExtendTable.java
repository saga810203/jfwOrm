package org.jfw.orm.core.impl.root.packages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jfw.orm.core.impl.RootNode;

public class ExtendTable extends PersistentNode{

	private String baseTableId;
	private boolean baseIsExtendTable;

	private List<CalcColumn> columns = new LinkedList<CalcColumn>();	
	
	public String getBaseTableId() {
		return baseTableId;
	}
	public void setBaseTableId(String baseTableId) {
		this.baseTableId = baseTableId;
	}
	
	public List<CalcColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<CalcColumn> columns) {
		this.columns = columns;
	}
	public boolean isBaseIsExtendTable() {
		return baseIsExtendTable;
	}
	public void setBaseIsExtendTable(boolean baseIsExtendTable) {
		this.baseIsExtendTable = baseIsExtendTable;
	}
	@Override
	public List<Column> getAllDefineColumn(RootNode rn) {
		List<Column> oList = null;
		if(this.baseIsExtendTable){
			ExtendTable et = rn.getExtendTable(baseTableId);
			oList = et.getAllDefineColumn(rn);
		}else{
		Table parent =rn.getTable(baseTableId);
		oList = parent.getAllDefineColumn(rn);}
		List<Column> list = new ArrayList<Column>(oList.size()+this.columns.size());
		list.addAll(oList);
		list.addAll(this.columns);
		return Collections.unmodifiableList(list);
	}
	@Override
	public String getFromSentence(RootNode rn) {
		Table t = rn.getTable(this.baseTableId);
		return t.getFromSentence(rn);
	}

	@Override
	public String getParentPersistentNodeId(RootNode rn) {
		return this.baseTableId;
	}
}
