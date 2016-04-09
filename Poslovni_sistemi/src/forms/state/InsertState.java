package forms.state;

import gui.standard.form.StandardForm;

import javax.swing.JTextField;

import model.tables.Column;

/**Implementacija state paterna zaduzena za dodavanje sloga u tabelu.
 * {@link InsertState} implementira {@link State}
 * @author Borko Arsovic
 */

public class InsertState implements State {

	private StandardForm standardForm;
	
	@Override
	public void comit(StandardForm sf) {
		this.standardForm = sf;
		
		standardForm.getStateManager().setCurrentState(standardForm.getStateManager().getInsertState());
		standardForm.getFocusedTable().clearSelection();
		for(Column column : standardForm.getItems().getColuumns()){
			JTextField textF =((JTextField)standardForm.form.get(column));
			textF.setText("");
			textF.setEditable(true);
		}
		
	}

}
