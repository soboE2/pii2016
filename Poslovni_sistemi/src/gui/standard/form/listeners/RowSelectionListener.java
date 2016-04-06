package gui.standard.form.listeners;

import gui.standard.form.StandardForm;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RowSelectionListener implements ListSelectionListener{
	
	private StandardForm standForm;
	public RowSelectionListener(StandardForm standForm) {
		super();
		this.standForm = standForm;
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if(e.getValueIsAdjusting()){
			standForm.fillForm();
			
		}
	}

}
