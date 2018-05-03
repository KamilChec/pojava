package lab3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

public class RadioButtonsFrame extends JFrame implements ActionListener {
	
	static final String[] COLOR_NAMES = {"red", "green", "blue"};
	static final Color[] COLORS = {Color.red, Color.green, Color.blue};
	static final Color INIT_COLOR = COLORS[0];

	JRadioButton radioButton1;
	JRadioButton radioButton2;
	JRadioButton radioButton3;
	
	public RadioButtonsFrame() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(300,100);
		this.getContentPane().setBackground(INIT_COLOR); //ustawienie koloru kontenera przy starcie
		this.setLayout(new FlowLayout()); 
		
		radioButton1 = new JRadioButton(COLOR_NAMES[0]);
		radioButton1.setActionCommand("0");              // wybiera kolor z tablicy jaki ma dzialac po kliknieciu
		radioButton1.setBackground(INIT_COLOR);
		radioButton1.addActionListener(this);
		radioButton1.setSelected(true);                  // wybiera jako zaznaczone przy starcie
		this.add(radioButton1);
		
		radioButton2 = new JRadioButton(COLOR_NAMES[1]);
		radioButton2.setActionCommand("1");
		radioButton2.setBackground(INIT_COLOR);
		radioButton2.addActionListener(this);
		this.add(radioButton2);
		
		radioButton3 = new JRadioButton(COLOR_NAMES[2]);
		radioButton3.setActionCommand("2");
		radioButton3.setBackground(INIT_COLOR);
		radioButton3.addActionListener(this);
		this.add(radioButton3);
		
		ButtonGroup group = new ButtonGroup();        // klasa która gdy wyłacza 1 przycisk w momencie wcisneicia 2
		group.add(radioButton1);
		group.add(radioButton2);
		group.add(radioButton3);
	}
	public void actionPerformed(ActionEvent arg0) {
		Color newColor;
		int colorNumber = Integer.parseInt(arg0.getActionCommand()); //rzutuje obiekt arg0 na int
		this.getContentPane().setBackground(COLORS[colorNumber]);
		radioButton1.setBackground(COLORS[colorNumber]);
		radioButton2.setBackground(COLORS[colorNumber]);
		radioButton3.setBackground(COLORS[colorNumber]);
	}
	public static void main(String[] args) {
		RadioButtonsFrame frame = new RadioButtonsFrame();
		frame.setVisible(true);

	}

}
