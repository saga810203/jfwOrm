package org.jfw.orm.core.impl;

import java.util.List;

import org.jfw.orm.core.Node;

public abstract class AbstractNode implements Node {
	protected String id;
	protected String code;
	protected String display;	
	

	@Override
	public final int size() {
		List<Node> childs = this.children();
		return null==childs?0:childs.size() ;
	}
	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
}
