package gui.main.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Ova klasa predstavlja dialog sa porukom o gre�ci.
 * Pojavljuje se u slu�ajevima kada nije mogu�e izvr�iti operaciju koju korisnik zahteva.
 * 
 * @author Nenad
 *
 */
public class ErrorDialog extends JDialog {
	private static final long serialVersionUID = -2440714066264301812L;
	
	    String message;

	public ErrorDialog(String message) {
		
		setModal(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setSize(new Dimension(300,150));
		setTitle("Greska");
		this.message = message;
		JLabel label = new JLabel(message);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//setVisible(false);
				dispose();
			}
		});
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(okButton);
		
		add(label, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}
}
