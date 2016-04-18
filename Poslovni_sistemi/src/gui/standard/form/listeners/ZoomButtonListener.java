package gui.standard.form.listeners;

import gui.main.form.MainFrame;
import gui.standard.form.StandardForm;
import gui.standard.menuItem.MyMenuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZoomButtonListener implements ActionListener{
	
	
	String itemS;
	StandardForm standardForm;
	
	public ZoomButtonListener(StandardForm form,String itemS) {
		super();
		this.itemS = itemS;
		this.standardForm=form;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MyMenuItems item = MainFrame.getInstance().getItem(itemS);
		StandardForm form = new StandardForm(standardForm,item);
		form.setVisible(true);
	}

}
