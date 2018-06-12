package lab10v2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class MySQLUser extends JFrame {
	
	JTextArea outText;
	JTextField inText;
	JButton connectionButton;
	DatabaseWorker worker;
	JPanel topPanel;
	JPanel downPanel;
	JLabel label;
	
	public MySQLUser() {
		super("mySQL manual control");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400, 500);
		setLocationRelativeTo(null);

		add(topPanel = new JPanel(), BorderLayout.PAGE_START);
		add(downPanel = new JPanel(), BorderLayout.CENTER);
		topPanel.setLayout(new GridLayout(3,1));
		downPanel.setLayout(new GridLayout(1,1));
		
		label = new JLabel(" Show all recodrs with the Dollar's Value smaller than: ");
		
		inText = new JTextField(20);
		
		connectionButton = new JButton("connect");
		connectionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				worker = new DatabaseWorker();
				worker.getInstructions(inText);
				try {
					worker.makeQuery(outText);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		outText = new JTextArea(1, 5);
		JScrollPane scrollPane = new JScrollPane(outText);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		outText.setEditable(false);
		
		topPanel.add(label);
		topPanel.add(inText);
		topPanel.add(connectionButton);
		downPanel.add(scrollPane);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MySQLUser mySQLUser = new MySQLUser();
				mySQLUser.setVisible(true);
				
			}
			
		});

	}

}
