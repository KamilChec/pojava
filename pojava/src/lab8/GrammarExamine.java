package lab8;

import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class GrammarExamine extends JFrame {
	JMenuBar menuBar;
	JPanel textPanel;
	JTextPane textField;
	JFileChooser chooser;
	File from, to;
	BufferedReader bfr;
	BufferedReader in = null;
	int nLines;
	FileWorker worker;
    Font textFont;
    Font baseFont;
	
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
				chooser = new JFileChooser();
				int result = chooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {}
				try {
					FileWriter fileWriter = new FileWriter(chooser.getSelectedFile().toString());
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					bufferedWriter.write(textField.getText());
					bufferedWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} 
		});
		JMenuItem menuItem2 = new JMenuItem("Import file");
		menuItem2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				worker = new FileWorker(textField);
				chooser = new JFileChooser();
				chooser.setDialogTitle("Load File");
				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					from = chooser.getSelectedFile();
					worker.loadFile(from);
				} else {
					return;
				}
			}
			
		});
		JMenuItem menuItem3 = new JMenuItem("Check grammar");
		menuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				worker = new FileWorker(textField);
				worker.compare(from);
				
				
			}
			
		});
		fileMenu.add(menuItem1);
		fileMenu.add(menuItem2);
		fileMenu.add(menuItem3);
		
		JMenu textMenu = new JMenu("Text");
		menuBar.add(textMenu);
		JMenuItem menuItem4 = new JMenuItem("italics");
		menuItem4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
                if (textFont.isItalic()) {
                    textFont = new Font(baseFont.getFontName(), Font.PLAIN, baseFont.getSize());
                }
                else{
                    textFont = new Font(baseFont.getFontName(), Font.ITALIC, baseFont.getSize());
                }
                textField.setFont(textFont);
            }	
		});
		JMenuItem menuItem5 = new JMenuItem("bold");
		menuItem5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
                if(textFont.isBold()) {
                    textFont = new Font(baseFont.getFontName(), Font.PLAIN, baseFont.getSize());
                }
                else {
                    textFont = new Font(baseFont.getFontName(), Font.BOLD, baseFont.getSize());
                }
                textField.setFont(textFont);
			}
			
		});
		textMenu.add(menuItem4);
		textMenu.add(menuItem5);
		
		// center
		textField = new JTextPane();
        textFont = textField.getFont();
        baseFont = textFont;
		this.add(textField);
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
