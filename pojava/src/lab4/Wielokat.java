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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Wielokat extends JFrame implements ActionListener{

	JPanel up, down, left, right, center;
	JButton CButton1, CButton2, drawButton;
	JRadioButton regular, random;
	JMenuBar menuBar;
	JSlider slider;
	int vertices = 3;
	int lineWidth;
	JLabel test;
	Color bgColor, lineColor;
	List<JTextField[]> fields ;


	
	public Wielokat() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600,500);
		this.add(up = new JPanel(), BorderLayout.PAGE_START);
		this.add(down = new JPanel(), BorderLayout.PAGE_END);
		this.add(left = new JPanel(), BorderLayout.WEST);
		this.add(right = new JPanel(), BorderLayout.EAST);
		this.setJMenuBar(menuBar = new JMenuBar());
		
		JMenu menu = new JMenu("Line width");							           //menu
		menuBar.add(menu);
		JMenuItem menuItem1 = new JMenuItem("1 pxl");
		menuItem1.setActionCommand("1");
		menuItem1.addActionListener(this);
		JMenuItem menuItem2 = new JMenuItem("5 pxl");
		menuItem2.setActionCommand("5");
		menuItem2.addActionListener(this);
		JMenuItem menuItem3 = new JMenuItem("10 pxl");
		menuItem3.setActionCommand("10");
		menuItem3.addActionListener(this);
		JMenuItem exit = new JMenuItem("Zakończ");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			System.exit(0); }
		});
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
		menu.add(exit);
		
		up.setLayout(new FlowLayout()); 									               //gorny panel
		JLabel upLabel = new JLabel("No. of verticles");
		up.add(upLabel);
		slider = new JSlider(3,33,3);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setValue(vertices);
		up.add(slider);
		slider.addChangeListener(new SpinerListener());
		test = new JLabel();
		up.add(test);
		drawButton = new JButton("Draw");
		up.add(drawButton);
		drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            		Wielokat.super.remove(center);
        			center = new PolygonDrawer(fields, regular, bgColor, lineWidth, lineColor);			   
        			Wielokat.this.add(BorderLayout.CENTER, center);
        			Wielokat.this.setVisible(true);
            }
        }); 

		down.setLayout(new FlowLayout());                                                //dolny panel
		down.add(CButton1 = new JButton("BG color"));
		CButton1.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				bgColor = JColorChooser.showDialog(null,"Choose a color", bgColor);
				center.setBackground(bgColor);
            }
		});
		down.add(CButton2 = new JButton("LN color"));
		CButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lineColor = JColorChooser.showDialog(null, "Line color",lineColor);

            }
        });
		
		left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));                      //lewy panel
		Border blackline = BorderFactory.createLineBorder(Color.black);
		left.setBorder(blackline);
		TitledBorder title = BorderFactory.createTitledBorder(blackline, "Polygon");
		left.setBorder(title);
		left.add(Box.createRigidArea(new Dimension(0,100)));
		left.add(regular = new JRadioButton("regular"));
		left.add(Box.createRigidArea(new Dimension(0,100)));
		left.add(random = new JRadioButton("random"));
        ButtonGroup group = new ButtonGroup();
        regular.setSelected(true);
        group.add(regular);
        group.add(random);
		
		right.setLayout(new GridLayout(0, 2));							             //prawy panel
		fields = new ArrayList<JTextField[]>(3);
		TitledBorder rightTitle = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Positions");
		right.setBorder(rightTitle);
		right.add(new JLabel("    X"));
		right.add(new JLabel("    Y"));

		center = new PolygonDrawer(fields, regular, bgColor, lineWidth, lineColor);	    //panel centralny
		this.add(BorderLayout.CENTER, center);
		System.out.println(fields.size());
        for (int i = 0; i < vertices; i++) {
            JTextField[] pairs = new JTextField[2];
            pairs[0] = new JTextField(3);
            pairs[1] = new JTextField(3);
            for (JTextField field : pairs) {
                right.add(field);
            }

            fields.add(pairs);
        } 
	}
	
    public void actionPerformed(ActionEvent e) {
    		lineWidth = Integer.parseInt(e.getActionCommand());
    }
	
	

	
	class SpinerListener implements ChangeListener {
		public void stateChanged(ChangeEvent arg0) {
	        vertices = slider.getValue();
	        if (vertices < fields.size()) {
	            List<JTextField[]> oldFields = fields.subList(vertices , fields.size());
	            for (JTextField[] pairs : oldFields) {
	                for (JTextField field : pairs) {
	                    right.remove(field);
	                }
	            }
	            fields.removeAll(oldFields);
	            right.revalidate();
	        } else if (vertices > fields.size()) {
	            int count = (vertices - fields.size());
	            for (int i = 0; i < count; i++) {
	                JTextField[] pairs = new JTextField[2];
	                pairs[0] = new JTextField(3);
	                pairs[1] = new JTextField(3);
	                for (JTextField field : pairs) {
	                    right.add(field);
	                }

	                fields.add(pairs);	           
	            }
	            right.revalidate();
	        }
    		center = new PolygonDrawer(fields, regular, bgColor, lineWidth, lineColor);
    		Wielokat.this.add(BorderLayout.CENTER, center);
		}
	}  
	


	
	public static void main(String[] args) {
		Wielokat wielokat = new Wielokat();
	    wielokat.setVisible(true);
	}
}