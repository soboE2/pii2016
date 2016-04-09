package actions.standard.form;

import forms.state.InsertState;
import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;



public class AddAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private StandardForm standardForm;
	
	public AddAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/add.gif")));
		putValue(SHORT_DESCRIPTION, "Dodavanje");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		InsertState insertState = new InsertState();
		insertState.comit(standardForm);

	}
}
