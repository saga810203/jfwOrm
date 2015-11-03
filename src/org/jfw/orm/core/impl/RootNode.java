package org.jfw.orm.core.impl;

import java.util.LinkedList;
import java.util.List;

import org.jfw.orm.core.Node;
import org.jfw.orm.core.impl.root.DataElementNode;
import org.jfw.orm.core.impl.root.PackageNode;
import org.jfw.orm.core.impl.root.TypeHandlerNode;

public class RootNode extends AbstractNode{
	
	private List<PackageNode> pacages = new LinkedList<PackageNode>();
	private List<TypeHandlerNode> handlers = new LinkedList<TypeHandlerNode>();
	private List<DataElementNode> dataEles = new LinkedList<DataElementNode>();
	
	
	public DataElementNode getDataElement(String id){
		for(DataElementNode de:this.dataEles)
		{
			if(de.getId().equals(id)) return de;
		}
		return null;
	}
	public PackageNode getPackageNode(String id){
		for(PackageNode pn:this.pacages){
			if(pn.getId().equals(id)) return pn;
		}
		return null;		
	}
	public TypeHandlerNode getTypeHandlerNode(String id){
		for(TypeHandlerNode thn:this.handlers){
			if(thn.getId().equals(id)) return thn;
		}
		return null;		
	}
	
	
	

	@Override
	public List<Node> children() {
		// TODO Auto-generated method stub
		return null;
	}

	
	


}
