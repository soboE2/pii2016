
package actions.standard.form;

import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import model.tables.Column;

/**
 * Metoda koja uvek vraca u stanje edit
 * @author Borko Arsovic
 *
 */
public class RollbackAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;

	public RollbackAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/remove.gif")));
		putValue(SHORT_DESCRIPTION, "Poništi");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		standardForm.getStateManager().setCurrentState(standardForm.getStateManager().getEditState());
		standardForm.getFocusedTable().clearSelection();
		for(Column column : standardForm.getItems().getColuumns()){
			JTextField textF =((JTextField)standardForm.form.get(column));
			textF.setText("");
			textF.setEditable(true);
		}
	}
}
