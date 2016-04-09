package actions.standard.form;

import gui.standard.form.MainTable;
import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * Metoda koja omogucava selektovanje provlog sloga u tabeli.Pod uslovom da je neki slog vec selektovan.
 * @author Borko Arsovic
 *
 */
public class FirstAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;

	public FirstAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/first.gif")));
		putValue(SHORT_DESCRIPTION, "Pocetak");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainTable table = standardForm.getFocusedTable();
		int tableCount = table.getRowCount();
		if(table.getSelectedRow()>=0  && tableCount > 0){
			table.setRowSelectionInterval(0, 0);
			standardForm.fillForm();
		}
	}
}
