package org.jfw.orm.core.impl.root.packages.subtype;

import org.jfw.orm.core.impl.RootNode;

public interface FilterParamDefine {
	boolean isDynamic();
	void init(DataQueryMethod dqm,RootNode rn);
	void writeWhereSentence(StringBuilder sb);
	void writeParam(StringBuilder sb);
}
