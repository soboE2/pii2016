package forms.state;

import javax.swing.JTextField;

import model.tables.Column;
import gui.standard.form.StandardForm;

/**Implementacija state paterna zaduzena za pretragu slogova u tabeli.
 * {@link SearchState} implementira {@link State}
 * @author Borko Arsovic
 *
 */
public class SearchState implements State {

	private StandardForm standardForm;
	
	@Override
	public void comit() {
		
		
		//Treba ovo prositi da se tabela isprazni i da se prikazuju samo rezultati pretrage
	}

}
