package actions.standard.form;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;


public class FirstAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;

	public FirstAction(JDialog standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/first.gif")));
		putValue(SHORT_DESCRIPTION, "Pocetak");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
