package gui.standard.menuItem;

import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JMenuItem;

import model.tables.Column;
public class MyMenuItems extends JMenuItem{
	
	private String code;
	private String name;
	private ArrayList<Column> coluumns = new ArrayList<Column>();
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Column> getColuumns() {
		return coluumns;
	}
	public void setColuumns(ArrayList<Column> coluumns) {
		this.coluumns = coluumns;
	}
	public MyMenuItems(String code, String name, ArrayList<Column> coluumns, Action a) {
		super(a);
		this.code = code;
		this.name = name;
		this.coluumns = coluumns;
	}


}
