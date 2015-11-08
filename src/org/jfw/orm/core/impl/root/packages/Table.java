package org.jfw.orm.core.impl.root.packages;

import java.util.LinkedList;
import java.util.List;

import org.jfw.orm.core.impl.RootNode;


public class Table extends Entry{
	
	private List<String> keys = new LinkedList<String>();
	public List<String> getKeys() {
		return keys;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	@Override
	public String getFromSentence(RootNode rn) {
		return this.code;
	}

	
	
}
