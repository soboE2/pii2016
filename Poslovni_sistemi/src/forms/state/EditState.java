package forms.state;

import gui.main.form.MainFrame;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JComponent;

import model.tables.Column;
import model.tables.MyTableModel;
import utils.Utils;
import database.DBConnection;

/**
 * Implementacija state paterna zaduzena za modifikaciju sloga u tabelu.
 * {@link EditState} implementira {@link State}
 * 
 * @author Borko Arsovic
 * 
 */
public class EditState implements State {
	
	@Override
	public void comit(ArrayList<Column> columns, String code,
			Map<Column, JComponent> form, MyTableModel table) {
		
		String updateSql = "UPDATE " + code + " SET ";
		String primaryKey = "";
		for (int i = 0; i < columns.size(); i++) {
			Column col = columns.get(i);
			if (!col.isPk()) {
				if (i != columns.size() - 1) {
					updateSql += columns.get(i).getCode() + " = ? ,";

				} else {
					updateSql += columns.get(i).getCode() + " = ?  WHERE ";

				}
			} else {
				if (col.getType().equals("java.lang.String")) {
					primaryKey += columns.get(i).getCode() + " LIKE ? ;";
				} else {
					primaryKey += columns.get(i).getCode() + " = ? ;";
				}

			}
		}

		updateSql += primaryKey;
//		System.out.println(updateSql);

		try {
			PreparedStatement pstmt = DBConnection.getConnection()
					.prepareStatement(updateSql);

			for (int i = columns.size(); i > 0; i--) {
					JComponent comp = form.get(columns.get(i-1));
					Utils.setPrepared(pstmt, columns.get(i-1).getType(), comp,
							columns.size()-i+1);
			}
			

			pstmt.executeUpdate();
			DBConnection.getConnection().commit();
			table.reload();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			MainFrame.getInstance().showSqlExceptionError(e);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "izmena";
	}

}
