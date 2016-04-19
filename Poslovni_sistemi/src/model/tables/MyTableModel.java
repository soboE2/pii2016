package model.tables;

import gui.standard.menuItem.MyMenuItems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import utils.SortUtils;
import database.DBConnection;

public class MyTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 4992228553348489370L;

	private String basicQuery = "SELECT * FROM ";
//	private String orderBy = " ORDER BY dr_sifra";
//	private String whereStmt = "";
	MyMenuItems item;
	
	public MyTableModel(MyMenuItems item){
		
		this.item = item;
		ArrayList<Column> columns = item.getColuumns();	
		
		for(Column col : columns)
			this.addColumn(col.getName());
		basicQuery += item.getCode();
		try {
			fillData(basicQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void reload() throws Exception{
		try {
			fillData(basicQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void fillData(String sql) throws Exception {

		Statement stmt = DBConnection.getConnection().createStatement();
		ResultSet rset = stmt.executeQuery(sql);
		this.setRowCount(0);
		
		while (rset.next()) {
			addRow(rset);
		}
		
		rset.close();
		stmt.close();
//		fireTableDataChanged();
	}

	public void addRow(ResultSet rset) throws SQLException{
		ArrayList<Column> columns =item.getColuumns();
		Object[] row = new Object[columns.size()];
		for(int i=0; i<columns.size(); i++){
			row[i]= rset.getString(i+1);
		}
		this.addRow(row);
	}
	
	/**
	 * Metoda koja onemogucuje direktno menjanje sadrzaja celijama.
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@SuppressWarnings("unused")
	private int sortedInsert(String sifra, String naziv) {

		int left = 0;
		int right = getRowCount() - 1;
		int mid = (left + right) / 2;

		while (left <= right ) {

			mid = (left + right) / 2;
			String aSifra = (String)getValueAt(mid, 0);
			if (SortUtils.getLatCyrCollator().compare(sifra, aSifra) > 0)
				left = mid + 1;
			else if (SortUtils.getLatCyrCollator().compare(sifra, aSifra) < 0)
				right = mid - 1;
			else
				// ako su jednaki: to ne moze da se desi ako tabela ima primarni
			break;

		}

		insertRow(left, new String[] {sifra, naziv});
		return left;

	}
	
	public void loadSearchResults(ResultSet rs) {
		this.setRowCount(0);
		try {
			while(rs.next()) {
				addRow(rs);
			}
			//rs.close();
			fireTableDataChanged();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

		
	
}
