
package actions.standard.form;

import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import model.tables.MyTableModel;

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
		
		if (standardForm.getStateManager().getCurrentState()
				.equals(standardForm.getStateManager().getSearchState())) {
			try {
				((MyTableModel) standardForm.getTblGrid().getModel()).reload();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		standardForm.getStateManager().changeToEditState();
		
	}
}
