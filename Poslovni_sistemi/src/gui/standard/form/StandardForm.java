package gui.standard.form;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import actions.standard.form.ActionManager;
import actions.standard.form.AddAction;
import actions.standard.form.CommitAction;
import actions.standard.form.DeleteAction;
import actions.standard.form.FirstAction;
import actions.standard.form.HelpAction;
import actions.standard.form.LastAction;
import actions.standard.form.NextAction;
import actions.standard.form.NextFormAction;
import actions.standard.form.PickupAction;
import actions.standard.form.PreviousAction;
import actions.standard.form.RefreshAction;
import actions.standard.form.RollbackAction;
import forms.state.StateManager;
import gui.main.form.MainFrame;
import gui.standard.form.listeners.RowSelectionListener;
import gui.standard.menuItem.MyMenuItems;
import model.tables.Column;
import model.tables.MyTableModel;
import net.miginfocom.swing.MigLayout;

public class StandardForm extends JDialog {
	private static final long serialVersionUID = 1L;

	private JToolBar toolBar;
	private JButton btnAdd, btnCommit, btnDelete, btnFirst, btnLast, btnHelp,
			btnNext, btnNextForm, btnPickup, btnRefresh, btnRollback,
			btnSearch, btnPrevious;
	private MainTable tblGrid = new MainTable();
	public Map<Column, JComponent> form = new HashMap<Column, JComponent>();
	MyMenuItems items;
	private StateManager stateManager = new StateManager(this);
	private ActionManager actionManager = new ActionManager(this);

	public StandardForm(MyMenuItems item) {
		this.items = item;
		setLayout(new MigLayout("fill"));
		setSize(new Dimension(800, 600));
		setTitle(item.getName());
		setLocationRelativeTo(MainFrame.getInstance());
		setModal(true);
		initToolbar();
		initTable(item);
		initGui(item);
		tblGrid.getSelectionModel().addListSelectionListener(
				new RowSelectionListener(this));
	}
	
	public void ponovo(MyMenuItems item){
		MyTableModel tableModel = new MyTableModel(item);
		tableModel.fireTableDataChanged();
		initTable(item);
	}

	private void initTable(MyMenuItems item) {
		JScrollPane scrollPane = new JScrollPane(tblGrid);
		add(scrollPane, "grow, wrap");
		MyTableModel model = new MyTableModel(item);

		tblGrid.setModel(model);
		tblGrid.setRowSelectionAllowed(true);
		tblGrid.setColumnSelectionAllowed(false);
		tblGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	private void initToolbar() {

		toolBar = new JToolBar();
		
		/*btnSearch = new JButton(new SearchAction(this));
		toolBar.add(btnSearch);*/
		toolBar.add(actionManager.getSearchAction());

		/*btnRefresh = new JButton(new RefreshAction());
		toolBar.add(btnRefresh);*/
		toolBar.add(actionManager.getRefreshAction());

		/*btnPickup = new JButton(new PickupAction(this));
		toolBar.add(btnPickup);*/
		toolBar.add(actionManager.getPickupAction());

		/*btnHelp = new JButton(new HelpAction());
		toolBar.add(btnHelp);*/
		toolBar.add(actionManager.getHelpAction());

		toolBar.addSeparator();

		/*btnFirst = new JButton(new FirstAction(this));
		toolBar.add(btnFirst);*/
		toolBar.add(actionManager.getFirstAction());

		/*btnPrevious = new JButton(new PreviousAction(this));
		toolBar.add(btnPrevious);*/
		toolBar.add(actionManager.getPreviousAction());

		/*btnNext = new JButton(new NextAction(this));
		toolBar.add(btnNext);*/
		toolBar.add(actionManager.getNextAction());

		/*btnLast = new JButton(new LastAction(this));
		toolBar.add(btnLast);*/
		toolBar.add(actionManager.getLastAction());

		toolBar.addSeparator();

		/*btnAdd = new JButton(new AddAction(this));
		toolBar.add(btnAdd);*/
		toolBar.add(actionManager.getAddAction());

		/*btnDelete = new JButton(new DeleteAction(this));
		toolBar.add(btnDelete);*/
		toolBar.add(actionManager.getDeleteAction());

		toolBar.addSeparator();

		/*btnNextForm = new JButton(new NextFormAction(this));
		toolBar.add(btnNextForm);*/
		toolBar.add(actionManager.getNextFormAction());

		add(toolBar, "dock north");
	}

	private void initGui(MyMenuItems item) {

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new MigLayout("fillx"));
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new MigLayout("gapx 15px"));

		JPanel buttonsPanel = new JPanel();
		btnCommit = new JButton(new CommitAction(this));
		btnRollback = new JButton(new RollbackAction(this));

		ArrayList<Column> columns = item.getColuumns();

		int i = 0;
		for (Column col : columns) {

			JLabel lblSifra = new JLabel(col.getName() + ":");
			JTextField tFiel = new JTextField(20);
			form.put(col, tFiel);
			i++;

			if (columns.size() < 5) {
				dataPanel.add(lblSifra);
				dataPanel.add(tFiel, "wrap");
			} else {
				if (i % 3 == 0) {
					dataPanel.add(lblSifra);
					dataPanel.add(tFiel);
					dataPanel.add(new JLabel(), "wrap");
				} else {
					dataPanel.add(lblSifra);
					dataPanel.add(tFiel);
				}
			}
		}

		bottomPanel.add(dataPanel);
		buttonsPanel.setLayout(new MigLayout("wrap"));
		buttonsPanel.add(btnCommit);
		buttonsPanel.add(btnRollback);
		bottomPanel.add(buttonsPanel, "dock east");

		add(bottomPanel, "grow, wrap");
	}

	public StateManager getStateManager() {
		return stateManager;
	}

	public void setStateManager(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void fillForm() {
		if (stateManager.getCurrentState() == stateManager.getEditState()
				|| stateManager.getCurrentState() == stateManager.getRemoveState())
			for (Column column : items.getColuumns()) {
				JTextField textF = ((JTextField) form.get(column));
				int row = tblGrid.getSelectedRow();
				int col = items.getColuumns().indexOf(column);
				textF.setText(tblGrid.getValueAt(row, col).toString());
			}
	}

	public MyMenuItems getItems() {
		return items;
	}

	public void setItems(MyMenuItems items) {
		this.items = items;
	}

	public MainTable getFocusedTable() {
		return tblGrid;
	}

	public void setTblGrid(MainTable tblGrid) {
		this.tblGrid = tblGrid;
	}

	public ActionManager getActionManager() {
		return actionManager;
	}
	
	

}
