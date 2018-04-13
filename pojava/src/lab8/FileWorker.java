package lab8;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;

public class FileWorker {
	JTextPane textField;
	JFileChooser chooser;
	
	File from;
	
	public FileWorker(JTextPane textField) {
		this.textField = textField;
	}
	
	public void loadFile() {
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>(){

			@Override
			protected Void doInBackground() throws Exception {
				chooser = new JFileChooser();
				BufferedInputStream bufferIn = null;
//				FileReader fr = null;
				
				chooser.setDialogTitle("Load File");
				int result = chooser.showOpenDialog(null);
				try {
					if (result != JFileChooser.APPROVE_OPTION) {
						from = chooser.getSelectedFile();
//						fr = new FileReader(from);
			            bufferIn = new BufferedInputStream(new FileInputStream(from));
			            
			            int sign = bufferIn.read();
			            
			            while (sign != -1) {
			            	int c = (char)sign;
			            	publish(c);
			            }
					} else {
						return null;
					}

		            
				} catch (Exception e){
                    e.printStackTrace();
				} finally {
					bufferIn.close();
				}
				return null;
			}
			protected void process(List<Integer> signs) {
				for(Integer sign : signs) {
			    	if(sign == 'ę'||sign == 'ó' || sign == 'ą'||sign == 'ś'||
			    			sign == 'ł' ||sign == 'ż' ||sign == 'ć' ||sign == 'ń') {
			    		textField.setText(textField.getText()+'?');
			    	}
			    	else {
			    		textField.setText(textField.getText() + sign);
			    	}
				}
				
			}
			
		};
		worker.execute();
	}
	
}
