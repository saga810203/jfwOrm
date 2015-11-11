package org.jfw.orm.core.impl.root.packages.subtype;

import java.util.Map;

import org.jfw.orm.core.impl.RootNode;

public interface DataModifyDefine {
	void init(DataModifyMehtod dmm,RootNode rn);
	void prepare(StringBuilder sb);
	Map<String,String> getAttributes();
	boolean isDynamicSql();
	void buildParams(StringBuilder sb);
	void buildSql(StringBuilder sb);
}
