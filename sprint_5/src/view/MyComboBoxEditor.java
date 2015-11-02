package view;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

class MyComboBoxEditor extends DefaultCellEditor {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyComboBoxEditor(String[] items) {
	    super(extracted(items));
	  }

	private static JComboBox<String> extracted(String[] items) {
		return new JComboBox<String>(items);
	}
	}