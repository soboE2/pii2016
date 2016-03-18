package forms.state;

public class StateManager {
	
	private State currentState;
	private InsertState insertState;
	private SearchState searchState;
	private EditState editState;
	
	public StateManager() {
		
		insertState = new InsertState();
		editState = new EditState();
		searchState = new SearchState();
		
		currentState = editState;
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
	
	
}
