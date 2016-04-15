package gui.main.form;

import gui.standard.menuItem.MyMenuItems;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import model.tables.Column;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import rs.mgifos.mosquito.IMetaLoader;
import rs.mgifos.mosquito.LoadingException;
import rs.mgifos.mosquito.impl.pdm.PDMetaLoader;
import rs.mgifos.mosquito.model.MetaColumn;
import rs.mgifos.mosquito.model.MetaModel;
import rs.mgifos.mosquito.model.MetaTable;
import actions.main.form.ShowDialog;
import database.DBConnection;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static MainFrame instance;
	private JMenuBar menuBar;
	private JMenu orgSema;
	private MainFrame(){}
	
	public void init(){

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(screenSize.width/3*2,screenSize.height/3*2));
		setLocationRelativeTo(null);
		setTitle("Poslovna");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	//	setExtendedState(MAXIMIZED_BOTH);
	//setUpMenu();
		setUpMenuPDM();
		addWindowListener(new WindowAdapter() {
			@Override
						
			public void windowClosing(WindowEvent e) {
				int code = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
						"Da li ste sigurni?", "Pitanje",
						JOptionPane.YES_NO_OPTION);
		
				if (code == JOptionPane.YES_OPTION) {
					/*
					 * Zatvori konekciju
					 */
					DBConnection.close();
					System.exit(0);
				}
			}
		});
		
		setJMenuBar(menuBar);

	}

	private void setUpMenu(){
		setUpMenuPDM();
		/*
		menuBar = new JMenuBar();
		JMenu orgSemaMenu = new JMenu("Organizaciona šema");
		orgSemaMenu.setMnemonic(KeyEvent.VK_O);
	
		BufferedReader bf = null;
		
		try {
			bf  =  new BufferedReader(new InputStreamReader(
			    new FileInputStream("tables.json"), "UTF-8"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONTokener tokener = new JSONTokener(bf);
		JSONObject obj = new JSONObject(tokener);
		JSONArray tables = obj.getJSONArray("tabele");
		
		for(int i = 0; i<tables.length(); i++){
			JSONArray columns =((JSONObject)tables.get(i)).getJSONArray("columns");
			String codeT = ((JSONObject)tables.get(i)).getString("code");
			String nameT = ((JSONObject)tables.get(i)).getString("tableName");
			ArrayList<Column> col = new ArrayList<Column>();
			
			for(int j = 0; j<columns.length(); j++){
				String code = ((JSONObject)columns.get(j)).getString("code");
				String name = ((JSONObject)columns.get(j)).getString("name");
				String type = ((JSONObject)columns.get(j)).getString("type");
				Column column = new Column(type,name,code);
				col.add(column);
			}
			MyMenuItems item = new MyMenuItems(codeT, nameT, col,new ShowDialog(nameT));
			orgSemaMenu.add(item);
		}
		
		menuBar.add(orgSemaMenu);
		*/
	}
	
	public void setUpMenuPDM(){
		
		IMetaLoader metaLoader = new PDMetaLoader();

		Properties properties = new Properties();
		properties.put(PDMetaLoader.FILENAME, "src/resources/Banka.pdm");
		menuBar = new JMenuBar();
		JMenu orgSemaMenu = new JMenu("Organizaciona šema");
		orgSemaMenu.setMnemonic(KeyEvent.VK_O);
		

		try {
			MetaModel model = metaLoader.getMetaModel(properties);
	
			for (MetaTable table : model) {
				ArrayList<Column> col = new ArrayList<Column>();
			    
			    String codeT = table.getCode();
			    String nameT = nameGenarator(codeT);
			    Iterator<Object> colIter = table.cColumns().iterator();
			    
			    while(colIter.hasNext()){
			    	MetaColumn column = (MetaColumn)colIter.next();
			    	String type = column.getJClassName();
			    	String code = column.getCode();
			    	String name = column.getName();
			    	Column absCol= null;
			    	if(column.getFkColParent() != null)
			    		absCol= new Column(type, name, code,column.isPartOfPK(), column.isPartOfFK(),column.isMandatory(),column.getFkColParent().getParentTable());
			    	else
			    		absCol= new Column(type, name, code,column.isPartOfPK(), column.isPartOfFK(),column.isMandatory(),null);

			    	col.add(absCol);
			    }
		    
				MyMenuItems item = new MyMenuItems(codeT, nameT, col,new ShowDialog(nameT));
				orgSemaMenu.add(item);
	    
			}
			
			
			menuBar.add(orgSemaMenu);
			this.orgSema = orgSemaMenu;
			
			
		} catch (LoadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public static MainFrame getInstance(){
		if (instance == null){
			instance=new MainFrame();
			instance.init();
		}
		return instance;

	}
	public MyMenuItems getItem(String code){
		for (int i = 0; i < orgSema.getItemCount(); i++) {
			MyMenuItems item = (MyMenuItems) orgSema.getItem(i);
			if(item.getCode().equals(code)){
				return item;
			}
		}
		
		return null;
	}
	private String nameGenarator(String name){
		
		String[] result = name.split("_");
		String returnS = result[0].substring(0,1).toUpperCase() + result[0].substring(1).toLowerCase();
		for(int x = 1; x<result.length; x++){			
			returnS +=  " " + result[x].toLowerCase();			
		}
		return returnS;
	}
	
	
	
}