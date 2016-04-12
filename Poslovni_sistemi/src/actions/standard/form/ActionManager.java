package actions.standard.form;

import gui.standard.form.StandardForm;

public class ActionManager {
	
	private AddAction addAction;
	private CommitAction commitAction;
	private DeleteAction deleteAction;
	private FirstAction firstAction;
	private LastAction lastAction;
	private HelpAction helpAction;
	private NextAction nextAction;
	private NextFormAction nextFormAction;
	private PickupAction pickupAction;
	private RefreshAction refreshAction;
	private RollbackAction rollbackAction;
	private SearchAction searchAction;
	private PreviousAction previousAction;
	
	public ActionManager(StandardForm standardForm) {
		
		addAction = new AddAction(standardForm);
		deleteAction = new DeleteAction(standardForm);
		firstAction = new FirstAction(standardForm);
		lastAction = new LastAction(standardForm);
		helpAction = new HelpAction();
		nextAction = new NextAction(standardForm);
		nextFormAction = new NextFormAction(standardForm);
		pickupAction = new PickupAction(standardForm);
		refreshAction = new RefreshAction();
		rollbackAction = new RollbackAction(standardForm);
		searchAction = new SearchAction(standardForm);
		previousAction = new PreviousAction(standardForm);
		
	}

	public AddAction getAddAction() {
		return addAction;
	}

	public CommitAction getCommitAction() {
		return commitAction;
	}

	public DeleteAction getDeleteAction() {
		return deleteAction;
	}

	public FirstAction getFirstAction() {
		return firstAction;
	}

	public LastAction getLastAction() {
		return lastAction;
	}

	public HelpAction getHelpAction() {
		return helpAction;
	}

	public NextAction getNextAction() {
		return nextAction;
	}

	public NextFormAction getNextFormAction() {
		return nextFormAction;
	}

	public PickupAction getPickupAction() {
		return pickupAction;
	}

	public RefreshAction getRefreshAction() {
		return refreshAction;
	}

	public RollbackAction getRollbackAction() {
		return rollbackAction;
	}

	public SearchAction getSearchAction() {
		return searchAction;
	}

	public PreviousAction getPreviousAction() {
		return previousAction;
	}
	
	
	
	

}
