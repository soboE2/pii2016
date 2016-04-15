package utils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class Utils {

	public static void setPrepared(PreparedStatement pstmt,String type,JComponent inputValue,int i){
		 //dodati za boolean jos
		 if( type.equals("tinyint") || type.equals("smallint") || type.equals("int")||type.equals("java.math.BigInteger") ){
			
				try {
					if(((JTextField) inputValue).getText().length()>0)
						pstmt.setInt(i,Integer.parseInt(((JTextField)inputValue).getText()));
					else
						pstmt.setNull(i, java.sql.Types.INTEGER);
					
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if ( type.equals("numeric") || type.equals("float") || type.equals("decimal") ||type.equals("java.math.BigDecimal")){
					try {
						if(((JTextField) inputValue).getText().length()>0)
							pstmt.setDouble( i,Double.parseDouble(((JTextField)inputValue).getText()));
						else
							pstmt.setNull(i, java.sql.Types.DOUBLE);
						
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}else if(type.equals("datetime") || type.equals("java.sql.Date")){
				 JDateChooser date = (JDateChooser) inputValue;
				 try {
					 if(date.getDate() != null)
						 pstmt.setDate(i, new Date(date.getDate().getTime()));
					 else
						 pstmt.setNull(i, java.sql.Types.DATE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					//ne prepoznaje JDateChooser
				
			}else if(type.equals("varchar")|| type.equals("char")||type.equals("java.lang.String")){
				
				try {
					if(((JTextField) inputValue).getText().length()>0)
						pstmt.setString( i,((JTextField)inputValue).getText());
					else
						pstmt.setNull(i, java.sql.Types.VARCHAR);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}else if(type.equals("java.lang.Boolean")){
				
			}
	}
	
	
	//srediti za datum
	public static void setPreparedForSearch(PreparedStatement pstmt,String type,JComponent inputValue,int i){
		 //dodati za boolean jos
		 if( type.equals("tinyint") || type.equals("smallint") || type.equals("int") ||type.equals("java.math.BigInteger")){
			
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
			}else if(type.equals("datetime")){
				 JDateChooser date = (JDateChooser) inputValue;
				 try {
					 if(date.getDate() != null)
						 pstmt.setDate(i, new Date(date.getDate().getTime()));
					 else
						 pstmt.setNull(i, java.sql.Types.DATE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					//ne prepoznaje JDateChooser
				
			}else if(type.equals("varchar")|| type.equals("char")){
				
				try {
					//if(((JTextField) inputValue).getText().length()>0)
						pstmt.setString( i,"%"+((JTextField)inputValue).getText()+"%");
					//else
						//pstmt.setNull(i, java.sql.Types.VARCHAR);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
	}
	
	
	
	
}
