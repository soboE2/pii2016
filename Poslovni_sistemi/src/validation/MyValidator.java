package validation;

import forms.state.SearchState;
import gui.main.form.MainFrame;
import gui.standard.form.StandardForm;

import java.sql.DatabaseMetaData;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

import model.tables.Column;

import com.toedter.calendar.JDateChooser;


/**
 * Klasa koja nasledjuje apstraktnu klasu {@code AbstractValidator} i njenu apstraktnu metodu u kojoj je implementirana
 * logika validacije.
 * @author Nemanja Sobo
 *
 */
public class MyValidator extends AbstractValidator{
	private Map <String,String> loc;
	public MyValidator(StandardForm parent, JComponent c, String message) {
		super(parent, c, message);
		// TODO Auto-generated constructor stub
	}

	public MyValidator(JFrame parent, JComponent c, String message) {
		super(parent, c, message);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Nasledjena metoda koja vrsi validaciju za prosledjenu komponentu u kojoj je implementirana logika validacije.
	 * Prima komponentu nad kojom  se vrsi validacija.
	 */
	@Override
	protected boolean validationCriteria(JComponent c) {
		// TODO Auto-generated method stub
		//ZAVRSI PROMENI NA YES NO BICE TI ZIVOT LAKSE
		
		Map<Column,JComponent> form = ((StandardForm)getParent()).getForm();
		
		for(Column col : form.keySet()){
			
			JComponent comp = form.get(col);
			
			if(comp.getName().equals(c.getName())){
				if(comp instanceof JTextField){
					
					JTextField formComp = (JTextField) c;
					
					if(!(((StandardForm)getParent()).getStateManager().getCurrentState() instanceof SearchState)){
						if(formComp.getText().length() == 0){							
							if(col.isMandatory()){
								changeMessage("Polje ne sme biti prazno");
								System.out.println(col.isMandatory());
							
								return false;
							}else{
								return true;
							}
						}
					}
					String type = col.getType();
					
					if( type.equals("java.math.BigInteger")){
						try {
							changeMessage("Morate uneti celobrojnu vrednost");
					          Integer.parseInt(formComp.getText());
					          
					       } catch (NumberFormatException e) {
					    	   if(formComp.getText().isEmpty())
					    		   return true;
					    	   else
					    		   return false;
					      }
						
						return true;
						
					}else if (type.equals("java.math.BigDecimal")){
						
						if(col.getPrecision() == 0){
							
							try {
								changeMessage("Unesite celobrojnu vrednost");
						          Integer.parseInt(formComp.getText());
						          
						       } catch (NumberFormatException e) {
						    	   if(formComp.getText().isEmpty())
						    		   return true;
						    	   else
						    		   return false; 
						      }
							
							if(formComp.getText().length() > col.getLength()){
								
								changeMessage("Maksimalan broj cifara je " + col.getLength());
								return false;
							}else{
								return true;
							}	
						}
						try {
							changeMessage("Unesite numericku vrednost");
					          Double.parseDouble(formComp.getText());
					          
					       } catch (NumberFormatException e) {
					    	   if(formComp.getText().isEmpty())
					    		   
					    		   return true;
					    	   else
					    		  // MainFrame.getInstance().getLocalization().getVal("INDEFAU");
					    		   return false;
					          
					      }
						
						
						
						String[] number = formComp.getText().split(".");
						
						if(formComp.getText().length()-1 > col.getLength()){
							changeMessage("Maksimalan broj cifara je" + col.getLength());
							return false;
						}else if(number[1].length() > col.getPrecision()){
							changeMessage("Makimalan broj decimala" + col.getPrecision());
							return false;
						}else {
							return true;
						}
	
					}else{
						
						if( formComp.getText().length() > col.getLength()){
							
							changeMessage("Maksimalan broj karaktera je" + col.getLength());
							return false;				
						}else{
							return true;
						}
							
					}
					
				}else if (comp instanceof JComboBox){
					
				}else if (comp instanceof JDateChooser){
					System.out.println("usao u chooser");
					JDateChooser formComp = (JDateChooser) c;
					if(!(((StandardForm)getParent()).getStateManager().getCurrentState() instanceof SearchState)){
						System.out.println("usao u chooser");
						System.out.println(formComp.getDate());
						if(formComp.getDate() == null){
							System.out.println("aslkjdhasfuisadghklasui");
							if(col.isMandatory()){	
								changeMessage("Morate uneti datum");
								return false;
							}else{
								return true;
							}
						}
					}
					
				}
				break;
			}
		
		}
		return true;
		//return false;	
	}
}
