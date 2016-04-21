package actions.standard.form;

import gui.main.form.MainFrame;
import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import model.tables.Column;
import model.tables.MyTableModel;
import utils.Utils;
import database.DBConnection;

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

		for(Column col : columns){
			String type = col.getType();
			if(col.isPk()){
				if (type.equals("java.lang.String")) {
					deleteSQL += col.getCode() + " LIKE ?;";
				}else{
					deleteSQL += col.getCode() + " = ?";
				}
			}
		}

		try {
			PreparedStatement pstmt = DBConnection.getConnection()
					.prepareStatement(deleteSQL);
			for(Column col : columns){
				if(col.isPk()){
					JComponent comp = standardForm.form.get(col);
					Utils.setPrepared(pstmt, col.getType(), comp, 1);
				}
			}
			pstmt.execute();
			DBConnection.getConnection().commit();
			table.reload();
			standardForm.refreshButton();
			standardForm.restartField();
		} catch (SQLException e1) {
			MainFrame.getInstance().showSqlExceptionError(e1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
