package forms.state;

import javax.swing.JTextField;

import model.tables.Column;
import gui.standard.form.StandardForm;

/**Implementacija state paterna zaduzena za brisanje sloga.
 * {@link RemoveState} implementira {@link State}
 * @author Borko Arsovic
 *
 */
public class RemoveState implements State {

	private StandardForm standardForm;
	
	@Override
	public void comit(StandardForm sf) {
		this.standardForm = sf;
		standardForm.getStateManager().setCurrentState(standardForm.getStateManager().getRemoveState());
		standardForm.getFocusedTable().clearSelection();
		for(Column column : standardForm.getItems().getColuumns()){
			JTextField textF =((JTextField)standardForm.form.get(column));
			textF.setText("");
			textF.setEditable(false);
		}
	}

}
