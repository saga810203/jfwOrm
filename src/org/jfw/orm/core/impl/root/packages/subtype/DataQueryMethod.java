package org.jfw.orm.core.impl.root.packages.subtype;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jfw.orm.core.OrmHandler;
import org.jfw.orm.core.Utils;
import org.jfw.orm.core.impl.RootNode;
import org.jfw.orm.core.impl.root.DataElementNode;
import org.jfw.orm.core.impl.root.TypeHandlerNode;
import org.jfw.orm.core.impl.root.packages.CalcColumn;
import org.jfw.orm.core.impl.root.packages.Column;

public class DataQueryMethod extends DataOperateMethod {

	private boolean multiRows = false;
	private String fieldsDefineClass = null;
	private transient FieldsDefine fd = null;
	private String directive = null;
	private String filterParamDefineClass = null;
	private transient FilterParamDefine fpd = null;
	private String otherSentence;

	private Map<String, String> attributes = new HashMap<String, String>();

	@Override
	public boolean isDynamicSql(RootNode rn) {
		return this.fpd.isDynamic();
	}

	@Override
	public String getReturnType() {
		StringBuilder sb = new StringBuilder();
		if (this.multiRows)
			sb.append("java.util.List<");
		String rt = this.fd.getResultSetType();
		if (this.multiRows && Utils.isPrimitive(rt)) {
			rt = Utils.getWrapClass(rt);
		}
		sb.append(rt);
		if (this.multiRows)
			sb.append(">");
		return sb.toString();
	}

	@Override
	public void prepare(StringBuilder sb, RootNode rn) {
		try {
			this.fd = (FieldsDefine) Class.forName(this.fieldsDefineClass)
					.newInstance();
			this.fd.init(this, rn);
		} catch (Exception e) {
			throw new RuntimeException("caeate fieldsDefine instance error:"
					+ this.fieldsDefineClass, e);
		}
		try {
			if (this.filterParamDefineClass == null
					|| this.filterParamDefineClass.trim().length() == 0) {
				this.fpd = EMPTY_FILTER_PARAM_DEFINE;
				return;
			}
			this.fpd = (FilterParamDefine) Class.forName(
					this.filterParamDefineClass).newInstance();
			this.fpd.init(this, rn);
		} catch (Exception e) {
			throw new RuntimeException(
					"caeate FilterParamDefine instance error:"
							+ this.filterParamDefineClass, e);
		}
	}

	@Override
	public void cleanAfterGenerated() {
		this.fd = null;
		this.fpd = null;

	}

	protected void buildResultSet(StringBuilder sb, RootNode rn) {
		List<Column> list = this.fd.getColumns();
		OrmHandler ohd;
		if (this.multiRows) {
			if (list.size() > 1) {
				sb.append(this.fd.getResultSetType()).append(" obj = new ")
						.append(this.fd.getResultSetType()).append("();");
				for(int i = 0 ; i < list.size(); ++i){
					Column col = list.get(i);
					DataElementNode den = rn.getDataElement(col.getDataEleId());
					TypeHandlerNode thn = den.getTypeHandlerNode(rn);					
					ohd = thn.getOrmHandler(rn);
					String fieldName =dbNameToJavaName(list.get(i).getCode());
					String methodName = fieldName.substring(0,1).toUpperCase(Locale.ENGLISH);
					if(fieldName.length()>1) methodName = methodName+fieldName.substring(1);
					ohd.readValue(sb, "obj.set"+methodName+"(", ");\r\n", i, col.isNullable()
							&& den.isNullable(), rn.TemplateVariable);
				}
				
			} else {
				sb.append(this.fd.getResultSetType()).append(" obj;\r\n");
				Column col = list.get(0);
				DataElementNode den = rn.getDataElement(col.getDataEleId());
				TypeHandlerNode thn = den.getTypeHandlerNode(rn);					
				ohd = thn.getOrmHandler(rn);
				ohd.readValue(sb, "obj=", ";\r\n", 0, list.get(0).isNullable()
						&& rn.getDataElement(list.get(0).getDataEleId())
								.isNullable(), rn.TemplateVariable);
				
			}
			sb.append("objs.add(obj);\r\n");
		} else {
			if (list.size() > 1) {
				sb.append(" obj = new ").append(this.fd.getResultSetType()).append("();");
				for(int i = 0 ; i < list.size(); ++i){
					Column col = list.get(i);
					DataElementNode den = rn.getDataElement(col.getDataEleId());
					TypeHandlerNode thn = den.getTypeHandlerNode(rn);					
					ohd = thn.getOrmHandler(rn);
					String fieldName =dbNameToJavaName(list.get(i).getCode());
					String methodName = fieldName.substring(0,1).toUpperCase(Locale.ENGLISH);
					if(fieldName.length()>1) methodName = methodName+fieldName.substring(1);
					ohd.readValue(sb, "obj.set"+methodName+"(", ");\r\n", i, col.isNullable()
							&& den.isNullable(), rn.TemplateVariable);
				}
				
			} else {
				Column col = list.get(0);
				DataElementNode den = rn.getDataElement(col.getDataEleId());
				TypeHandlerNode thn = den.getTypeHandlerNode(rn);					
				ohd = thn.getOrmHandler(rn);
				ohd.readValue(sb, "obj=", ";\r\n", 0, list.get(0).isNullable()
						&& rn.getDataElement(list.get(0).getDataEleId())
								.isNullable(), rn.TemplateVariable);
			}
		}

		if (this.multiRows)
			sb.append("objs.add(obj);\r\n");
	}

