package actions.standard.form;

import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import model.tables.Column;

public class DeleteAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;
	
	public DeleteAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/remove.gif")));
		putValue(SHORT_DESCRIPTION, "Brisanje");
		this.standardForm=standardForm;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		standardForm.getStateManager().setCurrentState(standardForm.getStateManager().getRemoveState());
		standardForm.getFocusedTable().clearSelection();
		for(Column column : standardForm.getItems().getColuumns()){
			JTextField textF =((JTextField)standardForm.form.get(column));
			textF.setText("");
			textF.setEditable(false);
		}
	}
}
