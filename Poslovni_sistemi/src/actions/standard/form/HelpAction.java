package actions.standard.form;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class HelpAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public HelpAction() {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/help.gif")));
		putValue(SHORT_DESCRIPTION, "Pomoc");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
