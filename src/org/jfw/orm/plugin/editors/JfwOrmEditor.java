package org.jfw.orm.plugin.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class JfwOrmEditor extends TextEditor {

	private ColorManager colorManager;

	public JfwOrmEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
