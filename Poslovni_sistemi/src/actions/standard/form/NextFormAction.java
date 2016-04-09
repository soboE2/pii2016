package actions.standard.form;

import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;


public class NextFormAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private StandardForm standardForm;
	
	public NextFormAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/nextform.gif")));
		putValue(SHORT_DESCRIPTION, "Sledeća forma");
		this.standardForm  = standardForm;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
