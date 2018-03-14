package lab4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;

public class Wielokat extends JFrame {

	JPanel up, down, left, right;
	JButton CButton1, CButton2, drawButton;
	JRadioButton regular, random;
	JMenuBar menuBar;
	JSlider slider;
	
	
	public Wielokat() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600,500);
		this.add(up = new JPanel(), BorderLayout.PAGE_START);
		this.add(down = new JPanel(), BorderLayout.PAGE_END);
		this.add(left = new JPanel(), BorderLayout.WEST);
		this.add(right = new JPanel(), BorderLayout.EAST);
		this.setJMenuBar(menuBar = new JMenuBar());
		
		JMenu menu = new JMenu("Line width");							//menu
		menuBar.add(menu);
		JMenu menuItem1 = new JMenu("1 pxl");
		JMenu menuItem2 = new JMenu("5 pxl");
		JMenu menuItem3 = new JMenu("10 pxl");
		JMenu menuItem4 = new JMenu("Zakończ");
		menuItem4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			System.exit(0); }
		});
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
		menu.add(menuItem4);
		
		up.setLayout(new FlowLayout()); 									//gorny panel
		JLabel upLabel = new JLabel("No. of verticles");
		up.add(upLabel);
		slider = new JSlider(0,35,0);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		up.add(slider);
		drawButton = new JButton("Draw");
		up.add(drawButton);
		
		
		down.setLayout(new FlowLayout());                               //dolny panel
		down.add(CButton1 = new JButton("BG color"));
		down.add(CButton2 = new JButton("LN color"));
		
		left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));       //lewy panel
		Border blackline = BorderFactory.createLineBorder(Color.black);
		left.setBorder(blackline);
		TitledBorder title = BorderFactory.createTitledBorder(blackline, "Polygon");
		left.setBorder(title);
		left.add(Box.createRigidArea(new Dimension(0,100)));
		left.add(regular = new JRadioButton("regular"));
		left.add(Box.createRigidArea(new Dimension(0,100)));
		left.add(random = new JRadioButton("random"));
		
		right.setLayout(new GridLayout(4,2));                           //prawy panel
	}
	public static void main(String[] args) {
		Wielokat wielokat = new Wielokat();
		wielokat.setVisible(true);

	}

}
