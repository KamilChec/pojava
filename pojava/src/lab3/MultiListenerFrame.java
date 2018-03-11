package lab3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MultiListenerFrame extends JFrame {
	
	JPanel panel;
	JLabel etykieta;
	JCheckBox przycisk1;
	JCheckBox przycisk2;
	JCheckBox przycisk3;
	
	public MultiListenerFrame() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600,500);
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		add(BorderLayout.NORTH, panel);
		
		przycisk1  = new JCheckBox("Ala");
		przycisk2  = new JCheckBox("ma");
		przycisk3  = new JCheckBox("kota");
		panel.add(przycisk1);
		panel.add(przycisk2);
		panel.add(przycisk3);
		
		przycisk1.addItemListener(this);
	}
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();
		if(source == przycisk1) {
			etykieta.setText("Ala");
		} else if (source == przycisk2) {
			etykieta.setText("ma");
		} else if(source == przycisk2) {
			etykieta.setText("kota");
		}
		
	}
	public static void main(String[] args) {
		MultiListenerFrame frame = new MultiListenerFrame();
		frame.setVisible(true);
	}

}
