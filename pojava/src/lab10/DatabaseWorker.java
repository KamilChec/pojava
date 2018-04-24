package lab10;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseWorker {
	String instruction;
	
	public void getInstructions(JTextField field) {
		instruction = field.getText();
		System.out.println(instruction);
	}
	
	public void makeQuery(JTextArea area) throws SQLException{
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				Connection conn = null;
				try {
					conn = DriverManager.getConnection(	"jdbc:h2:./data/nazwabazy", "sa", "");
					Statement statement = conn.createStatement();
					statement.execute(instruction);
					
					ResultSet rs = statement.getResultSet();
					
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
