package lab3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OuterClassListener extends JFrame {
	
	public OuterClassListener() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(300,100);
		
		String[] colors = {"red", "green", "blue"};
		JComboBox<String> colorList = new JComboBox<String>(colors);
		this.add(colorList, BorderLayout.PAGE_START);
        this.getContentPane().setBackground(Color.red);
        
       //
        colorList.addItemListener(new ComboBoxItemListener((JPanel)this.getContentPane()));
	}
	
	public static void main(String[] args) {
		OuterClassListener frame = new OuterClassListener();
		frame.setVisible(true);

	}

}
