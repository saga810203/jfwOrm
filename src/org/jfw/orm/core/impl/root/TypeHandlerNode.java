package org.jfw.orm.core.impl.root;

import java.util.List;

import org.jfw.orm.core.Node;
import org.jfw.orm.core.impl.AbstractNode;

public class TypeHandlerNode extends AbstractNode {
	private String codeContent;
	private String supperHandlerId;
	private boolean abstracted;
	private String supportClassName;
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

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

	public String getSupportClassName() {
		return supportClassName;
	}

	public void setSupportClassName(String supportClassName) {
		this.supportClassName = supportClassName;
	}

	@Override
	public List<Node> children() {
		return null;
	}

}
