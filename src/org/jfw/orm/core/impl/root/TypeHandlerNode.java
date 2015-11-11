package org.jfw.orm.core.impl.root;

import java.util.List;

import org.jfw.orm.core.Node;
import org.jfw.orm.core.OrmHandler;
import org.jfw.orm.core.impl.AbstractNode;
import org.jfw.orm.core.impl.RootNode;

public class TypeHandlerNode extends AbstractNode {
	public static final String PACKAGE_NAME="org.jfw.orm.core.ormhandler.impl";
	
	private String codeContent;
	private String supperHandlerId;
	private boolean abstracted;

	public String getCodeContent() {
		return codeContent;
	}

	public void setCodeContent(String codeContent) {
		this.codeContent = codeContent;
	}

	public String getSupperHandlerId() {
		return supperHandlerId;
	}

	public void setSupperHandlerId(String supperHandlerId) {
		this.supperHandlerId = supperHandlerId;
	}

	public boolean isAbstracted() {
		return abstracted;
	}

	public void setAbstracted(boolean abstracted) {
		this.abstracted = abstracted;
	}

	public String getSupportClassName(RootNode rn) {
		return this.getOrmHandler(rn).supportsClass().getName();
	}

	
	public OrmHandler getOrmHandler(RootNode rn){
		try {
			return rn.ormHandlers.get(this.id).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("can't create object with typeHandler.id="+this.id);
		}
	}
	
	
	public String getJavaCode()
	{
		return null;
	}

	@Override
	public List<Node> children() {
		return null;
	}

}
