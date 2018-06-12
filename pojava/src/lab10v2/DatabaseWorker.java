package lab10v2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class DatabaseWorker {
	String instruction;
	
	public void getInstructions(JTextField field) {
		instruction = field.getText();
	}
	
	public void makeQuery(JTextArea area) throws SQLException{
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				Connection conn = null;
				try {
					conn = DriverManager.getConnection("jdbc:mysql://db4free.net/waluty2018", "kamilchec", "chec1234");
//					Statement statement = conn.createStatement();
//					statement.execute("SELECT * FROM waluty where usd < " + instruction);
					PreparedStatement prep = conn.prepareStatement
							("SELECT * FROM waluty where usd < (?)");
					prep.setString(1, instruction);
					prep.execute();
					
					ResultSet rs = prep.getResultSet();
					
					ResultSetMetaData md  = rs.getMetaData();
					

					for (int ii = 1; ii <= md.getColumnCount(); ii++){
						String buff = area.getText();
						area.setText(buff+ md.getColumnName(ii)+ " | ");
						
					}
					String buff1 = area.getText();
					area.setText(buff1 + "\n");
					
					while (rs.next()) {
						for (int ii = 1; ii <= md.getColumnCount(); ii++){
							String buff = area.getText();
							area.setText(buff + rs.getObject(ii) + " | ");
						}
						String buff2 = area.getText();
						area.setText(buff2 + "\n");
						
					}
					
				} finally {
					if (conn!= null){
						conn.close();
					}
				}
				return null;
			}
			
		};
		worker.execute();
	}
}
