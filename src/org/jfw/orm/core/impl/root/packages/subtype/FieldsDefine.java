package org.jfw.orm.core.impl.root.packages.subtype;

import java.util.List;

import org.jfw.orm.core.OrmHandler;
import org.jfw.orm.core.impl.RootNode;
import org.jfw.orm.core.impl.root.packages.Column;

public interface FieldsDefine {
	void init(DataQueryMethod dqm,RootNode rn);
	String getResultSetType();
	List<Column> getColumns();
	String getFromSentence();
}
