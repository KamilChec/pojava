package lab10;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class H2User extends JFrame {
	JTextArea outText;
	JTextField inText;
	JButton connectionButton;
	DatabaseWorker worker;
	JPanel topPanel;
	JPanel downPanel;
	
	public H2User() {
		super("H2 manual control");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400, 500);
		setLocationRelativeTo(null);

		add(topPanel = new JPanel(), BorderLayout.PAGE_START);
		add(downPanel = new JPanel(), BorderLayout.CENTER);
		topPanel.setLayout(new GridLayout(2,1));
		downPanel.setLayout(new GridLayout(1,1));

		
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
		
		topPanel.add(inText);
		topPanel.add(connectionButton);
		downPanel.add(scrollPane);
		
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				H2User h2User = new H2User();
				h2User.setVisible(true);
				
			}
		
		});
	}

}
