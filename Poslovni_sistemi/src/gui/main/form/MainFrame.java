package gui.main.form;

import gui.standard.menuItem.MyMenuItems;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.tables.Column;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import actions.main.form.NaseljenoMestoAction;
import actions.main.form.ShowDialog;
import database.DBConnection;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static MainFrame instance;
	private JMenuBar menuBar;

	public MainFrame(){

		setSize(new Dimension(800,600));
		setLocationRelativeTo(null);
		setTitle("Poslovna");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUpMenu();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(MainFrame.getInstance(),
						"Da li ste sigurni?", "Pitanje",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
		
		
	}



	public static MainFrame getInstance(){
		if (instance==null)
			instance=new MainFrame();
		return instance;

	}

}