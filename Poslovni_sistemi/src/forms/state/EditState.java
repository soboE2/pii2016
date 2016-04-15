package forms.state;

import gui.standard.form.StandardForm;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JComponent;

import model.tables.Column;
import model.tables.MyTableModel;

/**Implementacija state paterna zaduzena za modifikaciju sloga u tabelu.
 * {@link EditState} implementira {@link State}
 * @author Borko Arsovic
 *
 */
public class EditState implements State {

	private StandardForm standardForm;

	@Override
	public void comit(ArrayList<Column> columns, String code,
			Map<Column, JComponent> form,MyTableModel table) {
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "izmena";
	}
	
	

}
