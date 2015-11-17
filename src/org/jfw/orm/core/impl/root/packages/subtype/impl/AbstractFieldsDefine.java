package org.jfw.orm.core.impl.root.packages.subtype.impl;

import java.util.HashMap;
import java.util.Map;

import org.jfw.orm.core.impl.RootNode;
import org.jfw.orm.core.impl.root.packages.subtype.DataQueryMethod;
import org.jfw.orm.core.impl.root.packages.subtype.FieldsDefine;

public abstract class AbstractFieldsDefine implements FieldsDefine{
	protected RootNode rn;
	protected DataQueryMethod dqm;
	protected Map<String,String> attributes; 
	
	@Override
	public void init(DataQueryMethod dqm, RootNode rn) {
		this.rn = rn;
		this.dqm = dqm;
		this.initIntenal();
		
	}
	protected abstract void initIntenal();


	@Override
	public Map<String, String> getAttributes() {
		this.attributes = new HashMap<String,String>();
		this.stroeAttributes();
		Map<String,String> map = this.attributes;
		this.attributes = null;
		return map;
	}
	protected abstract void stroeAttributes();
	protected String readAttr(String name){
		return this.dqm.getAttributes().get(this.getClass().getName()+"."+name);
	}
	protected void saveAttr(String name,String val){
		this.attributes.put(this.getClass().getName()+"."+name, val);
	}

}
