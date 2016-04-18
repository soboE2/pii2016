package gui.standard.form.listeners;

import gui.main.form.MainFrame;
import gui.standard.form.StandardForm;
import gui.standard.menuItem.MyMenuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZoomButtonListener implements ActionListener{
	
	
	String itemS;
	
	public ZoomButtonListener(String itemS) {
		super();
		this.itemS = itemS;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MyMenuItems item = MainFrame.getInstance().getItem(itemS);
		StandardForm form = new StandardForm(item);
		form.setVisible(true);
	}

}
