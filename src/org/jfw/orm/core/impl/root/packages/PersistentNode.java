package org.jfw.orm.core.impl.root.packages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.jfw.orm.core.Contants;
import org.jfw.orm.core.Node;
import org.jfw.orm.core.Utils;
import org.jfw.orm.core.impl.AbstractNode;
import org.jfw.orm.core.impl.RootNode;
import org.jfw.orm.core.impl.root.DataElementNode;
import org.jfw.orm.core.impl.root.TypeHandlerNode;
import org.jfw.orm.core.impl.root.packages.subtype.DataOperateMethod;
import org.jfw.orm.core.impl.root.packages.subtype.MethodParam;

public abstract class PersistentNode extends AbstractNode {

	public String getJavaName(RootNode rn) {
		return dbNameToJavaName(this.code);
	}

	public String getPackageName(RootNode rn) {
		return rn.getPackageNodeWithChildId(this.id).getCode();
	}

	public abstract String getParentPersistentNodeId(RootNode rn);

	public abstract List<Column> getAllDefineColumn(RootNode rn);

	public abstract String getFromSentence(RootNode rn);

	public List<DataOperateMethod> getHandlers()
	{
		return null;
	}

	@Override
	public List<Node> children() {
		return null;
	}

	public static void writeComment(StringBuilder sb, String co) {
		if (co != null && co.length() > 0) {
			String coo = co.replace("*/", "* /");
			boolean newLine = true;
			sb.append(Contants.SP_4).append("/*");
			for (int i = 0; i < coo.length(); ++i) {
				char c = coo.charAt(i);
				if (c == '\r')
					continue;
				if (newLine) {
					sb.append("\r\n").append(Contants.SP_4).append(" ").append("*");
					newLine = false;
				}
				if (c == '\n') {
					newLine = true;
				} else {
					sb.append(c);
				}
			}
			sb.append(Contants.SP_4).append("*/\r\n");
		}
	}

	public void generatePersistentObjectField(StringBuilder sb, RootNode rn, Column col) {
		writeComment(sb, col.getDisplay());
		writeComment(sb, col.getComment());
		sb.append(Contants.SP_4).append("private ");
		DataElementNode den = rn.getDataElement(col.getDataEleId());
		TypeHandlerNode thn = rn.getTypeHandlerNode(den.getTypeHandlerId());
		sb.append(thn.getSupportClassName(rn)).append(" ").append(dbNameToJavaName(col.getCode().trim()))
				.append(";\r\n\r\n");

	}

	public void generatePersistentObjectFields(StringBuilder sb, RootNode rn) {
		List<Column> parentList = null;
		String pid = this.getParentPersistentNodeId(rn);
		if (pid != null) {
			PersistentNode pn = rn.getPersistentNode(pid);
			parentList = pn.getAllDefineColumn(rn);
		}
		List<Column> cList = this.getAllDefineColumn(rn);
		for (Column col : cList) {
			if (parentList != null && !parentList.contains(col)) {
				generatePersistentObjectField(sb, rn, col);
			}
		}
	}

	public void generatePersistentObjectMethod(StringBuilder sb, RootNode rn, Column col) {
		String name = dbNameToJavaName(col.getCode());
		DataElementNode den = rn.getDataElement(col.getDataEleId());
		TypeHandlerNode thn = rn.getTypeHandlerNode(den.getTypeHandlerId());
		String className = thn.getSupportClassName(rn).trim();

		sb.append(Contants.SP_4).append("public ").append(className);
		if (className.equals("boolean"))
			sb.append(" is");
		else
			sb.append(" get");		
		Utils.appendFieldNameToMethodName(name, sb);
		sb.append("(){\r\n").append(Contants.SP_8).append("return this.")
				.append(name).append(";\r\n").append(Contants.SP_4).append("}\r\n");

		sb.append(Contants.SP_4).append("public void set");
		Utils.appendFieldNameToMethodName(name, sb);
		sb.append("(").append(className).append(" ").append(name).append("){\r\n").append(Contants.SP_8).append("this.")
				.append(name).append(" = ").append(name).append(";\r\n").append(Contants.SP_4).append("}\r\n");

	}

	public void generatePersistentObjectMethods(StringBuilder sb, RootNode rn) {
		List<Column> parentList = null;
		String pid = this.getParentPersistentNodeId(rn);
		if (pid != null) {
			PersistentNode pn = rn.getPersistentNode(pid);
			parentList = pn.getAllDefineColumn(rn);
		}
		List<Column> cList = this.getAllDefineColumn(rn);
		for (Column col : cList) {
			if (parentList != null && !parentList.contains(col)) {
				generatePersistentObjectMethod(sb, rn, col);
			}
		}
	}

