package org.jfw.orm.core;

import org.eclipse.swt.widgets.Composite;

public interface NodeView {
	void setParent(Composite composite);
	void setNode(Node node);
	void show();
}
