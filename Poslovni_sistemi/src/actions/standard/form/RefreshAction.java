package actions.standard.form;

import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class RefreshAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private StandardForm standardForm;
	
	public RefreshAction(StandardForm form) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/refresh.gif")));
		putValue(SHORT_DESCRIPTION, "Refresh");
		this.standardForm = form;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
