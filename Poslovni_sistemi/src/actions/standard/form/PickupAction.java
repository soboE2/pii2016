package actions.standard.form;

import gui.standard.form.StandardForm;

import java.awt.event.ActionEvent;

import javax.crypto.spec.OAEPParameterSpec;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import model.tables.Column;

public class PickupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private StandardForm standardForm;
	private StandardForm parent;

	public PickupAction(StandardForm standardForm) {
		putValue(SMALL_ICON,
				new ImageIcon(getClass().getResource("/img/zoom-pickup.gif")));
		putValue(SHORT_DESCRIPTION, "Zoom pickup");
		this.standardForm = standardForm;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		parent = standardForm.getParentForm();
		if (parent != null) {
			for (Column column1 : parent.getItems().getColuumns()) {
				if (column1.isFk()) {
					JTextField textF1 = ((JTextField) parent.form.get(column1));
					for (Column column : standardForm.getItems().getColuumns()) {
						if (column.isPk()
								&& column.getCode().equals(column1.getCode())) {
							JTextField textF = ((JTextField) standardForm.form
									.get(column));
							textF1.setText(textF.getText());

						}
					}

				}
			}
			standardForm.dispose();
			standardForm.setVisible(false);
		}
	}
}
