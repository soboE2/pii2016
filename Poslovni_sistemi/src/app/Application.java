package app;

import javax.swing.UIManager;

import gui.main.form.MainFrame;

public class Application {
	
	public static void main (String[] args){
		UIManager.put("OptionPane.yesButtonText", "Da");
		UIManager.put("OptionPane.noButtonText", "Ne");
		UIManager.put("OptionPane.cancelButtonText", "Otkaži");
		
		MainFrame.getInstance().setVisible(true);
	}

}
