package gui.standard.form;

import forms.state.StateManager;
import gui.main.form.MainFrame;
import gui.standard.form.listeners.RowSelectionListener;
import gui.standard.form.listeners.ZoomButtonListener;
import gui.standard.menuItem.MyMenuItems;

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

import model.tables.Column;
import model.tables.MyTableModel;
import net.miginfocom.swing.MigLayout;
import actions.standard.form.ActionManager;
import actions.standard.form.CommitAction;
import actions.standard.form.FirstAction;
import actions.standard.form.LastAction;
import actions.standard.form.NextAction;
import actions.standard.form.PreviousAction;
import actions.standard.form.RollbackAction;

public class StandardForm extends JDialog {
	private static final long serialVersionUID = 1L;

	private JToolBar toolBar;
	private JButton btnRollback, btnCommit;
	private MainTable tblGrid = new MainTable();
	public Map<Column, JComponent> form = new HashMap<Column, JComponent>();
	MyMenuItems items;
	private StateManager stateManager = new StateManager(this);
	private ActionManager actionManager = new ActionManager(this);
	private StatusBar status;
	private MyTableModel model;
	private StandardForm parentForm;

	

	/**
	 * Konstreuktor
	 * 
	 * @param item Tabela za koju se kreira standardna forma
	 */
	public StandardForm(MyMenuItems item) {
		this.items = item;
		setLayout(new MigLayout("fill"));
		setSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800,600));
		setTitle(item.getName());
		setLocationRelativeTo(MainFrame.getInstance());
		setModal(true);
		initToolbar();
		initTable(item);
		initGui(item);
		tblGrid.getSelectionModel().addListSelectionListener(
				new RowSelectionListener(this));
	}

	public StandardForm(StandardForm form, MyMenuItems item) {
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
		this.parentForm = form;
	}

	/**
	 * Inicijalizacija tabele
	 * @param item Tabela za koju se kreira standardna forma
	 */
	private void initTable(MyMenuItems item) {	
        JScrollPane scrollPane = new JScrollPane(tblGrid);
        add(scrollPane, "grow, wrap");
		model = new MyTableModel(item);
		tblGrid.setModel(model);
		tblGrid.setRowSelectionAllowed(true);
		tblGrid.setColumnSelectionAllowed(false);
		tblGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		refreshButton();
		
	}

	/**
	 * Metoda koja na osnovu broja redova u tabeli,vrsi ukljucivanje ili
	 * iskljucivanje akcija namenjenih za kretanje kroz tabelu
	 * {@link NextAction}, {@link PreviousAction}, {@link FirstAction} ,
	 * {@link LastAction}
	 */
	public void refreshButton() {
		if (tblGrid.getRowCount() == 0) {
			this.getActionManager().getNextAction().setEnabled(false);
			this.getActionManager().getPreviousAction().setEnabled(false);
			this.getActionManager().getFirstAction().setEnabled(false);
			this.getActionManager().getLastAction().setEnabled(false);
		} else {
			this.getActionManager().getNextAction().setEnabled(true);
			this.getActionManager().getPreviousAction().setEnabled(true);
			this.getActionManager().getFirstAction().setEnabled(true);
			this.getActionManager().getLastAction().setEnabled(true);
		}
	}

	/**
	 * Ponistavanje teksutalnih polja
	 */
	public void restartField() {
		for (Column col : items.getColuumns()) {
			JTextField textF = ((JTextField) form.get(col));
			textF.setText("");
			textF.setEnabled(true);
		}
		tblGrid.clearSelection();
	}

	/**
	 * Inicijalizacija tolbara standardne forme
	 */
	private void initToolbar() {

		toolBar = new JToolBar();

		/*
		 * btnSearch = new JButton(new SearchAction(this));
		 * toolBar.add(btnSearch);
		 */
		toolBar.add(actionManager.getSearchAction());

		/*
		 * btnRefresh = new JButton(new RefreshAction());
		 * toolBar.add(btnRefresh);
		 */
		toolBar.add(actionManager.getRefreshAction());

		/*
		 * btnPickup = new JButton(new PickupAction(this));
		 * toolBar.add(btnPickup);
		 */
		toolBar.add(actionManager.getPickupAction());

		/*
		 * btnHelp = new JButton(new HelpAction()); toolBar.add(btnHelp);
		 */
		toolBar.add(actionManager.getHelpAction());

		toolBar.addSeparator();

		/*
		 * btnFirst = new JButton(new FirstAction(this)); toolBar.add(btnFirst);
		 */
		toolBar.add(actionManager.getFirstAction());

		/*
		 * btnPrevious = new JButton(new PreviousAction(this));
		 * toolBar.add(btnPrevious);
		 */
		toolBar.add(actionManager.getPreviousAction());

		/*
		 * btnNext = new JButton(new NextAction(this)); toolBar.add(btnNext);
		 */
		toolBar.add(actionManager.getNextAction());

		/*
		 * btnLast = new JButton(new LastAction(this)); toolBar.add(btnLast);
		 */
		toolBar.add(actionManager.getLastAction());

		toolBar.addSeparator();

		/*
		 * btnAdd = new JButton(new AddAction(this)); toolBar.add(btnAdd);
		 */
		toolBar.add(actionManager.getAddAction());

		/*
		 * btnDelete = new JButton(new DeleteAction(this));
		 * toolBar.add(btnDelete);
		 */
		toolBar.add(actionManager.getDeleteAction());

		toolBar.addSeparator();

		/*
		 * btnNextForm = new JButton(new NextFormAction(this));
		 * toolBar.add(btnNextForm);
		 */
		toolBar.add(actionManager.getNextFormAction());

		add(toolBar, "dock north");
	}

	/**
	 * Inicijalizacija gui
	 * 
	 * @param item
	 *            Tabela za koju se kreira standardna forma
	 */
	private void initGui(MyMenuItems item) {
		JPanel bottomPanel = new JPanel(new MigLayout("fill"));
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new MigLayout("gapx 15px"));

		JPanel buttonsPanel = new JPanel();
		btnCommit = new JButton(new CommitAction(this));
		btnRollback = new JButton(new RollbackAction(this));

		ArrayList<Column> columns = item.getColuumns();

		for (int i = 0; i < columns.size(); i++) {
			Column col = columns.get(i);
			JLabel lblSifra;
			if (col.isMandatory() || col.isFk() || col.isPk()) {
				lblSifra = new JLabel(col.getName() + " * :");
			} else {
				lblSifra = new JLabel(col.getName() + ":");
			}

			JTextField tFiel = new JTextField(20);
			JButton button = new JButton("...");
			if (col.isFk())
				button.addActionListener(new ZoomButtonListener(this, col
						.getFkTableCode()));
			form.put(col, tFiel);
			if (columns.size() < 5) {
				dataPanel.add(lblSifra);

				if (col.isFk()) {
					tFiel.setEnabled(false);
					dataPanel.add(tFiel);
					dataPanel.add(button, "wrap");
				} else
					dataPanel.add(tFiel, "wrap");
			} else {
				if (i % 3 == 0) {
					dataPanel.add(lblSifra);
					dataPanel.add(tFiel);
					if (col.isFk())
						dataPanel.add(button);
					dataPanel.add(new JLabel(), "wrap");
				} else {
					dataPanel.add(lblSifra);
					dataPanel.add(tFiel);
					if (col.isFk())
						dataPanel.add(button);

				}
			}
		}
		bottomPanel.add(dataPanel);
		buttonsPanel.setLayout(new MigLayout("wrap"));
		buttonsPanel.add(btnCommit);
		buttonsPanel.add(btnRollback);
		bottomPanel.add(buttonsPanel, "dock east");
		add(bottomPanel, "grow, wrap");
		
		status = new StatusBar(getStateManager().getCurrent());
		add(status, "dock south");
	}

	public void fillForm() {
		if (stateManager.getCurrentState() == stateManager.getEditState()) {
		for (Column column : items.getColuumns()) {
				JTextField textF = ((JTextField) form.get(column));
				int row = tblGrid.getSelectedRow();
				int col = items.getColuumns().indexOf(column);
				textF.setText(tblGrid.getValueAt(row, col).toString());
				if(column.isPk()){
					textF.setEnabled(false);
				}
			}
		}
	}

	public StateManager getStateManager() {
		return stateManager;
	}

	public void setStateManager(StateManager stateManager) {
		this.stateManager = stateManager;
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

	public JToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(JToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public MainTable getTblGrid() {
		return tblGrid;
	}

	public Map<Column, JComponent> getForm() {
		return form;
	}

	public void setForm(Map<Column, JComponent> form) {
		this.form = form;
	}

	public StatusBar getStatus() {
		return status;
	}

	public void setStatus(StatusBar status) {
		this.status = status;
	}

	public MyTableModel getModel() {
		return model;
	}

	public StandardForm getParentForm() {
		return parentForm;
	}


}
