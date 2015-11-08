package org.jfw.orm.core.impl.root.packages;

public class CalcColumn extends Column {
	private String expression;

	@Override
	public boolean isDefaultQuery()
	{
		return null!=this.expression&& expression.trim().length()>0;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}

}
