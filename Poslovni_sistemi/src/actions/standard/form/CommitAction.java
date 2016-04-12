package actions.standard.form;


import gui.standard.form.MainTable;
import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.tables.Column;
import database.DBConnection;


/**
 * Metoda koja ce u zavisnosti od stanja raditi drugaciji komit.
 * @author Borko Arsovic,Nemanja Sobo
 *
 */
public class CommitAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;
	
	public CommitAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/commit.gif")));
		putValue(SHORT_DESCRIPTION, "Commit");
		this.standardForm = standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		MainTable table = standardForm.getFocusedTable();		
		if(standardForm.getStateManager().getCurrentState()== standardForm.getStateManager().getRemoveState()){
			if(table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(standardForm, "Brisanje nije omoguceno!!!"+"\n"+"Potrebno je selektovati zeljeni red za brisanje");
			}else{
				String basicQuery = "DELETE FROM ";
				basicQuery += standardForm.getItems().getCode() + " WHERE ";

				
				for(int i=0; i<table.getColumnCount(); i++){
					Column col = standardForm.getItems().getColuumns().get(i);
					if(i+1 == table.getColumnCount()){
						basicQuery += col.getCode() +"= ?";
					}else {
						basicQuery += col.getCode() + "= ? AND ";
					}
				}
				
				basicQuery+=";";
				//System.out.println(basicQuery);
				
				try {
					PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(basicQuery);
					int tableRow = table.getSelectedRow();
					
					for(int i=0; i<table.getColumnCount(); i++){
						pstmt.setString(i+1,(String)table.getValueAt(tableRow, i));
					}
					
				
				pstmt.execute();
				pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}else if(standardForm.getStateManager().getCurrentState()== standardForm.getStateManager().getInsertState()){
			
			String insertQuery = "INSERT INTO ";
			insertQuery += standardForm.getItems().getCode()+" (";
			for(int i=0; i<table.getColumnCount(); i++){
				Column col = standardForm.getItems().getColuumns().get(i);
				if(i+1 == table.getColumnCount()){
					insertQuery += col.getCode() +" )";
				}else {
					insertQuery += col.getCode() + " , ";
				}
			}
			insertQuery += " VALUES (";
			for(int i=0; i<table.getColumnCount(); i++){
				Column col = standardForm.getItems().getColuumns().get(i);
				JTextField textF =((JTextField)standardForm.form.get(col));
				
				if(i+1 == table.getColumnCount()){
					insertQuery += "'"+ textF.getText() +"' );";
				}else {
					insertQuery += "'" + textF.getText().toUpperCase() + "' , ";
				}
			}
			try {
				Statement statement = DBConnection.getConnection().createStatement();
				statement.executeUpdate(insertQuery);
				statement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			//System.out.println(insertQuery);
			
		}else if(standardForm.getStateManager().getCurrentState()== standardForm.getStateManager().getEditState()){
			if(table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(standardForm, "Modifikacija  nije moguca!!!"+"\n"+"Potrebno je selektovati zeljeni red za modifikovanje");
			}else{

			}
		}else if(standardForm.getStateManager().getCurrentState()== standardForm.getStateManager().getSearchState()){
			
		}
		
		standardForm.getStateManager().changeToEditState();
	}
}

