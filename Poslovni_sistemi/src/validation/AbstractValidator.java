package validation;

import gui.standard.form.StandardForm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.plaf.ToolTipUI;


/**
 * Apstrkatna klasa koja implementira izbacivanje greske nad poljima za unos, izmenu ili pretragu. 
 * @author Nemanja
 *
 */

public abstract class AbstractValidator extends InputVerifier implements KeyListener {
    private JDialog popup;
  
    private Object parent;
    private JLabel messageLabel;
    private JLabel image;
    private Point point;
    private String initMessage;
    private Dimension cDim;
    private Color color;
	
    private AbstractValidator() {
        color = new Color(243, 255, 159);
    }
	
    private AbstractValidator(JComponent c, String message) {
        this();
        c.addKeyListener(this);
        this.initMessage = message;
        messageLabel = new JLabel(message + " ");
        image = new JLabel( new ImageIcon("/img/symbol_error.png"));
    }
	
    /**
     * @param parent A JDialog that implements the ValidationCapable interface.
     * @param c The JComponent to be validated.
     * @param message A message to be displayed in the popup help tip if validation fails.
     */
	
    public AbstractValidator (StandardForm parent, JComponent c, String message) {		
        this(c, message);
        this.parent = parent;
        popup = new JDialog(parent);
        initComponents();
    }
    
    /*public AbstractValidator (LoginDialog parent, JComponent c, String message) {		
        this(c, message);
        this.parent = parent;
        popup = new JDialog(parent);
        initComponents();
    }
*/
	
    /**
     * @param parent A JFrame that implements the ValidationCapable interface.
     * @param c The JComponent to be validated.
     * @param message A message to be displayed in the popup help tip if validation fails.
     */
	
    public AbstractValidator (JFrame parent, JComponent c, String message) {
        this(c, message);
        this.parent = parent;
        popup = new JDialog(parent);
        initComponents();
    }
	
    /**
     * Implement the actual validation logic in this method. The method should
     * return false if data is invalid and true if it is valid. It is also possible
     * to set the popup message text with setMessage() before returning, and thus
     * customize the message text for different types of validation problems.
     * 
     * @param c The JComponent to be validated.
     * @return false if data is invalid. true if it is valid.
     */
	
    protected abstract boolean validationCriteria(JComponent c);
	
    /**
     * This method is called by Java when a component needs to be validated.
     * It should not be called directly. Do not override this method unless
     * you really want to change validation behavior. Implement
     * validationCriteria() instead.
     */
	public void changeMessage(String message){
		messageLabel.setText(message);
		
	}
   public boolean verify(JComponent c) {		
        if (!validationCriteria(c)) {
			
            c.setBackground(Color.PINK);
            popup.setSize(0, 0);
            popup.setLocationRelativeTo(c);
            point = popup.getLocation();
            cDim = c.getSize();
            popup.setLocation(point.x,
               point.y);
            popup.pack();
            popup.setVisible(true);
            return false;
        }
        
        c.setBackground(Color.WHITE);

		
        return true;
    }
	
    /**
     * Changes the message that appears in the popup help tip when a component's
     * data is invalid. Subclasses can use this to provide context sensitive help
     * depending on what the user did wrong.
     * 
     * @param message
     */
	
    protected void setMessage(String message) {
       messageLabel.setText(message);
    }
	
     /**
      * @see KeyListener
      */
	
    public void keyPressed(KeyEvent e) {
        popup.setVisible(false);
    }
	
    /**
     * @see KeyListener
     */
	
    public void keyTyped(KeyEvent e) {}
	
    /**
     * @see KeyListener
     */
	
    public void keyReleased(KeyEvent e) {}
	
    private void initComponents() {
        popup.getContentPane().setLayout(new FlowLayout());
        popup.setUndecorated(true);
        popup.getContentPane().setBackground(color);
        popup.getContentPane().add(image);
        popup.getContentPane().add(messageLabel);
        popup.setFocusableWindowState(false);
    }

	public Object getParent() {
		return parent;
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}

	public String getInitMessage() {
		return initMessage;
	}

	public void setInitMessage(String initMessage) {
		this.initMessage = initMessage;
	}
    
}