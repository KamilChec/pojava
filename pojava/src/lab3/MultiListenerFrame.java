package lab3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

public class MultiListenerFrame extends JFrame {
	
	JPanel panel;
	JPanel panel2;
	JPanel panel3;
	JCheckBox button1;
	JCheckBox button2;
	JCheckBox button3;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JSlider slider;
	JButton button;
	
	
	public MultiListenerFrame() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600,500);
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		add(BorderLayout.NORTH, panel);
		
		button1  = new JCheckBox("button1");
		button2  = new JCheckBox("button2");
		button3  = new JCheckBox("button3");
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		
		button1.addActionListener(new ButtonListener());
		button2.addActionListener(new ButtonListener());
		button3.addActionListener(new ButtonListener());
		
		panel2 = new JPanel();
		this.add(BorderLayout.CENTER, panel2);
		
		
		slider = new JSlider(0,200,100);
		panel2.add(slider);
		this.slider.addChangeListener(new ChangeListener() {  // anonimowa klasa wewnetrzna

			@Override
			public void stateChanged(ChangeEvent arg0) {
				int value = slider.getValue();
				panel2.setBackground( new Color(value));
			}
			
		}); 
		
		panel3 = new JPanel();
		panel3.setBackground(Color.white);
		this.add(BorderLayout.SOUTH, panel3);
		
		String[] colors = {"bialy", "czarny", "niebieski"};
		JComboBox<String> colorList = new JComboBox<String>(colors);
		ComboListener sluchacz = new ComboListener(panel3, label4 = new JLabel("Wybrano kolor bialy")); // Listener kalsa zewnetrzna
		colorList.addItemListener(sluchacz);
		panel3.add(colorList);
		label4.setForeground(Color.red);
		panel3.add(label4);
	}
	
	class ButtonListener implements ActionListener {  /// Listener przez klase wewnetrzna
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj == button1)
				if (button1.isSelected()) label1.setText("Ala"); 
					else label1.setText("");
			if (obj == button2)
				if (button2.isSelected()) label2.setText("ma"); 
					else label2.setText("");
			if (obj == button3)
				if (button3.isSelected()) label3.setText("kota"); 
					else label3.setText("");
		}
		
	}


	
	public static void main(String[] args) {
		MultiListenerFrame frame = new MultiListenerFrame();
		frame.setVisible(true);
	}

}
