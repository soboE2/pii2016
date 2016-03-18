package actions.main.form;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class NaseljenoMestoAction  extends AbstractAction{
	private static final long serialVersionUID = 1L;
	
	public NaseljenoMestoAction() {
		KeyStroke ctrlMKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK);
		putValue(ACCELERATOR_KEY,ctrlMKeyStroke);
		putValue(SHORT_DESCRIPTION, "Naseljeno mesto");
		putValue(NAME, "Naseljeno mesto");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}

