package lab3;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

public class InnerClassListenerFrame extends JFrame {

	static final int SLIDER_MIN = -100;
	static final int SLIDER_MAX = 100;
	static final int SLIDER_INIT = 0;

    JSlider slider;
    JLabel label;
    
    public InnerClassListenerFrame() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600,50);
		
		slider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
		this.add(slider, BorderLayout.PAGE_START);
		
		label = new JLabel(String.format("%d", slider.getValue()));
		this.add(label);
		
		slider.addChangeListener((javax.swing.event.ChangeListener) new SliderChangeListener());
    }
	public class SliderChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent arg0) {
			String value = String.format("%d", slider.getValue());
			label.setText(value);
			
		}
		

		
	}
	public static void main(String[] args) {
		InnerClassListenerFrame ramka = new InnerClassListenerFrame();
		ramka.setVisible(true);
	}

}
