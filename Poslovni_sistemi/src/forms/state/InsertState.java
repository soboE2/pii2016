package forms.state;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JComponent;

import model.tables.Column;
import model.tables.MyTableModel;
import utils.Utils;
import database.DBConnection;

/**Implementacija state paterna zaduzena za dodavanje sloga u tabelu.
 * {@link InsertState} implementira {@link State}
 * @author Borko Arsovic
 */

public class InsertState implements State {


	@Override
	public void comit(ArrayList<Column> columns, String code,
		 Map<Column, JComponent> form,MyTableModel table) throws Exception {

		 String insertSQL = "INSERT INTO " + code + " (";
		 String values = "VALUES (";
		 for(int i = 0; i < columns.size(); i++){ 
			 if(i!= columns.size() -1){
				 insertSQL += columns.get(i).getCode()+ ",";
				 values +="?,";
			 }else{
				 insertSQL += columns.get(i).getCode()+ ")";
				 values +="?)";
			 }
	
		 }
		 insertSQL+=values;
		 
		 	try{
				PreparedStatement pstmt=  DBConnection.getConnection().prepareStatement(insertSQL);
				 for(int i = 0; i < columns.size(); i++){ 
					 JComponent comp = form.get(columns.get(i));
					 Utils.setPrepared(pstmt, columns.get(i).getType(), comp, i+1);
				 }
				pstmt.execute();
				DBConnection.getConnection().commit();
				table.reload();
			} catch (Exception e) {
				DBConnection.getConnection().rollback();
				throw e;
			}

		 	
	}
	
	@Override
	public String toString() {
		return "dodavanje";
	}
	
	

}
