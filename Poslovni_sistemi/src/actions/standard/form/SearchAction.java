package actions.standard.form;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class SearchAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JDialog standardForm;

	public SearchAction(JDialog standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/search.gif")));
		putValue(SHORT_DESCRIPTION, "Pretraga");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
