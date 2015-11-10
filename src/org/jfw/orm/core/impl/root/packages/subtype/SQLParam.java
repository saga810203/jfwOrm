package org.jfw.orm.core.impl.root.packages.subtype;

import org.jfw.orm.core.OrmHandler;
import org.jfw.orm.core.Utils;
import org.jfw.orm.core.impl.RootNode;
import org.jfw.orm.core.impl.root.DataElementNode;

public abstract class SQLParam {	
	
	public abstract String getValueExpression(RootNode rn);
	public abstract DataElementNode getDataElement(RootNode rn);
	public abstract boolean useTempalteVariable(RootNode rn);
	public abstract boolean isDbNullable(RootNode rn);

	public abstract OrmHandler getOrmHandler(RootNode rn);
	
	
	public boolean isPrimitive(RootNode rn){
		return Utils.isPrimitive(this.getDataElement(rn).getTypeHandlerNode(rn).getSupportClassName());
	}
}
