package lab8;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class GrammarExamine extends JFrame {
	JMenuBar menuBar;
	JPanel textPanel;
	JTextPane textField;
	File from, to;
	String nameIn, nameOut, line;
	BufferedReader bfr;
	JFileChooser chooser;
	BufferedReader in = null;
	int nLines;
	
	public GrammarExamine() throws HeadlessException{
		super("Check your grammar");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600,500);
		
		this.setJMenuBar(menuBar = new JMenuBar());
		
		//----Menu-----
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem menuItem1 = new JMenuItem("Save");
		menuItem1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) { 


				FileWriter fw = null;

				try {
					fw = new FileWriter("plik.txt");
					BufferedWriter bw = new BufferedWriter(fw);

					for (int i = 0; i < 1; i++) {
						bw.write(textField.getText());
						bw.newLine();
					}

					bw.close();
					//fw.close(); - wystarczy zamknąć zewnętrzny
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		JMenuItem menuItem2 = new JMenuItem("Import file");
		menuItem2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				loadFile(textField);
			}
			
		});
		
		fileMenu.add(menuItem1);
		fileMenu.add(menuItem2);
		
		// center
		textField = new JTextPane();
		this.add(textField);
	}
	
	public void loadFile(JTextPane textField)  {
		 chooser = new JFileChooser();
		 FileReader fr = null;
		 bfr = null;
			
		 chooser.setDialogTitle("Load File");
	     int result = chooser.showOpenDialog(null);
	     try {
	        if (result == JFileChooser.APPROVE_OPTION) {
		        from = chooser.getSelectedFile();
		        nameIn = from.getAbsolutePath();
		        fr = new FileReader(from);
		        bfr = new BufferedReader(fr);
	        } 
	        int sign;
	        
	        while ((sign = bfr.read()) != -1) {
		        	int c = (char)sign;
			    	if(c == 'ę'||c == 'ó' || c == 'ą'||c == 'ś'||c == 'ł' ||c == 'ż' ||c == 'ć' ||c == 'ń') {
			    		textField.setText(textField.getText()+'?');
			    	}
			    	else {
			    		textField.setText(textField.getText() + (char)c);
			    	}

	        }
	        fr.close();
	     } catch (Exception e) {
			System.out.println("BLAD IO!");
			System.exit(1);
	     }
		
	} 

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				GrammarExamine examine = new GrammarExamine();
				examine.setVisible(true);
				
			}
		
		});

	}

}
