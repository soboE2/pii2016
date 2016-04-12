package app;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JComponent;
import javax.swing.JTextField;

public class Utils {

	public void setPrepared(PreparedStatement pstmt,String type,JComponent inputValue,int i){
		 //dodati za boolean jos
		 if( type.equals("tinyint") || type.equals("smallint") || type.equals("int") ){
			
				try {
					if(((JTextField) inputValue).getText().length()>0)
						pstmt.setInt(i,Integer.parseInt(((JTextField)inputValue).getText()));
					else
						pstmt.setNull(i, java.sql.Types.INTEGER);
					
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if ( type.equals("numeric") || type.equals("float") || type.equals("decimal")){
					try {
						if(((JTextField) inputValue).getText().length()>0)
							pstmt.setDouble( i,Double.parseDouble(((JTextField)inputValue).getText()));
						else
							pstmt.setNull(i, java.sql.Types.DOUBLE);
						
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			/*}else if(type.equals("datetime")){
				 JDateChooser date = (JDateChooser) inputValue
				 try {
					 if(date.getDate() != null)
						 pstmt.setDate(i, new Date(date.getDate().getTime()));
					 else
						 pstmt.setNull(i, java.sql.Types.DATE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
					
					//ne prepoznaje JDateChooser
				
			}else if(type.equals("varchar")|| type.equals("char")){
				
				try {
					if(((JTextField) inputValue).getText().length()>0)
						pstmt.setString( i,((JTextField)inputValue).getText());
					else
						pstmt.setNull(i, java.sql.Types.VARCHAR);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
	}
	
}