	@Override
	public void buildHandleResult(StringBuilder sb, RootNode rn) {
		sb.append(this.getReturnType()).append(" ");
		if (this.multiRows) {
			sb.append("objs = new ").append(this.getReturnType())
					.append("();\r\n");
		} else {
			if (Utils.isPrimitive(this.fd.getResultSetType())) {
				sb.append("obj;\r\n");
			} else {
				sb.append("obj = null;\r\n");
			}
		}
		sb.append("java.sql.ResultSet rs = ps.executeQuery();\r\n");
		sb.append("try{\r\n");
		if (this.multiRows) {
			sb.append("while(rs.next()){");
		} else {
			sb.append("if(rs.next(){");
		}
		this.buildResultSet(sb, rn);
		sb.append("}");
		if (this.multiRows) {
			sb.append("return objs;\r\n");
		} else {
			sb.append("return obj;\r\n");
		}
		sb.append("}finally{try{rs.close();}catch(Exception e){}}");
		if (this.multiRows) {
			sb.append("return objs;");
		} else {
			sb.append("return obj;");
		}
	}

	@Override
	public void buildParams(StringBuilder sb, RootNode rn) {
		this.fpd.writeParam(sb);
	}

	private void addQueryField(StringBuilder sb, RootNode rn) {
		List<Column> list = this.fd.getColumns();
		for (int i = 0; i < list.size(); ++i) {
			if (i > 0)
				sb.append(",");
			Column col = list.get(i);
			if (col instanceof CalcColumn) {
				sb.append(((CalcColumn) col).getExpression());
				if (col.getCode() != null && col.getCode().trim().length() > 0) {
					sb.append(" ").append(col.getCode().trim());
				}
			}
		}
	}

	@Override
	public void buildSql(StringBuilder sb, RootNode rn) {
		if (this.fpd.isDynamic()) {
			sb.append("StringBuilder sql = new StringBuilder();\r\nsql.append(");
		} else {
			sb.append("String sql =");
		}
		sb.append("\"SELECT ");
		if (this.directive != null && this.directive.trim().length() > 0)
			sb.append(this.directive.trim()).append(" ");
		this.addQueryField(sb, rn);
		sb.append(" FROM ").append(this.fd.getFromSentence());
		this.fpd.writeWhereSentence(sb);
		if (this.fpd.isDynamic()) {
			if (this.otherSentence != null
					&& this.otherSentence.trim().length() > 0) {
				sb.append("sql.append(\"").append(this.otherSentence.trim())
						.append("\");\r\n");
			}
		} else {
			if (this.otherSentence != null
					&& this.otherSentence.trim().length() > 0) {
				sb.append(" ").append(this.otherSentence.trim());
			}
			sb.append("\";");
		}

	}

	public static final FilterParamDefine EMPTY_FILTER_PARAM_DEFINE = new FilterParamDefine() {

		@Override
		public boolean isDynamic() {
			return false;
		}

		@Override
		public void init(DataQueryMethod dqm, RootNode rn) {

		}

		@Override
		public void writeWhereSentence(StringBuilder sb) {
		}

		@Override
		public void writeParam(StringBuilder sb) {
		}
	};
}
