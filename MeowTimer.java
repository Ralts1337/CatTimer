import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Use Swing: awt.Frame makes a window
public class MeowTimer extends JFrame {
	
	//what do I need?
	//3 text fields: hour, minute, second, for user input
	//3 labels: hour, minute, second
	//3 buttons on bottom : Start,Pause, Reset
	//play sound at the end of timer
	
	//3 text fields:
	JTextField hourInput;
	JTextField minuteInput;
	JTextField secondInput;

	
	//4 labels:
	JLabel hour;
	JLabel minute;
	JLabel second;
	JLabel timeDisplay;//to display time remaining
	
	//3 buttons:
	JButton start;
	JButton pause;
	JButton reset;
	//sound:
	
	//Constructor:
	public MeowTimer(){
		
		//Retrieve the content-pane of the top level container
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(3,1));
		
		//Initialize the labels:
		hour= new JLabel("Hour");
		minute=new JLabel("Minute");
		second=new JLabel("Second");
		timeDisplay= new JLabel("00:00:00",SwingConstants.CENTER);

		//Initialize the Text Fields and Strings
		hourInput=new JTextField("0");
		minuteInput=new JTextField("0");
		secondInput=new JTextField("0");
		
		
		//create a panel for inputs
		JPanel topPanel = new JPanel(new GridLayout(2,3));
		topPanel.add(hour);
		topPanel.add(minute);
		topPanel.add(second);
		topPanel.add(hourInput);
		topPanel.add(minuteInput);
		topPanel.add(secondInput);
		
		//initialize the two buttons
		start = new JButton("Start");
		pause = new JButton("Pause");
		reset = new JButton("Reset");
		//add ActionListener to Buttons
		
		//create a panel to display time
		JPanel midPanel = new JPanel();
		midPanel.setLayout(new GridLayout(1,1));
		midPanel.add(timeDisplay);
		
		//create a second panel for two buttons
		JPanel bottomPanel = new JPanel(new GridLayout(1,2));
		bottomPanel.add(start);
		bottomPanel.add(pause);
		bottomPanel.add(reset);
		
		//add panels to layout
		frame.add(topPanel);
		frame.add(midPanel);
		frame.add(bottomPanel);
		
		//title, size etc.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MeowTimer");
		frame.setSize(400,300);
		//frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);//can't resize
		frame.setAlwaysOnTop(true);//display the timer on top of all appss
		
		//Event Listener
		Count listener = new Count(this);
		start.addActionListener(listener);
		pause.addActionListener(listener);
		reset.addActionListener(listener);
		
		
		//change Font color and size
		Font font1 = new Font("Times New Roman", Font.BOLD,24);
		Font font2 = new Font("Times New Roman",Font.ITALIC,48);
		hour.setFont(font1);
		minute.setFont(font1);
		second.setFont(font1);
		timeDisplay.setFont(font2);
		hourInput.setFont(font2);
		minuteInput.setFont(font2);
		secondInput.setFont(font2);
		start.setFont(font1);
		pause.setFont(font1);
		reset.setFont(font1);
		
		
		if(listener.startButton==false) {
			pause.setEnabled(false);
			reset.setEnabled(false);
		}
		
	}
	
	//Methods
	
	//test it
	public static void main(String[] args) {
		MeowTimer timer = new MeowTimer();
		
	}
	



}
