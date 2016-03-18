package actions.main.form;

import gui.standard.form.StandardForm;
import gui.standard.menuItem.MyMenuItems;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;


public class ShowDialog  extends AbstractAction{
	private static final long serialVersionUID = 1L;

	public ShowDialog(String name) {
		
		putValue(SHORT_DESCRIPTION, name);
		putValue(NAME, name);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {	
		
		MyMenuItems item = (MyMenuItems)arg0.getSource();
		StandardForm form = new StandardForm(item);
		form.setVisible(true);
	}
}


