package org.jfw.orm.core.impl;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jfw.orm.core.Node;
import org.jfw.orm.core.OrmHandler;
import org.jfw.orm.core.impl.root.DataElementNode;
import org.jfw.orm.core.impl.root.PackageNode;
import org.jfw.orm.core.impl.root.TypeHandlerNode;
import org.jfw.orm.core.impl.root.packages.Entry;
import org.jfw.orm.core.impl.root.packages.ExtendTable;
import org.jfw.orm.core.impl.root.packages.PersistentNode;
import org.jfw.orm.core.impl.root.packages.Table;

public class RootNode extends AbstractNode{
	
	
	public final transient Map<String,Object> TemplateVariable = new HashMap<String,Object>();
	private List<PackageNode> pacages = new LinkedList<PackageNode>();
	private List<TypeHandlerNode> handlers = new LinkedList<TypeHandlerNode>();
	private List<DataElementNode> dataEles = new LinkedList<DataElementNode>();
	public final transient Map<String,Class<OrmHandler>> ormHandlers = new HashMap<String,Class<OrmHandler>>();
	
	
	
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
	public PackageNode getPackageNodeWithChildId(String childId){
		for(PackageNode pn:this.pacages){
			if(pn.exists(childId))return pn;
		}
		return null;		
	}
	public TypeHandlerNode getTypeHandlerNode(String id){
		for(TypeHandlerNode thn:this.handlers){
			if(thn.getId().equals(id)) return thn;
		}
		return null;		
	}
	public Entry getEntry(String id){
		for(PackageNode pn : this.pacages){
			for(Entry en:pn.getEntrys()){
				if(en.getId().equals(id)) return en;
			}
			for(Entry en:pn.getTables()){
				if(en.getId().equals(id)) return en;
			}
		}
		return null;
	}
	public Table getTable(String id){
		for(PackageNode pn : this.pacages){
			for(Table en:pn.getTables()){
				if(en.getId().equals(id)) return en;
			}
		}
		return null;
	}
	public ExtendTable getExtendTable(String id){
		for(PackageNode pn : this.pacages){
			for(ExtendTable en:pn.getExtendTables()){
				if(en.getId().equals(id)) return en;
			}
		}
		return null;
	}
	
	public PersistentNode getPersistentNode(String id){
		for(PackageNode pn : this.pacages){
			PersistentNode po = pn.getPersistentNode(id);
			if(po!=null) return po;
		}
		return null;
	}
	
	
	public static void generateJavaFile(File file,RootNode rn){
		
	}
	
	

	@Override
	public List<Node> children() {
		// TODO Auto-generated method stub
		return null;
	}

	
	


}
