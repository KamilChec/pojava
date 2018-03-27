package lab4;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PolygonDrawer extends JPanel {
	List<JTextField[]> fields;
	JRadioButton regular;
	Color bgColor, lineColor;
	int lineWidth;
	
	PolygonDrawer(List<JTextField[]> fields, JRadioButton regular, Color bgColor,int lineWidth, Color lineColor ) {
		this.fields = fields;
		this.regular = regular;
		this.bgColor = bgColor;
		this.lineWidth = lineWidth;
		this.lineColor = lineColor;
		
		this.setBackground(bgColor);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Graphic"));
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
	    int posX[] = new int[fields.size()];
	    int posY[] = new int[fields.size()];
	    int xIndex = 0;
	    int yIndex = 0;
	    int loopIndex = 0;
	    Random generator = new Random();
	    for(JTextField[] pairs : fields) {
	    		for(JTextField field : pairs) {
	    			if((loopIndex & 1) == 0) {
	    				if(regular.isSelected()) {
	    					posX[xIndex] = (int) (100*Math.cos((Math.PI/2+2*Math.PI*(xIndex+1))/fields.size())+200);
	    				}
	    				else {
	    					posX[xIndex] = (generator.nextInt()%150)+200;
	    				}
	    				field.setText(Integer.toString(posX[xIndex]));
	    				if(xIndex < fields.size()) { xIndex++;}
	    			}
	    			else {
	    				if(regular.isSelected()) {
	    					posY[yIndex] = (int) (100*Math.sin((Math.PI/2+2*Math.PI*(yIndex+1))/fields.size())+200);
	    				}
	    				else {
	    					posY[yIndex] = (generator.nextInt()%150)+200;
	    				}
    					field.setText(Integer.toString(posY[yIndex]));
    					if(yIndex < fields.size()) {yIndex++;}
	    			}
	    			loopIndex++;
	    		}
	    } 
	    	 //System.out.println("halo");

	    Graphics2D g2d = (Graphics2D) g;
	    g.setColor(lineColor);
        BasicStroke bs1 = new BasicStroke(lineWidth);
        g2d.setStroke(bs1);
	    g2d.draw(new Polygon(posX, posY, fields.size())); 
	}


}
