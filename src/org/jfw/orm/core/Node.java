package org.jfw.orm.core;

import java.util.List;

public interface Node {
	int size();
	List<Node> children();
	String getId();
	String getDisplay();
	String getCode();
}
