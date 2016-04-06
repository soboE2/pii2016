package model.tables;

import gui.standard.menuItem.MyMenuItems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

import database.DBConnection;

public class MyTableModel extends DefaultTableModel{
	
	private String basicQuery = "SELECT * FROM ";
	private String orderBy = " ORDER BY dr_sifra";
	private String whereStmt = "";
	
	MyMenuItems item;
	public MyTableModel(MyMenuItems item){
		
		this.item = item;
		ArrayList<Column> columns = item.getColuumns();	
		
		for(Column col : columns)
			this.addColumn(col.getName());
		basicQuery += item.getCode();
		this.setRowCount(0);
		
		try {
			fillData(basicQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void fillData(String sql) throws SQLException {

		Statement stmt = DBConnection.getConnection().createStatement();
		ResultSet rset = stmt.executeQuery(sql);

		while (rset.next()) {
			addRow(rset);
		}
		
		rset.close();
		stmt.close();
		fireTableDataChanged();

	}

	public void addRow(ResultSet rset) throws SQLException{
		
		ArrayList<Column> columns =item.getColuumns();
		Object[] row = new Object[columns.size()];
		for(int i=0; i<columns.size(); i++){
			row[i]= rset.getString(i+1);
		}
		this.addRow(row);
		
	}
	
	
}
