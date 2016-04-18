package actions.standard.form;

import gui.main.form.ErrorDialog;
import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import utils.Utils;
import database.DBConnection;
import model.tables.Column;
import model.tables.MyTableModel;

public class DeleteAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;

	public DeleteAction(StandardForm standardForm) {
		putValue(SMALL_ICON,
				new ImageIcon(getClass().getResource("/img/remove.gif")));
		putValue(SHORT_DESCRIPTION, "Brisanje");
		this.standardForm = standardForm;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String code = standardForm.getItems().getCode();
		ArrayList<Column> columns = standardForm.getItems().getColuumns();
		MyTableModel table = (MyTableModel) standardForm.getTblGrid().getModel();

		String deleteSQL = "DELETE FROM " + code + " WHERE ";

		for (int i = 0; i < columns.size(); i++) {
			String type = columns.get(i).getType();
			if (type.equals("java.lang.String")) {
				if (i != columns.size() - 1) {
					deleteSQL += columns.get(i).getCode() + " LIKE ";
					deleteSQL += "? AND ";
				} else {
					deleteSQL += columns.get(i).getCode() + " LIKE ";
					deleteSQL += "?;";
				}
			} else {
				if (i != columns.size() - 1) {
					deleteSQL += columns.get(i).getCode() + " = ";
					deleteSQL += "? AND ";
				} else {
					deleteSQL += columns.get(i).getCode() + " = ";
					deleteSQL += "?;";
				}
			}

		}

		try {
			PreparedStatement pstmt = DBConnection.getConnection()
					.prepareStatement(deleteSQL);
			for (int i = 0; i < columns.size(); i++) {
				JComponent comp = standardForm.form.get(columns.get(i));
				Utils.setPrepared(pstmt, columns.get(i).getType(), comp, i + 1);
			}
			pstmt.execute();
			DBConnection.getConnection().commit();
			table.reload();
			standardForm.refreshButton();
			standardForm.restartField();
		} catch (Exception e1) {
			e1.printStackTrace();
			ErrorDialog error = new ErrorDialog("Nemoguce izbrisati podatke "
					+ e1.getMessage());
			error.setVisible(true);
		}

		// System.out.println(deleteSQL);
	}
}
