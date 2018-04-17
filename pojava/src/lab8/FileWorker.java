package lab8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;

public class FileWorker {
	JTextPane textField;
	File from;
	FileReader fr;
	int misCounter;
	GrammarException exception;
	
	public FileWorker(JTextPane textField) {
		this.textField = textField;
	}
	
	public void loadFile(File from) {
		SwingWorker<Void, Character> worker = new SwingWorker<Void, Character>(){

			@Override
			protected Void doInBackground() throws Exception {
				fr = null;
				BufferedReader bufferIn = null;
				try {
						fr = new FileReader(from);
						bufferIn = new BufferedReader(fr);
						bufferIn = Files.newBufferedReader(from.toPath(), Charset.forName("UTF-8"));
			            int sign;
			            while ((sign = bufferIn.read()) != -1) {
			            	char c = (char)sign;
			            	publish(c);
			            }  
				} catch (Exception e){
                    e.printStackTrace();
				} finally {
					bufferIn.close();
				}
				return null;
			}
			protected void process(List<Character> signs) {
				int index = 0;
				for(Character sign : signs) {
			    	if(sign == 'u'||sign == 'ó' || sign == 'ż' || sign == 'Ż' || sign == 'U' || sign == 'Ó') {
			    		textField.setText(textField.getText()+'?');
			    	} else if((sign == 'h'||sign == 'H')) {
			    		if(index > 0 && (signs.get(index -1) == 'c' ||signs.get(index -1) == 'C')){
			    			textField.setText(textField.getText().substring(0, textField.getText ().length() - 1));
			    			textField.setText(textField.getText()+ "??");
			    		}else {
				    		textField.setText(textField.getText() + '?');
				    	}	
			    	} else if((sign == 'z'||sign == 'Z')) {
			    		if(index > 0 && (signs.get(index -1) == 'r' ||signs.get(index -1) == 'R')){
			    			textField.setText(textField.getText().substring(0, textField.getText ().length() - 1));
			    			textField.setText(textField.getText()+ "??");
			    		}else {
				    		textField.setText(textField.getText() + sign);
				    	}
			    	} else {
			    		textField.setText(textField.getText() + sign);
			    	} 
			    	index++;
				}
			}
			
		};
		worker.execute();
	}
	public void compare(File from) {
		SwingWorker<Integer, String> worker = new SwingWorker<Integer, String>(){
			protected void process(List<String> mistakes) {
				exception = new GrammarException();
				for(String mistake : mistakes) {
					System.out.println(mistake);
					
					
				}
			}
			@Override
			protected Integer doInBackground() throws Exception {
				fr = null;
				BufferedReader bufferIn = null;
				try {
					fr = new FileReader(from);
					bufferIn = new BufferedReader(fr);
					int sign;
					int index = 0;
					boolean ifMistake = false;
					String mistakes = "";
					misCounter = 0;

					while ((sign = bufferIn.read()) != -1) {
						if(sign == ' ' || sign == '.' ||sign == ',') {
							if(ifMistake) {
			            		publish(mistakes);
			            	}
			            	mistakes = "";
			            	ifMistake = false;
						} else {
						   	char c = (char)sign;
			            	String buff = mistakes + c;
			            	mistakes = buff;
			            	if(textField.getText().charAt(index) != sign ) {
			            		misCounter++;
			            		ifMistake = true;
			            	}
						}
						
						index++;
					}
					
				} catch (Exception e){
                    e.printStackTrace();
				} finally {
					bufferIn.close();
				}
				return misCounter;
			}
			protected void done() {
				try {
					throw new GrammarException(get());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				} catch (GrammarException e) {
					JOptionPane.showMessageDialog(null, e, "Amount of mistakes", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		};
		worker.execute();
	}
	
}
