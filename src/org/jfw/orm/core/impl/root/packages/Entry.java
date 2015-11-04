package org.jfw.orm.core.impl.root.packages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Entry extends PackageNode{
	private String parentId;
	private String comment;
	private List<Column> columns = new LinkedList<Column>();
	private transient Entry parent;
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Entry getParent() {
		return parent;
	}
	public void setParent(Entry parent) {
		this.parent = parent;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<Column> getColumns() {
		if(this.parent==null){
			return Collections.unmodifiableList(this.columns);
		}else{
			List<Column> oList = this.parent.getColumns();
			List<Column> list = new ArrayList<Column>(oList.size()+this.columns.size());
			list.addAll(oList);
			list.addAll(this.columns);
			return Collections.unmodifiableList(list);
		}
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
}
