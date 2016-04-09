package actions.standard.form;

import gui.standard.form.MainTable;
import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * Metoda namenjena za selektovanje prethodnig sloga. Pod uslovom da je neki slog u tabeli vec selektovan.
 * @author Borko Arsovic
 *
 */
public class PreviousAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;

	public PreviousAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/prev.gif")));
		putValue(SHORT_DESCRIPTION, "Prethodni");
		this.standardForm=standardForm;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainTable table = standardForm.getFocusedTable();
		int tableCount = table.getRowCount();
		if(tableCount>0){
			if(table.getSelectedRow()>=0 && table.getSelectedRow()-1>=0){
				table.setRowSelectionInterval(table.getSelectedRow()-1, table.getSelectedRow()-1);
			}
		}
	}
}
