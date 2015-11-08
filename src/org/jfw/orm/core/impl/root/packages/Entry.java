package org.jfw.orm.core.impl.root.packages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jfw.orm.core.impl.RootNode;

public class Entry extends PersistentNode{
	private String parentId;
	private List<Column> columns = new LinkedList<Column>();
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Override
	public List<Column> getAllDefineColumn(RootNode rn){
		if(this.parentId!=null&& this.parentId.trim().length()>0){
			Entry parent = rn.getEntry(parentId);
			List<Column> oList = parent.getAllDefineColumn(rn);
			List<Column> list = new ArrayList<Column>(oList.size()+this.columns.size());
			list.addAll(oList);
			list.addAll(this.columns);
			return Collections.unmodifiableList(list);
			
		}
		return Collections.unmodifiableList(this.columns);
	}
	public void addColumn(Column col){
		this.columns.add(col);
	}
	public boolean removeColumn(String columnId){
		for(Column col:this.columns){
			if(col.getId().equals(columnId)){
				this.columns.remove(col);
				return true;
			}
		}
		return false;
	}
	public void removeColumn(Column col){
		this.removeColumn(col.getId());
	}
	@Override
	public String getFromSentence(RootNode rn) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getParentPersistentNodeId(RootNode rn) {
		return this.parentId;
	}
}
