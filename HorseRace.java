import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class HorseRace implements ActionListener{
	public static void main(String[] args){
		new HorseRace();
	}

	public HorseRace(){
		init();
	}

	public void init(){
		FRAME_MAIN = new JFrame("HORSE-RACING");
		PANEL_RACE = new JPanel();		
		BUTTON_START = new JButton("READY!");
		TEXT_SPEED = new JTextField(5);
		LABEL_HORSE1 = new JLabel(new ImageIcon("idle1.png"));
		LABEL_HORSE2 = new JLabel(new ImageIcon("idle2.png"));
		LABEL_HORSE3 = new JLabel(new ImageIcon("idle3.png"));
		LABEL_HORSE4 = new JLabel(new ImageIcon("idle4.png"));
		LABEL_HORSE5 = new JLabel(new ImageIcon("idle5.png"));
		LABEL_TRACK  = new JLabel(new ImageIcon("track.png"));		
		LABEL_POS = new JTextArea[5];
		SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
		double CENTER_X = SCREENSIZE.width/2;
		double CENTER_Y = SCREENSIZE.height/2;

		intX = new int[5];

		for(int ctr = 0; ctr < 5; ctr++){
			intX[ctr] = 10;
			LABEL_POS[ctr] = new JTextArea();
			LABEL_POS[ctr].setBorder(BorderFactory.createTitledBorder((ctr+1) + "-PLACE:"));
			LABEL_POS[ctr].setFont(new Font("Courier", 1, 20));
			LABEL_POS[ctr].setEditable(false);
			LABEL_POS[ctr].setBounds(x_pos, 410, 100, 50);
			FRAME_MAIN.add(LABEL_POS[ctr]);
			
			switch(ctr){
				case 0:
					LABEL_POS[ctr].setForeground(Color.BLUE); 
					break;
				case 1: 
					LABEL_POS[ctr].setForeground(Color.RED); 
					break;
				case 2: 
					LABEL_POS[ctr].setForeground(Color.GREEN); 
					break;
				case 3: 
					LABEL_POS[ctr].setForeground(Color.ORANGE); 
					break;
				case 4: 
					LABEL_POS[ctr].setForeground(Color.BLACK); 
					break;				
			}
			
			x_pos += 110;
		}

		BUTTON_START.setBounds(10, 410, 100, 50);
		BUTTON_START.addActionListener(this);
		FRAME_MAIN.add(BUTTON_START);

		TEXT_SPEED.setBounds(130, 410, 80, 50);
		TEXT_SPEED.setBorder(BorderFactory.createTitledBorder("Speed"));
		FRAME_MAIN.add(TEXT_SPEED);

		LABEL_HORSE1.setBounds(10, 10, 100, horseHeight);
		LABEL_HORSE2.setBounds(10, 70, 100, horseHeight);
		LABEL_HORSE3.setBounds(10, 140, 100, horseHeight);
		LABEL_HORSE4.setBounds(10, 220, 100, horseHeight);
		LABEL_HORSE5.setBounds(10, 300, 100, horseHeight);
		LABEL_TRACK.setBounds(0, 0, 800, 500);

		PANEL_RACE.add(LABEL_HORSE1);
		PANEL_RACE.add(LABEL_HORSE2);
		PANEL_RACE.add(LABEL_HORSE3);
		PANEL_RACE.add(LABEL_HORSE4);
		PANEL_RACE.add(LABEL_HORSE5);		
		PANEL_RACE.add(LABEL_TRACK);

		PANEL_RACE.setLayout(null);
		PANEL_RACE.setBounds(0, 0, SCREENSIZE.width, SCREENSIZE.height);
		PANEL_RACE.setBackground(new Color(34, 177, 76));
		//PANEL_RACE.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		FRAME_MAIN.add(PANEL_RACE);

		FRAME_MAIN.setLayout(null);
		FRAME_MAIN.setSize(SCREENSIZE.width, SCREENSIZE.height);
		FRAME_MAIN.setLocationRelativeTo(null);
		FRAME_MAIN.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRAME_MAIN.setBackground(new Color(34, 177, 76));
		FRAME_MAIN.setResizable(false);
		FRAME_MAIN.setUndecorated(true);
		FRAME_MAIN.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){

		if(e.getSource() == BUTTON_START){
			startButtonAction();
		}
		
	}

	public void startButtonAction(){

		LABEL_HORSE1.setBounds(10, 10, 100, horseHeight);
		LABEL_HORSE2.setBounds(10, 70, 100, horseHeight);
		LABEL_HORSE3.setBounds(10, 140, 100, horseHeight);
		LABEL_HORSE4.setBounds(10, 220, 100, horseHeight);
		LABEL_HORSE5.setBounds(10, 300, 100, horseHeight);
		
		for(int ctr = 0; ctr < 5; ctr++){
			LABEL_POS[ctr].setText(""); 
		}		

		finished = 0;

		if(start == 1){
			try{
				animSpeed = Integer.parseInt(TEXT_SPEED.getText());
			} catch(NumberFormatException a){
			 	animSpeed = 45;
			}

			TEXT_SPEED.setEnabled(false);

			TIMER_MOVEMENT1 = new Timer(animSpeed, new HorseAnimation(0));
			TIMER_MOVEMENT2 = new Timer(animSpeed, new HorseAnimation(1));
			TIMER_MOVEMENT3 = new Timer(animSpeed, new HorseAnimation(2));
			TIMER_MOVEMENT4 = new Timer(animSpeed, new HorseAnimation(3));
			TIMER_MOVEMENT5 = new Timer(animSpeed, new HorseAnimation(4));

			LABEL_HORSE1.setIcon(new ImageIcon ("horse1.gif"));
			LABEL_HORSE2.setIcon(new ImageIcon ("horse2.gif"));
			LABEL_HORSE3.setIcon(new ImageIcon ("horse3.gif"));
			LABEL_HORSE4.setIcon(new ImageIcon ("horse4.gif"));
			LABEL_HORSE5.setIcon(new ImageIcon ("horse5.gif"));

			TIMER_MOVEMENT1.start();
			TIMER_MOVEMENT2.start();
			TIMER_MOVEMENT3.start();
			TIMER_MOVEMENT4.start();
			TIMER_MOVEMENT5.start();
		}

		start = 1;
		BUTTON_START.setText("GO!");

	}

	class HorseAnimation implements ActionListener{
		int horse;
		int speed[] = new int[5];
		int temp[] = new int[5];
		boolean running = true;
		boolean end;

		public HorseAnimation(int horse){
			this.horse = horse;
			for(int ctr = 0; ctr < 5; ctr++){
				speed[ctr] = 1;
			}			
		}

		public void actionPerformed(ActionEvent e){
			end = true;

			Random r = new Random();

			switch(horse){
				case 0: 
					if(running) LABEL_HORSE1.setBounds(intX[horse]+=speed[horse], 10, 100, horseHeight);
					if(intX[horse] >= 670){
						intX[horse] = 10;
						ranks[finished] = horse;
						LABEL_HORSE1.setBounds(670, 10, 100, horseHeight);
						LABEL_HORSE1.setIcon(new ImageIcon ("idle1.png"));
						LABEL_POS[finished].setForeground(Color.BLUE);
						LABEL_POS[finished].setText("HORSE-1");
				 		try{
							Thread.sleep(finishDelay);
						} catch(Exception timerExcptn){
							timerExcptn.printStackTrace();
						}
				 		TIMER_MOVEMENT1.stop();
						finished++;
				 	}
					break;

				case 1: 
					if(running) LABEL_HORSE2.setBounds(intX[horse]+=speed[horse], 70, 100, horseHeight);
					if(intX[horse] >= 670){
						intX[horse] = 10;
						ranks[finished] = horse;
						LABEL_HORSE2.setBounds(670, 70, 100, horseHeight);
						LABEL_HORSE2.setIcon(new ImageIcon ("idle2.png"));
						LABEL_POS[finished].setForeground(Color.RED);
						LABEL_POS[finished].setText("HORSE-2");
				 		try{
							Thread.sleep(finishDelay);
						} catch(Exception timerExcptn){
							timerExcptn.printStackTrace();
						}
				 		TIMER_MOVEMENT2.stop();
						finished++;
				 	}
					break;

				case 2: 
					if(running) LABEL_HORSE3.setBounds(intX[horse]+=speed[horse], 140, 100, horseHeight);
					if(intX[horse] >= 670){
						intX[horse] = 10;
						ranks[finished] = horse;
						LABEL_HORSE3.setBounds(670, 140, 100, horseHeight);
						LABEL_HORSE3.setIcon(new ImageIcon ("idle3.png"));
						LABEL_POS[finished].setForeground(Color.GREEN);
						LABEL_POS[finished].setText("HORSE-3");
				 		try{
							Thread.sleep(finishDelay);
						} catch(Exception timerExcptn){
							timerExcptn.printStackTrace();
						}
				 		TIMER_MOVEMENT3.stop();
						finished++;
				 	}
					break;

				case 3: 
					if(running) LABEL_HORSE4.setBounds(intX[horse]+=speed[horse], 220, 100, horseHeight);
					if(intX[horse] >= 670){
						intX[horse] = 10;
						ranks[finished] = horse;
						LABEL_HORSE4.setBounds(670, 220, 100, horseHeight);
						LABEL_HORSE4.setIcon(new ImageIcon ("idle4.png"));
						LABEL_POS[finished].setForeground(Color.ORANGE);
						LABEL_POS[finished].setText("HORSE-4");
				 		try{
							Thread.sleep(finishDelay);
						} catch(Exception timerExcptn){
							timerExcptn.printStackTrace();
						}
				 		TIMER_MOVEMENT4.stop();
						finished++;
				 	}
					break;

				case 4: 
					if(running) LABEL_HORSE5.setBounds(intX[horse]+=speed[horse], 300, 100, horseHeight);
					if(intX[horse] >= 670){
						intX[horse] = 10;
						ranks[finished] = horse;
						LABEL_HORSE5.setBounds(670, 300, 100, horseHeight);
						LABEL_HORSE5.setIcon(new ImageIcon ("idle5.png"));
						LABEL_POS[finished].setForeground(Color.BLACK);
						LABEL_POS[finished].setText("HORSE-5");
				 		try{
							Thread.sleep(finishDelay);
						} catch(Exception timerExcptn){
							timerExcptn.printStackTrace();
						}
				 		TIMER_MOVEMENT5.stop();
					  	finished++;
				 	}
					break;

				default: 
					break;
			}

			for(int ctr = 0; ctr < 5; ctr++){
				temp[ctr] = r.nextInt(50);
				if(temp[ctr] >= 0 && temp[ctr] <= 10){ speed[ctr] = (temp[ctr] % 2 )*2 + 2;}
				else if(temp[ctr] > 11 && temp[ctr] <= 20){ speed[ctr] = (temp[ctr] % 3)*3 + 2;}
				else if(temp[ctr] > 21 && temp[ctr] <= 30){ speed[ctr] = (temp[ctr] % 4)*4 + 2;}
				else if(temp[ctr] > 31 && temp[ctr] <= 40){ speed[ctr] = (temp[ctr] % 5)*5 + 2;}
				else if(temp[ctr] > 41 && temp[ctr] <= 50){ speed[ctr] = (temp[ctr] % 10)*1 + 2;}
				else {speed[ctr] = 1;}		
			}

			if(finished == 5){
				finished = 0;
				String[] colors = new String[5];
				for(int ctr = 0; ctr < 5; ctr++){
					intX[ctr] = 10;
					switch(ctr){
						case 0: colors[0] = "BLUE PONYTA";break;
						case 1: colors[1] = "RED PONYTA";break;
						case 2: colors[2] = "GREEN PONYTA";break;
						case 3: colors[3] = "YELLOW PONYTA";break;
						case 4: colors[4] = "BROWN PONYTA";break;
					}
				}

				String first = "1st place - "+(colors[ranks[0]])+"\n";
				String second = "2nd place - "+(colors[ranks[1]])+"\n";
				String third = "3rd place - "+(colors[ranks[2]])+"\n";
				String fourth = "4th place - "+(colors[ranks[3]])+"\n";
				String fifth = "5th place - "+(colors[ranks[4]])+"\n";
				String s =  colors[ranks[0]] + " wins!\n" + first + second + third + fourth + fifth;
				//JOptionPane.showMessageDialog(null, s);
				start = 0;

				TEXT_SPEED.setEnabled(true);
				BUTTON_START.setEnabled(true);
				BUTTON_START.setText("READY!");

				// UNCOMMENT FOR AUTO-START
				// try{
				// 	Thread.sleep(3000);
				// } catch(Exception timerExcptn){
				// 	timerExcptn.printStackTrace();
				// }

				//start = 1;
				//startButtonAction();
				
			} else {
				BUTTON_START.setEnabled(false);
			}
		}
	}

	private JFrame FRAME_MAIN;

	private JPanel PANEL_BET;
	private JPanel PANEL_RACE;

	private JLabel LABEL_HORSE1;
	private JLabel LABEL_HORSE2;
	private JLabel LABEL_HORSE3;
	private JLabel LABEL_HORSE4;
	private JLabel LABEL_HORSE5;
	private JLabel LABEL_TRACK;
	
	private JTextArea[] LABEL_POS;

	//private JRadioButton[] RADIO_HORSE;

	private JTextField TEXT_SPEED;
	//private JTextField TEXT_BET;

	private JButton BUTTON_START;

	private Timer  TIMER_MOVEMENT1;
	private Timer  TIMER_MOVEMENT2;
	private Timer  TIMER_MOVEMENT3;
	private Timer  TIMER_MOVEMENT4;
	private Timer  TIMER_MOVEMENT5;

	private Dimension SCREENSIZE;

	private int intX[];
	private int x_pos = 220;
	private int ranks[] = new int[6];
	private int finished = 0;
	private int start = 0;
	private int horseHeight = 70;
	private int animSpeed;
	private int finishDelay = 1000;
}