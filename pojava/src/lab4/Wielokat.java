package lab4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Polygon;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Wielokat extends JFrame {

	JPanel up, down, left, right, center;
	JButton CButton1, CButton2, drawButton;
	JRadioButton regular, random;
	JMenuBar menuBar;
	JSlider slider;
	int vertices = 3;
	JLabel test;
	List<JTextField[]> fields;
	List<Integer> xPoly, yPoly;
	Polygon poly;
	
	
	public Wielokat() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600,500);
		this.add(up = new JPanel(), BorderLayout.PAGE_START);
		this.add(down = new JPanel(), BorderLayout.PAGE_END);
		this.add(left = new JPanel(), BorderLayout.WEST);
		this.add(right = new JPanel(), BorderLayout.EAST);
		//this.add(center = new JPanel(), BorderLayout.CENTER);
		this.setJMenuBar(menuBar = new JMenuBar());
		
		JMenu menu = new JMenu("Line width");							//menu
		menuBar.add(menu);
		JMenuItem menuItem1 = new JMenuItem("1 pxl");
		JMenuItem menuItem2 = new JMenuItem("5 pxl");
		JMenuItem menuItem3 = new JMenuItem("10 pxl");
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
		
		up.setLayout(new FlowLayout()); 									//gorny panel
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
		
		right.setLayout(new GridLayout(0, 2));							//prawy panel
		fields = new ArrayList<>(3);
        for (int i = 0; i < vertices; i++) {
            JTextField[] pairs = new JTextField[2];
            pairs[0] = new JTextField(3);
            int x = (int) (20*Math.cos((Math.PI/2+2*Math.PI*(i+1))/vertices));
            pairs[0].setText(Integer.toString(x));
            pairs[1] = new JTextField(3);
            int y = (int) (20*Math.sin((Math.PI/2+2*Math.PI*(i+1))/vertices));
            pairs[1].setText(Integer.toString(y));

            for (JTextField field : pairs) {
                right.add(field);
            }

            fields.add(pairs);
        }
		//JScrollPane scroll = new JScrollPane(right);
		//scroll.setPreferredSize(null);
		//right.add(scroll);
		//scroll.setPreferredSize( new Dimension(300,100));
		//this.add(new JScrollPane(right));
		//right.add(new JScrollPane());
		     						
        //int xPoly[] = {150, 250, 325, 375, 450, 275, 200};				
       // int yPoly[] = {150, 100, 125, 225, 250, 375, 300};
        xPoly = new ArrayList<Integer>();								//srodkowy panel
        yPoly = new ArrayList<Integer>();
        poly = new Polygon(xPoly, yPoly, vertices);				
        center = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.drawPolygon(poly);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(800, 600);
            }
        };
        this.add(center, BorderLayout.CENTER);
       
		
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
	                int x = (int) (20*Math.cos((Math.PI/2+2*Math.PI*(count+1))/vertices));
	                xPoly.add(x);
	                pairs[0].setText(Integer.toString(x));
	                pairs[1] = new JTextField(3);
	                int y = (int) (20*Math.sin((Math.PI/2+2*Math.PI*(count+1))/vertices));
	                yPoly.add(y);
	                pairs[1].setText(Integer.toString(y));

	                for (JTextField field : pairs) {
	                    right.add(field);
	                }

	                fields.add(pairs);
	            }
	            right.revalidate();
	        }
		}
	}
	
	public static void main(String[] args) {
		Wielokat wielokat = new Wielokat();
		wielokat.setVisible(true);

	}

}