	public String getPersistentNodeObjectCode(RootNode rn) {
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(this.getPackageName(rn)).append(";\r\n");
		sb.append("public class ").append(this.getJavaName(rn));
		String ppn = this.getParentPersistentNodeId(rn);
		if (ppn != null) {
			PersistentNode ppo = rn.getPersistentNode(ppn);
			sb.append(" extends ");
			if (!this.getPackageName(rn).equals(ppo.getPackageName(rn))) {
				sb.append(ppo.getPackageName(rn).trim()).append(".");
			}
			sb.append(ppo.getJavaName(rn));
		}
		sb.append("{\r\n");
		this.generatePersistentObjectFields(sb, rn);
		this.generatePersistentObjectMethods(sb, rn);
		sb.append("}");
		return sb.toString();
	}

	public void generatePersistentObject(RootNode rn, File path) throws IOException {
		String[] dirName = this.getPackageName(rn).split("\\.");
		File cf = path;
		for (int i = 0; i < dirName.length; ++i) {
			cf = new File(cf, dirName[i]);
			if (cf.exists()) {
				if (cf.isFile())
					throw new IOException("Unable to create directory:" + cf.getAbsolutePath());
			} else {
				if (!cf.mkdir()) {
					throw new IOException("Unable to create directory:" + cf.getAbsolutePath());
				}
			}

		}
		File file = new File(cf, this.getJavaName(rn).trim() + ".java");
		if (file.exists()) {
			if (file.isDirectory())
				throw new IOException("Unable to create file:" + file.getAbsolutePath());
			if (!file.delete())
				throw new IOException("Unable to delete old file:" + file.getAbsolutePath());
		}
		OutputStream os = new FileOutputStream(file);
		try {
			os.write(this.getPersistentNodeObjectCode(rn).getBytes("UTF-8"));
			os.flush();
		} finally {
			os.close();
		}
	}

	
	public void generatePersistentObjectHandler(RootNode rn, File path) throws IOException {
		if(null == this.getHandlers() || this.getHandlers().isEmpty())return;
		String[] dirName1 = this.getPackageName(rn).split("\\.");
		String[] dirName = new String[dirName1.length+1];
		System.arraycopy(dirName1, 0,dirName, 0,dirName.length);
		dirName[dirName.length]="handler";
		
		File cf = path;
		for (int i = 0; i < dirName.length; ++i) {
			cf = new File(cf, dirName[i]);
			if (cf.exists()) {
				if (cf.isFile())
					throw new IOException("Unable to create directory:" + cf.getAbsolutePath());
			} else {
				if (!cf.mkdir()) {
					throw new IOException("Unable to create directory:" + cf.getAbsolutePath());
				}
			}

		}
		File file = new File(cf, this.getJavaName(rn).trim()+"Handler" + ".java");
		if (file.exists()) {
			if (file.isDirectory())
				throw new IOException("Unable to create file:" + file.getAbsolutePath());
			if (!file.delete())
				throw new IOException("Unable to delete old file:" + file.getAbsolutePath());
		}
		OutputStream os = new FileOutputStream(file);
		try {
			os.write(this.getPersistentObjectHandlerCode(rn).getBytes("UTF-8"));
			os.flush();
		} finally {
			os.close();
		}
	}
	public String getPersistentObjectHandlerCode(RootNode rn){
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(this.getPackageName(rn)).append(".handler;\r\n");
		sb.append("public class ").append(this.getJavaName(rn)+"Handler");
		sb.append("{\r\n");
		sb.append(Contants.SP_4).append("public static ").append(this.getJavaName(rn)+"Handler ").append("HANDLER = new ")
		  .append(this.getJavaName(rn)+"Handler();\r\n");
		this.generatePersistentObjectHandlerMethods(sb, rn);
		sb.append("}");
		return sb.toString();
	}
	public void generatePersistentObjectHandlerMethods(StringBuilder sb,RootNode rn){
		for(DataOperateMethod dom : this.getHandlers()){
			writeComment(sb,dom.getComment());
			sb.append(Contants.SP_4).append("public ").append(dom.getReturnType()).append(" ")
			  .append(dom.getJavaMethodName()).append("(java.sql.Connection con");
			for(MethodParam mp:dom.getParams()){
				sb.append(",").append(mp.getJavaTypeName()).append(" ").append(mp.getName());
			}
			sb.append(") throws java.sql.SQLException{\r\n");
			sb.append(Contants.SP_8);
			rn.TemplateVariable.clear();
			dom.generateMethodCode(sb,rn);				
			sb.append(Contants.SP_4).append("}\r\n");
		}
		
	}
	
	public static void main(String[] args) {
		String pn = PersistentNode.class.getPackage().getName();
		String[] dirName = pn.split("\\.");
		for (String s : dirName) {
			System.out.println(s);
		}
	}
}
