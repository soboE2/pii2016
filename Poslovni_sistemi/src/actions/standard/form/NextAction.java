package actions.standard.form;

import gui.standard.form.MainTable;
import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * Metoda koja omogucava selektovanje sledeceg sloga u tabeli.Pod uslovom da je neki slog vec selektovan.
 * @author Borko Arsovic
 *
 */
public class NextAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;


	public NextAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/next.gif")));
		putValue(SHORT_DESCRIPTION, "Sledeci");
		this.standardForm=standardForm;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainTable table = standardForm.getFocusedTable();
		int tableCount = table.getRowCount();
		if(tableCount>0){
			if(table.getSelectedRow()>=0 && table.getSelectedRow()< tableCount-1){
				table.setRowSelectionInterval(table.getSelectedRow()+1, table.getSelectedRow()+1);
				standardForm.fillForm();
			}
		}
	}
}
