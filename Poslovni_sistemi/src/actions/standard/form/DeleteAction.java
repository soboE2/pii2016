package actions.standard.form;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class DeleteAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;
	
	public DeleteAction(JDialog standardForm) {
		putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/remove.gif")));
		putValue(SHORT_DESCRIPTION, "Brisanje");
		this.standardForm=standardForm;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
