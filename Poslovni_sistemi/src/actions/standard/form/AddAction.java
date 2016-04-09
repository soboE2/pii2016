package actions.standard.form;

import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import model.tables.Column;



public class AddAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private StandardForm standardForm;
	
	public AddAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/add.gif")));
		putValue(SHORT_DESCRIPTION, "Dodavanje");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		standardForm.getStateManager().setCurrentState(standardForm.getStateManager().getInsertState());
		standardForm.getFocusedTable().clearSelection();
		for(Column column : standardForm.getItems().getColuumns()){
			JTextField textF =((JTextField)standardForm.form.get(column));
			textF.setText("");
			textF.setEditable(true);
		}
	}
}
