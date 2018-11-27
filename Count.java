import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.*;//plays a sound after timer ends
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

//serves as a ActionListioner
public class Count implements ActionListener, Runnable {

	private static long totalTime = 0;
	//which button is clicked:
	public static boolean resetButton=false;
	public static boolean pauseButton=false;
	public static boolean startButton=false;
	
	//the sound to play when finished
	

	//Constructor
	MeowTimer timer;
	public Count(MeowTimer InputTimer) {
		this.timer = InputTimer;
	}


	//get time input from GUI
	private long getTotalTime(){

		long result=0;
		result+=Integer.parseInt(timer.hourInput.getText())*3600; 
		result+=Integer.parseInt(timer.minuteInput.getText())*60;
		result+=Integer.parseInt(timer.secondInput.getText());
		return result;
	}

	//convert the seconds got from getTotalTime() to 00:00:00
	private static String format(long totalTime) {

		String result = "";
		long hours = totalTime/3600;
		long minutes = (totalTime/60)%60;
		long seconds = totalTime%60;

		if(hours<10) result+="0"+hours;
		else result+=""+hours;
		if(minutes<10) result+=":0"+minutes;
		else result+=":"+minutes;
		if(seconds<10) result+=":0"+seconds;
		else result+=":"+seconds;

		return result; 
	}

	//run the timer
	public void run(){ // <--- Thread.start() 

		long pastTime = totalTime;

		outer:
			for(;totalTime>=0; totalTime--){
				try {
					if(resetButton){ // if reset button clicked
						timer.timeDisplay.setText("00:00:00");
						return;
					}
					if(pauseButton){ // if pause button clicked
						startButton = true;
						return;
					}
					String time = format(totalTime);
					timer.timeDisplay.setText(time);

					Thread.sleep(1000);
					if(totalTime==0 && pastTime>0){ // if the timer finished

						timer.timeDisplay.setText("00:00:00");
						startButton = false;
						timer.start.setEnabled(true);
						timer.pause.setEnabled(false);
						timer.reset.setEnabled(false);
						//show up a message
						//JOptionPane.showMessageDialog(null, "Done!", "Timer", JOptionPane.PLAIN_MESSAGE);
						try {
							 AudioInputStream input = AudioSystem.getAudioInputStream(new File("Meow.wav").getAbsoluteFile()); 
							 Clip clip = AudioSystem.getClip();
							 clip.open(input);
							 clip.start();
							 Thread.sleep(2000);
							 clip.stop();
						}
						catch (Exception e) {
							JOptionPane.showMessageDialog(null,"Music Error","Error",JOptionPane.PLAIN_MESSAGE);
						}
						return;
					}					

				}catch(InterruptedException e){
					JOptionPane.showMessageDialog(null, "Something gone wrong!\nError:\n" + e.toString(), "Timer: error", JOptionPane.PLAIN_MESSAGE);
				}
			}
	}


	//ActionListener
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton clickedButton = (JButton)event.getSource();

		if(clickedButton == timer.start){ // if start button clicked

			if(!startButton){
				totalTime = getTotalTime();
			}
			timer.start.setEnabled(false);
			timer.pause.setEnabled(true);
			timer.reset.setEnabled(true);
			startButton = true;
			pauseButton = false;
			new Thread(this).start();
		}

		else if(clickedButton==timer.reset){ // if reset button clicked

			timer.timeDisplay.setText("00:00:00");
			resetButton=true;
			startButton=false;
			timer.reset.setEnabled(false);
			timer.pause.setEnabled(false);
			timer.start.setEnabled(true);
			try{
				Thread.sleep(1000); 
				resetButton = false;
				}
			catch(InterruptedException e1){
				//don't really care here.
			}

		}else if(clickedButton==timer.pause){ // if pause button clicked

			pauseButton=true;
			startButton=true;
			timer.start.setEnabled(true);
			timer.pause.setEnabled(false);
		}
	}

}




