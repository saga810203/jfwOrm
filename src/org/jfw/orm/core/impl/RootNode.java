package org.jfw.orm.core.impl;

import java.util.LinkedList;
import java.util.List;

import org.jfw.orm.core.Node;
import org.jfw.orm.core.impl.container.DataElementNode;
import org.jfw.orm.core.impl.container.PackageNode;
import org.jfw.orm.core.impl.container.TypeHandlerNode;

public class RootNode extends AbstractNode{
	
	private List<PackageNode> pacages = new LinkedList<PackageNode>();
	private List<TypeHandlerNode> handlers = new LinkedList<TypeHandlerNode>();
	private List<DataElementNode> dataEles = new LinkedList<DataElementNode>();
	
	
	

	@Override
	public List<Node> children() {
		// TODO Auto-generated method stub
		return null;
	}

	
	


}
