package org.jfw.orm.core.impl.root.packages;

import java.util.List;

import org.jfw.orm.core.Node;
import org.jfw.orm.core.impl.AbstractNode;
import org.jfw.orm.core.impl.RootNode;

public class PackageNode extends AbstractNode{
	public String getJavaName(RootNode rn)
	{
		return dbNameToJavaName(this.code);
	}
	public String getPackageName(RootNode rn){
		return rn.getPackageNodeWithChildId(this.id).getCode();
	}
	@Override
	public List<Node> children() {
		return null;
	}
}
