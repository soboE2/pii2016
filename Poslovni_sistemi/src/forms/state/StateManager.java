package forms.state;

import gui.standard.form.StandardForm;

public class StateManager {
	
	private State currentState;
	private InsertState insertState;
	private SearchState searchState;
	private EditState editState;
	private RemoveState removeState;
	
	private StandardForm form;
	
	public StateManager(StandardForm sf) {
		
		insertState = new InsertState();
		editState = new EditState();
		searchState = new SearchState();
		removeState = new RemoveState();

		currentState = editState;
		
		form = sf;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public InsertState getInsertState() {
		return insertState;
	}

	public void setInsertState(InsertState insertState) {
		this.insertState = insertState;
	}

	public SearchState getSearchState() {
		return searchState;
	}

	public void setSearchState(SearchState searchState) {
		this.searchState = searchState;
	}

	public EditState getEditState() {
		return editState;
	}

	public void setEditState(EditState editState) {
		this.editState = editState;
	}

	public RemoveState getRemoveState() {
		return removeState;
	}

	public void setRemoveState(RemoveState removeState) {
		this.removeState = removeState;
	}
	
	public void changeToInsertState() {
		currentState = insertState;
		form.getActionManager().getDeleteAction().setEnabled(false);
		form.getActionManager().getSearchAction().setEnabled(false);	
	}
	
	public void changeToRemoveState() {
		currentState = removeState;
		form.getActionManager().getAddAction().setEnabled(false);
		form.getActionManager().getSearchAction().setEnabled(false);	
	}
	
	public void changeToSearchState() {
		currentState = searchState;
		form.getActionManager().getAddAction().setEnabled(false);
		form.getActionManager().getDeleteAction().setEnabled(false);	
	}
	
	public void changeToEditState() {
		currentState = editState;
		form.getActionManager().getAddAction().setEnabled(true);
		form.getActionManager().getDeleteAction().setEnabled(true);
		form.getActionManager().getSearchAction().setEnabled(true);
	}
	
	
}
