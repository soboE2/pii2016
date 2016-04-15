package forms.state;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JComponent;

import model.tables.Column;
import model.tables.MyTableModel;

public interface State {
	void comit(ArrayList<Column> columns,String code,Map<Column,JComponent> form,MyTableModel table) throws Exception ;
	
}
