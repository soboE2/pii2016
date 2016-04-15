package forms.state;

import gui.standard.form.StandardForm;

public class StateManager {
	
	private State currentState;
	private InsertState insertState;
	private SearchState searchState;
	private EditState editState;
	private String current;
	private StandardForm form;
	
	public StateManager(StandardForm sf) {
		
		insertState = new InsertState();
		editState = new EditState();
		searchState = new SearchState();

		currentState = editState;
		current = editState.toString();
		
		form = sf;
	}

	public State getCurrentState() {
		return currentState;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public StandardForm getForm() {
		return form;
	}

	public void setForm(StandardForm form) {
		this.form = form;
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
	
	public void changeToInsertState() {
		currentState = insertState;
		setCurrStirng();
		form.getActionManager().getDeleteAction().setEnabled(false);
		form.getActionManager().getSearchAction().setEnabled(false);	
	}
	private void setCurrStirng(){
		current = currentState.toString();
		form.getStatus().setText(current);
	}
	
	public void changeToSearchState() {
		currentState = searchState;
		setCurrStirng();
		form.getActionManager().getAddAction().setEnabled(false);
		form.getActionManager().getDeleteAction().setEnabled(false);	
	}
	
	public void changeToEditState() {
		currentState = editState;
		setCurrStirng();
		form.getActionManager().getAddAction().setEnabled(true);
		form.getActionManager().getDeleteAction().setEnabled(true);
		form.getActionManager().getSearchAction().setEnabled(true);
	}
	
	
}
