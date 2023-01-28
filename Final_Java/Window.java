package greater;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener{
	// setting up text boxes
	JPanel jp = new JPanel();
	JLabel label1 = new JLabel("Display");
	JTextArea text1 = new  JTextArea("");
	JLabel label1_1 = new JLabel("Max: ");
	JTextField text1_1 = new  JTextField("",5);
	JLabel label1_2 = new JLabel("Min: ");
	JTextField text1_2 = new  JTextField("",5);
	JLabel label1_3 = new JLabel("Avg: ");
	JTextField text1_3 = new  JTextField("",5);
	
	JLabel label_separator = new JLabel("");
	JLabel label2 = new JLabel("Please select data source:        ");
	JButton btn = new JButton("Humidity");
	JButton btn2 = new JButton("Temperature");
	
	public Window(String s) {
		//setting the attributes of the window
		super(s);
		setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(350, 200)); // overall window size
        
        jp.add(label1);
        jp.add(text1);
        jp.add(label1_1);
        jp.add(text1_1);
        jp.add(label1_2);
        jp.add(text1_2);
        jp.add(label1_3);
        jp.add(text1_3);
        
        text1.setPreferredSize(new Dimension(300,50));
        text1.setLineWrap(true);
        text1.setWrapStyleWord(true);
        btn.addActionListener(this);
        btn2.addActionListener(this);
        btn.setPreferredSize(new Dimension(159, 20));
        btn2.setPreferredSize(new Dimension(150, 20));
        label_separator.setPreferredSize(new Dimension(300,2));
        jp.add(label_separator);
        jp.add(label2);
        jp.add(btn);
        jp.add(btn2);
        
        add(jp);
        pack();
	}
	// what happens when the Humidity button is clicked 
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btn)) {
			btn2.setEnabled(false);
			System.out.print("Humidity Values Called!" );
			try {
				getData(2);
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		// what happens when the temperature button is clicked 
		if(e.getSource().equals(btn2)) {
			btn.setEnabled(false);
			System.out.print("Temperature Values Called!" );
			try {
				getData(1);
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void getData(int value) throws Throwable {
		// try output the data catch any errors.
		try {
			String recivedString = ClientArray.start(value);
			DataEngine d = new DataEngine(recivedString);
			text1.setText(d.Array.toString());
			text1_1.setText(""+d.max);
			text1_2.setText(""+d.min);
			text1_3.setText(""+d.avg);
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}

}
