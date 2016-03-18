package actions.main.form;

import gui.standard.form.DrzavaStandardForm;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;


public class DrzaveAction  extends AbstractAction{
	private static final long serialVersionUID = 1L;
	
	public DrzaveAction() {
		KeyStroke ctrlDKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK);
		putValue(ACCELERATOR_KEY,ctrlDKeyStroke);
		putValue(SHORT_DESCRIPTION, "Države");
		putValue(NAME, "Države");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {	
		DrzavaStandardForm form = new DrzavaStandardForm();
		form.setVisible(true);
	}
}


