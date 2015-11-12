package org.jfw.orm.core.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jfw.orm.core.Node;
import org.jfw.orm.core.OrmHandler;
import org.jfw.orm.core.compiler.StringCompileException;
import org.jfw.orm.core.compiler.StringCompiler;
import org.jfw.orm.core.impl.root.DataElementNode;
import org.jfw.orm.core.impl.root.PackageNode;
import org.jfw.orm.core.impl.root.TypeHandlerNode;
import org.jfw.orm.core.impl.root.packages.Entry;
import org.jfw.orm.core.impl.root.packages.ExtendTable;
import org.jfw.orm.core.impl.root.packages.PersistentNode;
import org.jfw.orm.core.impl.root.packages.Table;
import org.jfw.orm.core.impl.root.packages.View;

public class RootNode extends AbstractNode {

	public final transient Map<String, Object> TemplateVariable = new HashMap<String, Object>();
	private List<PackageNode> pacages = new LinkedList<PackageNode>();
	private List<TypeHandlerNode> handlers = new LinkedList<TypeHandlerNode>();
	private List<DataElementNode> dataEles = new LinkedList<DataElementNode>();
	public final transient Map<String, Class<OrmHandler>> ormHandlers = new HashMap<String, Class<OrmHandler>>();
	private List<String> compileOptions = new ArrayList<String>();

	public DataElementNode getDataElement(String id) {
		for (DataElementNode de : this.dataEles) {
			if (de.getId().equals(id))
				return de;
		}
		return null;
	}

	public PackageNode getPackageNode(String id) {
		for (PackageNode pn : this.pacages) {
			if (pn.getId().equals(id))
				return pn;
		}
		return null;
	}

	public PackageNode getPackageNodeWithChildId(String childId) {
		for (PackageNode pn : this.pacages) {
			if (pn.exists(childId))
				return pn;
		}
		return null;
	}

	public TypeHandlerNode getTypeHandlerNode(String id) {
		for (TypeHandlerNode thn : this.handlers) {
			if (thn.getId().equals(id))
				return thn;
		}
		return null;
	}

	public Entry getEntry(String id) {
		for (PackageNode pn : this.pacages) {
			for (Entry en : pn.getEntrys()) {
				if (en.getId().equals(id))
					return en;
			}
			for (Entry en : pn.getTables()) {
				if (en.getId().equals(id))
					return en;
			}
		}
		return null;
	}

	public Table getTable(String id) {
		for (PackageNode pn : this.pacages) {
			for (Table en : pn.getTables()) {
				if (en.getId().equals(id))
					return en;
			}
		}
		return null;
	}
	public View getView(String id){
		for (PackageNode pn : this.pacages) {
			for (View en : pn.getViews()) {
				if (en.getId().equals(id))
					return en;
			}
		}
		return null;
	}

	public ExtendTable getExtendTable(String id) {
		for (PackageNode pn : this.pacages) {
			for (ExtendTable en : pn.getExtendTables()) {
				if (en.getId().equals(id))
					return en;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<PersistentNode> getAllPersistentNodes() {
		List<PersistentNode> list = new ArrayList<PersistentNode>();
		for (PackageNode pn : this.pacages) {
			list.addAll(pn.getEntrys() == null ? Collections.EMPTY_LIST : pn
					.getEntrys());
			list.addAll(pn.getTables() == null ? Collections.EMPTY_LIST : pn
					.getTables());
			list.addAll(pn.getExtendTables() == null ? Collections.EMPTY_LIST
					: pn.getExtendTables());
			list.addAll(pn.getViews() == null ? Collections.EMPTY_LIST : pn
					.getViews());
		}
		return list;
	}

	public PersistentNode getPersistentNode(String id) {
		for (PackageNode pn : this.pacages) {
			PersistentNode po = pn.getPersistentNode(id);
			if (po != null)
				return po;
		}
		return null;
	}

	public static void createOrmHandlers(RootNode rn, StringCompiler sc)
			throws StringCompileException {
		Map<String, String> files = new HashMap<String, String>();
		Map<String, String> idNames = new HashMap<String, String>();
		for (TypeHandlerNode thn : rn.getHandlers()) {
			String className = TypeHandlerNode.PACKAGE_NAME + "."
					+ thn.getCode().trim();
			files.put(className, thn.getJavaCode(rn));
			idNames.put(className, thn.getId());
		}
		if (files.isEmpty())
			return;
		for (Map.Entry<String, Class<OrmHandler>> en : sc.<OrmHandler> compile(
				files).entrySet()) {
			String id = idNames.get(en.getKey());
			rn.ormHandlers.put(id, en.getValue());
		}
	}

	public static void generateJavaFile(File file, RootNode rn)
			throws StringCompileException, IOException {
		StringCompiler sc = new StringCompiler(RootNode.class.getClassLoader(),
				rn.getCompileOptions());
		ClassLoader oldCl = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(sc.getClassLoader());
		rn.ormHandlers.clear();
		try {
			createOrmHandlers(rn, sc);
			for (PersistentNode pn : rn.getAllPersistentNodes()) {
				pn.generatePersistentObject(rn, file);
				pn.generatePersistentObjectHandler(rn, file);
			}

		} finally {
			rn.ormHandlers.clear();
			Thread.currentThread().setContextClassLoader(oldCl);
		}

	}

	public List<TypeHandlerNode> getHandlers() {
		return handlers;
	}

	@Override
	public List<Node> children() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getCompileOptions() {
		return compileOptions;
	}

	public void setCompileOptions(List<String> compileOptions) {
		this.compileOptions = compileOptions;
	}

}
