package actions.standard.form;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class LastAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;


	public LastAction(JDialog standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/last.gif")));
		putValue(SHORT_DESCRIPTION, "Poslednji");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
