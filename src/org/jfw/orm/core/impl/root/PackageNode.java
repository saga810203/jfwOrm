package org.jfw.orm.core.impl.root;

import java.util.LinkedList;
import java.util.List;

import org.jfw.orm.core.Node;
import org.jfw.orm.core.impl.AbstractNode;
import org.jfw.orm.core.impl.root.packages.Entry;
import org.jfw.orm.core.impl.root.packages.ExtendTable;
import org.jfw.orm.core.impl.root.packages.Table;
import org.jfw.orm.core.impl.root.packages.View;


public class PackageNode extends AbstractNode {
	private List<Entry> entrys = new LinkedList<Entry>();
	private List<Table> tables = new LinkedList<Table>();
	private List<ExtendTable> extendTables = new LinkedList<ExtendTable>();
	private List<View> views = new LinkedList<View>();

	@Override
	public List<Node> children() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Entry> getEntrys() {
		return entrys;
	}

	public void setEntrys(List<Entry> entrys) {
		this.entrys = entrys;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public List<ExtendTable> getExtendTables() {
		return extendTables;
	}

	public void setExtendTables(List<ExtendTable> extendTables) {
		this.extendTables = extendTables;
	}

	public List<View> getViews() {
		return views;
	}

	public void setViews(List<View> views) {
		this.views = views;
	}
	public boolean exists(String childId){
		for(AbstractNode en:this.entrys){
			if(en.getId().equals(childId)) return true;
		}
		for(AbstractNode en:this.tables){
			if(en.getId().equals(childId)) return true;
		}
		for(AbstractNode en:this.extendTables){
			if(en.getId().equals(childId)) return true;
		}
		for(AbstractNode en:this.views){
			if(en.getId().equals(childId)) return true;
		}
		return false;
	}
}
