package forms.state;

import javax.swing.JTextField;

import model.tables.Column;
import gui.standard.form.StandardForm;

/**Implementacija state paterna zaduzena za modifikaciju sloga u tabelu.
 * {@link EditState} implementira {@link State}
 * @author Borko Arsovic
 *
 */
public class EditState implements State {

	private StandardForm standardForm;
	
	@Override
	public void comit(StandardForm sf) {
		this.standardForm = sf;
		standardForm.getStateManager().setCurrentState(standardForm.getStateManager().getEditState());
		standardForm.getFocusedTable().clearSelection();
		for(Column column : standardForm.getItems().getColuumns()){
			JTextField textF =((JTextField)standardForm.form.get(column));
			textF.setText("");
			textF.setEditable(true);
		}
		
	}

}
