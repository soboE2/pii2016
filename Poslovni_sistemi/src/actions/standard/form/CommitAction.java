package actions.standard.form;

import forms.state.State;
import gui.main.form.ErrorDialog;
import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import model.tables.Column;
import model.tables.MyTableModel;

/**
 * Metoda koja ce u zavisnosti od stanja raditi drugaciji komit.
 * 
 * @author Borko Arsovic,Nemanja Sobo
 * 
 */
public class CommitAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;

	public CommitAction(StandardForm standardForm) {
		putValue(SMALL_ICON,
				new ImageIcon(getClass().getResource("/img/commit.gif")));
		putValue(SHORT_DESCRIPTION, "Commit");
		this.standardForm = standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		State state = standardForm.getStateManager().getCurrentState();
		ArrayList<Column> columns = standardForm.getItems().getColuumns();
		String code = standardForm.getItems().getCode();
		MyTableModel table = (MyTableModel) standardForm.getTblGrid()
				.getModel();
		Map<Column, JComponent> form = standardForm.getForm();
			try {
				state.comit(columns, code, form, table);
				standardForm.restartField();
				standardForm.refreshButton();

			} catch (Exception e1) {
				e1.printStackTrace();
				ErrorDialog error = new ErrorDialog("Nemoguce upisati podatke "
						+ e1.getMessage());
				error.setVisible(true);
			}
	}
}
