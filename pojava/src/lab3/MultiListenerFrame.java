package lab3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;

public class MultiListenerFrame extends JFrame {
	
	JSpinner spinner;
	JLabel etykieta;
	
	public MultiListenerFrame() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600,500);
		this.setLayout(new FlowLayout());
		
	    SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 4, 1); // (WarPocz, min, max, krok)
		spinner = new JSpinner(spinnerModel);
		//spiner.setName("kolorowy spinner");
		//JOptionPane.showMessageDialog(null, spinner);
		
		//spinner.setBounds(100,100,50,30);
		etykieta = new JLabel();
        etykieta.setHorizontalAlignment(JLabel.CENTER);    
        etykieta.setSize(250,100); 

		this.add(spinner,BorderLayout.PAGE_END);  
		spinner.addChangeListener(this);
	}
	
		public void stateChanged(ChangeEvent arg0) {
			etykieta.setText("Value : " + ((JSpinner)arg0.getSource()).getValue());  
		}
		
	public static void main(String[] args) {
		MultiListenerFrame frame = new MultiListenerFrame();
		frame.setVisible(true);
	}

}
