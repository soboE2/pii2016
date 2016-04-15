package forms.state;

import gui.standard.form.StandardForm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JComponent;

import model.tables.Column;
import model.tables.MyTableModel;
import utils.Utils;
import database.DBConnection;

/**Implementacija state paterna zaduzena za pretragu slogova u tabeli.
 * {@link SearchState} implementira {@link State}
 * @author Borko Arsovic
 *
 */
public class SearchState implements State {

	private StandardForm standardForm;

	@Override
	public void comit(ArrayList<Column> columns, String code,
			Map<Column, JComponent> form,MyTableModel table) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
		String searchSQL = "SELECT * FROM " + code + " WHERE ";
		String conditions = "";
		for(int i=0; i<columns.size(); i++) {
			String type = columns.get(i).getType();
			if (type.equals("varchar") || type.equals("char")) {
				if (i == columns.size() - 1)
					conditions += columns.get(i).getCode() + " LIKE ?";
				else
					conditions += columns.get(i).getCode() + " LIKE ? AND ";
			}
			else if(type.equals("tinyint") || type.equals("smallint") || type.equals("int") ||
					type.equals("numeric") || type.equals("float") || type.equals("decimal")) {
				if (i == columns.size() - 1)
					conditions += columns.get(i).getCode() + " = ?";
				else
					conditions += columns.get(i).getCode() + " = ? AND ";
			}
		}
		
		searchSQL += conditions;
		
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(searchSQL);
			for(int i=0; i<columns.size(); i++) {
				JComponent comp = form.get(columns.get(i));
				Utils.setPreparedForSearch(pstmt, columns.get(i).getType(), comp, i+1);
			}
			rs = pstmt.executeQuery();
			DBConnection.getConnection().commit();
			table.reload();
		} catch (Exception e) {
			// TODO Auto-generated catch bloc
			DBConnection.getConnection().rollback();
			throw e;
		}
		
		if(rs != null)
			table.loadSearchResults(rs);
		
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "pretraga";
	}
	
}
