package actions.standard.form;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class PickupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;
	
	public PickupAction(JDialog standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/zoom-pickup.gif")));
		putValue(SHORT_DESCRIPTION, "Zoom pickup");
		this.standardForm = standardForm;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
