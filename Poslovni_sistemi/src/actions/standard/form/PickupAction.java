package actions.standard.form;

import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class PickupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private StandardForm standardForm;
	
	public PickupAction(StandardForm standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/zoom-pickup.gif")));
		putValue(SHORT_DESCRIPTION, "Zoom pickup");
		this.standardForm = standardForm;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
