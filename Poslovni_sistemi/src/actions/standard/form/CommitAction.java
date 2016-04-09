package actions.standard.form;


import gui.standard.form.MainTable;
import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
		
		//Stanje brisanje
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
				System.out.println(basicQuery);
				
				try {
					PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(basicQuery);
					int tableRow = table.getSelectedRow();
					
					for(int i=0; i<table.getColumnCount(); i++){
						pstmt.setString(i+1,(String)table.getValueAt(tableRow, i));
					}
					
				pstmt.execute();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}else if(standardForm.getStateManager().getCurrentState()== standardForm.getStateManager().getInsertState()){


			
		}else if(standardForm.getStateManager().getCurrentState()== standardForm.getStateManager().getEditState()){
			if(table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(standardForm, "Modifikacija  nije moguca!!!"+"\n"+"Potrebno je selektovati zeljeni red za modifikovanje");
			}else{
				//Treba ga doraditi
//				String basicQuery = "UPDATE FROM ";
//				basicQuery += standardForm.getItems().getCode() + " WHERE ";
//				for(Column col : standardForm.getItems().getColuumns()){
//					basicQuery += col.getCode()+" = ? ";
//				}
//				
//				for(Column column : standardForm.getItems().getColuumns()){
//					JTextField textF =((JTextField)standardForm.form.get(column));
//					System.out.println(textF.getText());
//				}
				
			}
		}else if(standardForm.getStateManager().getCurrentState()== standardForm.getStateManager().getSearchState()){
			
		}
	}
}

