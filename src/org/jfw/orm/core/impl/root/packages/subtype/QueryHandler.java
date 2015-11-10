package org.jfw.orm.core.impl.root.packages.subtype;

import org.jfw.orm.core.impl.RootNode;
import org.jfw.orm.core.impl.root.DataElementNode;

public interface QueryHandler {
	void init(DataQueryMethod dqm,RootNode rn);
	boolean isMutliRows();
	boolean isDynamicFilter();
	String getPersistentNodeId();
	DataElementNode getDataElementNodeWithSingleValue();
    void writeQueryFields(StringBuilder sb);
	void writeFromSentence(StringBuilder sb);
	void writeWhereSentence(StringBuilder sb);
	void writeOtherSentence(StringBuilder sb);
	
	
	
	//boolean is
}
