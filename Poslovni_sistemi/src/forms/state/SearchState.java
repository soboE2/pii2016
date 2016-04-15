package forms.state;

import gui.standard.form.StandardForm;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JComponent;

import model.tables.Column;
import model.tables.MyTableModel;

/**Implementacija state paterna zaduzena za pretragu slogova u tabeli.
 * {@link SearchState} implementira {@link State}
 * @author Borko Arsovic
 *
 */
public class SearchState implements State {

	private StandardForm standardForm;

	@Override
	public boolean comit(ArrayList<Column> columns, String code,
			Map<Column, JComponent> form,MyTableModel table) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "pretraga";
	}
	
}
