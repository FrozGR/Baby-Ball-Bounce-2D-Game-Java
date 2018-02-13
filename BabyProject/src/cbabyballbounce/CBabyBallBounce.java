/**
 * Assignment 2 : Programming (Java)
 * Program : Application - Baby Ball Bounce
 * Filename : CBabyBallBounce.java
 * @author : Kitsantas Fotios
 * @version : 2.0 Incorporates Artificial Intelligence!
 * Date : 18/05/2017
 */

package cbabyballbounce;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class CBabyBallBounce extends JFrame implements ActionListener, ChangeListener, KeyListener{
	
	//Setting timer variables.
	int nHours = 0, nMinutes = 0, nSeconds = 0, nCounter = 0, nRightScore = 0, nLeftScore = 0;

	//Declaring GameCanvas for displaing graphics.
	GameCanvas canvas;

	//Declaring MenuBar.
	JMenuBar menuBar;
	JMenu menuScenario, menuEdit, menuControls, menuHelp;
	JMenuItem menuSave, menuExit, menuOpen, menuCut, menuCopy, menuPaste, menuAbout, menuHelpTopic;
	
	//Declaring Slider for ball speed control.
	JSlider speedSlider;

	//Declaring Buttons.
	JButton jBPlayer2, jBPlayer4, jBMultiPlayer, jBExit, jBAct, jBRun, jBReset, jBForward,
			jBBackward, jBUp, jBDown, jBBall, jBEmpty1, jBEmpty2, jBEmpty3, jBEmpty4, jBDirection;
	
	//Declaring Labels, TextFields and Panels.
	JPanel rightPanel, bottomPanel, centerPanel, innerCenterPanel;
	JLabel jLTimer, jLScore, jLOption, jLSquare, jLDirection, jLPlayer, jLSpeed;
	JTextField jTHour, jTMins, jTSeconds, jTLeftScore, jTRightScore, jTOption, jTSquare, jTDirection;
	
	//Declaring border for Right Panel.
	Border loweredetched;

	// Time Controls for Running the ball and timer.
	Timer timerEplased, timerBallMove, timerBabyMover;
	
	//some code needs to run only once, I use runOnceOnly variable for this
	boolean bRunOnceOnly = true, b2BabyMode = false, b4BabyMode = false;
	
	//Setting baby auto move directions 0 means up 1 means down
	int nBabyDirection2 = 1, nBabyDirection4 = 1;

	int directionX;
	int directionY;

	CBabyBallBounce(){
		//Setting Form Configuration
		super("CBabyBallBounce | Baby Ball Bounce Application");
		setLayout(null);
		setSize(825, 585);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		//Set Frame Icon
		setIconImage( Toolkit.getDefaultToolkit().getImage("images\\greenfoot.png") );

		// Right Panel
		rightPanel = new JPanel();
		rightPanel.setSize(200, 485);
		rightPanel.setLocation(617, 0);
		rightPanel.setLayout(null);
		rightPanel.setBorder(loweredetched);
		add(rightPanel);
		//rightPanel.setBackground(Color.WHITE);


		//Setting bottom panel's border as Etched Border
		rightPanel.setBorder( BorderFactory.createEtchedBorder() );


		// Add components to right panel
		jLTimer = new JLabel("DIGITAL TIMER");
		jLTimer.setSize(100, 30);
		jLTimer.setLocation(65, 1);
		rightPanel.add(jLTimer);

		// Add the hour text box
		jTHour = new JTextField("00");
		jTHour.setLocation(20, 30);
		jTHour.setSize(40, 22);
		jTHour.setBackground(Color.BLACK);
		jTHour.setForeground(Color.WHITE);
		jTHour.setBorder( BorderFactory.createEtchedBorder(Color.GRAY, Color.LIGHT_GRAY) );
		rightPanel.add(jTHour);

		// Add the minutes text box
		jTMins = new JTextField("00");
		jTMins.setLocation(80, 30);
		jTMins.setSize(40, 22);
		jTMins.setBackground(Color.BLACK);
		jTMins.setForeground(Color.WHITE);
		jTMins.setBorder( BorderFactory.createEtchedBorder(Color.GRAY, Color.LIGHT_GRAY) );
		rightPanel.add(jTMins);

		// Add the seconds text box
		jTSeconds = new JTextField("00");
		jTSeconds.setLocation(140, 30);
		jTSeconds.setSize(40, 22);
		jTSeconds.setBackground(Color.BLACK);
		jTSeconds.setForeground(Color.WHITE);
		jTSeconds.setBorder( BorderFactory.createEtchedBorder(Color.GRAY, Color.LIGHT_GRAY) );
		rightPanel.add(jTSeconds);


		// Add the score
		jLScore = new JLabel("SCORE");
		jLScore.setLocation(80, 50);
		jLScore.setSize(100, 30);
		rightPanel.add(jLScore);

		// Add the left score panel
		jTLeftScore = new JTextField();
		jTLeftScore.setLocation(20, 75);
		jTLeftScore.setSize(40, 22);
		jTLeftScore.setBackground(Color.BLACK);
		jTLeftScore.setForeground(Color.WHITE);
		jTLeftScore.setBorder( BorderFactory.createEtchedBorder(Color.GRAY, Color.LIGHT_GRAY) );
		jTLeftScore.setText("00");
		rightPanel.add(jTLeftScore);

		//Add L : R label
		jLPlayer = new JLabel(" <  L : R  > ");
		jLPlayer.setLocation(70, 70);
		jLPlayer.setSize(60, 30);
		rightPanel.add(jLPlayer);

		// Add the right score panel
		jTRightScore = new JTextField();
		jTRightScore.setLocation(140, 75);
		jTRightScore.setSize(40, 22);
		jTRightScore.setBackground(Color.BLACK);
		jTRightScore.setForeground(Color.WHITE);
		jTRightScore.setBorder( BorderFactory.createEtchedBorder(Color.GRAY, Color.LIGHT_GRAY) );
		jTRightScore.setText("00");
		rightPanel.add(jTRightScore);

		// text fields for option square direction.
		jLOption = new JLabel("Option:");
		jLOption.setSize(50, 20);
		jLOption.setLocation(20, 100);
		rightPanel.add(jLOption);

		// textfield for options
		jTOption = new JTextField("");
		jTOption.setSize(90, 20);
		jTOption.setLocation(90, 100);
		jTOption.setText("2 Player");
		rightPanel.add(jTOption);

		//Square label
		jLSquare = new JLabel("Square:");
		jLSquare.setSize(60, 20);
		jLSquare.setLocation(20, 125);
		rightPanel.add(jLSquare);

		// textfield for Square
		jTSquare = new JTextField("");
		jTSquare.setSize(90, 20);
		jTSquare.setLocation(90, 125);
		rightPanel.add(jTSquare);

		//Direction label
		jLDirection = new JLabel("Direction:");
		jLDirection.setSize(60, 20);
		jLDirection.setLocation(20, 150);
		rightPanel.add(jLDirection);

		// textfield for Direction
		jTDirection = new JTextField("");
		jTDirection.setSize(90, 20);
		jTDirection.setLocation(90, 150);
		jTDirection.setText("SE");
		rightPanel.add(jTDirection);

		// ^ Button
		jBUp = new JButton("^");
		jBUp.setSize(50, 30);
		jBUp.setLocation(80, 180);
		jBUp.setBackground(Color.white);
		jBUp.addActionListener(this);
		rightPanel.add(jBUp);

		//Empty Button for deisgn
		jBEmpty1 = new JButton("");
		jBEmpty1.setSize(50, 30);
		jBEmpty1.setLocation(20, 180);
		jBEmpty1.setEnabled(false);
		jBEmpty1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		rightPanel.add(jBEmpty1);

		//Empty Button for deisgn
		jBEmpty2 = new JButton("");
		jBEmpty2.setSize(50, 30);
		jBEmpty2.setLocation(140, 180);
		jBEmpty2.setEnabled(false);
		jBEmpty2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		rightPanel.add(jBEmpty2);

		//Ball Button
		jBBall = new JButton("");
		jBBall.setSize(50, 30);
		jBBall.setLocation(80, 215);
		rightPanel.add(jBBall);
		jBBall.setBackground(Color.white);
		ImageIcon iconball = new ImageIcon("images\\ball.png");
		jBBall.setIcon(iconball);

		// v Button
		jBDown = new JButton("v");
		jBDown.setSize(50, 30);
		jBDown.setBackground(Color.white);
		jBDown.setLocation(80, 250);
		jBDown.addActionListener(this);
		rightPanel.add(jBDown);


		// > Button
		jBForward = new JButton(">");
		jBForward.setSize(50, 30);
		jBForward.setBackground(Color.white);
		jBForward.setLocation(140, 215);
		jBForward.addActionListener(this);
		rightPanel.add(jBForward);

		// < Button
		jBBackward = new JButton("<");
		jBBackward.setSize(50, 30);
		jBBackward.setLocation(20, 215);
		jBBackward.setBackground(Color.white);
		jBBackward.addActionListener(this);
		rightPanel.add(jBBackward);

		//Empty button for design
		jBEmpty3 = new JButton("");
		jBEmpty3.setSize(50, 30);
		jBEmpty3.setLocation(20, 250);
		jBEmpty3.setEnabled(false);
		jBEmpty3.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		rightPanel.add(jBEmpty3);

		//Empty button for design
		jBEmpty4 = new JButton("");
		jBEmpty4.setSize(50, 30);
		jBEmpty4.setLocation(140, 250);
		jBEmpty4.setEnabled(false);
		jBEmpty4.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		rightPanel.add(jBEmpty4);

		// compass
		jBDirection = new JButton("");
		jBDirection.setSize(92, 92);
		jBDirection.setLocation(65, 290);
		jBDirection.setBackground(Color.white);
		rightPanel.add(jBDirection);
		ImageIcon iconcompass = new ImageIcon("images\\North.jpg");
		jBDirection.setIcon(iconcompass);

		// 2 Player, 4 Player, Multi and Exit.
		jBPlayer2 = new JButton("2 Player");
		jBPlayer2.setSize(80, 30);
		jBPlayer2.setLocation(20, 385);
		jBPlayer2.setBackground(Color.white);
		jBPlayer2.setVisible(true);
		jBPlayer2.addActionListener(this);
		rightPanel.add(jBPlayer2);

		jBPlayer4 = new JButton("4 Player");
		jBPlayer4.setSize(80, 30);
		jBPlayer4.setLocation(110, 385);
		jBPlayer4.setBackground(Color.white);
		jBPlayer4.addActionListener(this);
		jBPlayer4.setVisible(true);
		rightPanel.add(jBPlayer4);

		jBMultiPlayer = new JButton("Multi");
		jBMultiPlayer.setSize(80, 30);
		jBMultiPlayer.setLocation(20, 420);
		jBMultiPlayer.setBackground(Color.white);
		jBMultiPlayer.addActionListener(this);
		jBMultiPlayer.setVisible(true);
		rightPanel.add(jBMultiPlayer);

		jBExit = new JButton("Exit");
		jBExit.setSize(80, 30);
		jBExit.setLocation(110, 420);
		jBExit.setBackground(Color.white);
		jBExit.setVisible(true);
		jBExit.addActionListener(this);
		rightPanel.add(jBExit);

	// end of right panel elements.

	// Bottom panel layout
		bottomPanel = new JPanel();
		bottomPanel.setSize(814, 190);
		bottomPanel.setLocation(3, 485);
		bottomPanel.setLayout(new FlowLayout());
		add(bottomPanel);
		
		//Setting bottom panel's border as Etched Border
		bottomPanel.setBorder( BorderFactory.createEtchedBorder() );

		// bottom panel buttons act, run, reset
		jBAct = new JButton("Act");
		jBAct.setSize(75, 30);
		jBAct.setLocation(50, 20);
		jBAct.setVisible(true);
		jBAct.setBackground(Color.white);
		jBAct.addActionListener(this);
		bottomPanel.add(jBAct);
		ImageIcon iconAct = new ImageIcon("images\\step.png");
		jBAct.setIcon(iconAct);

		jBRun = new JButton("Run");
		jBRun.setSize(75, 30);
		jBRun.setLocation(135, 20);
		jBRun.setVisible(true);
		jBRun.setBackground(Color.white);
		jBRun.addActionListener(this);
		bottomPanel.add(jBRun);
		ImageIcon iconRun = new ImageIcon("images\\run.png");
		jBRun.setIcon(iconRun);

		jBReset = new JButton("Reset");
		jBReset.setSize(90, 30);
		jBReset.setLocation(220, 20);
		jBReset.setVisible(true);
		jBReset.setBackground(Color.white);
		jBReset.addActionListener(this);
		bottomPanel.add(jBReset);
		ImageIcon iconReset = new ImageIcon("images\\reset.png");
		jBReset.setIcon(iconReset);

		jLSpeed = new JLabel("                 Speed:");
		jLSpeed.setSize(60, 20);
		bottomPanel.add(jLSpeed);

		speedSlider = new JSlider(10, 200);
		speedSlider.setMajorTickSpacing(10);
		speedSlider.setPaintTicks(true);
		speedSlider.addChangeListener(this);
		speedSlider.setVisible(true);
		bottomPanel.add(speedSlider);


		//Center Panel settings.
		centerPanel = new JPanel();
		centerPanel.setSize(600, 483);
		centerPanel.setLocation(3, 1);
		centerPanel.setLayout(null);
		centerPanel.setBorder( BorderFactory.createEtchedBorder() );
		add(centerPanel);

		//Setting Game Canvas
		canvas = new GameCanvas();
		canvas.setSize(535, 435);
		canvas.setLocation(2, 2);
		canvas.setBackground(Color.red);

		//Creating an inner panel in the center panel
		innerCenterPanel = new JPanel();
		innerCenterPanel.setBorder( BorderFactory.createLineBorder(Color.BLACK, 2) );
		innerCenterPanel.add(canvas);
		innerCenterPanel.setLayout(null);
		innerCenterPanel.setLocation(30, 20);
		innerCenterPanel.setSize(540, 440);
		centerPanel.add(innerCenterPanel);

		// Setting timer to record time
		timerBallMove = new Timer(100, this);
		timerEplased = new Timer(1000, new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				//Starting eplased timer.
		
				//Setting seconds to 0 if it reaches 60 and add 1+ in minutes.
				if (nSeconds == 60) {
					nSeconds = 0;
					nMinutes++;
				}
	
	
				//Setting minutes to 0 if it reaches 60 and add 1+ in hours.
				if(nMinutes == 60){
					nMinutes = 0;
					nHours++;
				}
	
				//displaying counter
				jTHour.setText(nHours + "");
				jTMins.setText(nMinutes + "");
				jTSeconds.setText(nSeconds + "");

				//counting seconds.
				nSeconds++;
			}
		});

		//Moving Babies automatically on two & multi player modes.
		timerBabyMover = new Timer(100, new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				//Calling move babies method to move babies automatically.
				moveBabies();
			}
		});


		// menu Bar
		menuBar = new JMenuBar();
		menuScenario = new JMenu("Scenario");
		menuExit = new JMenuItem("Exit");
		menuControls = new JMenu("Controls");
		menuSave = new JMenuItem("Save");
		menuOpen = new JMenuItem("Open");
		menuEdit = new JMenu("Edit");
		menuCut = new JMenuItem("Cut");
		menuCopy = new JMenuItem("Copy");
		menuPaste = new JMenuItem("Paste");
		menuHelp = new JMenu("Help");
		menuAbout = new JMenuItem("About");
		menuHelpTopic = new JMenuItem("Help Topic");
		menuControls.add(menuSave);
		menuControls.add(menuOpen);
		menuScenario.add(menuExit);
		menuEdit.add(menuCut);
		menuEdit.add(menuCopy);
		menuEdit.add(menuPaste);
		menuHelp.add(menuAbout);
		menuHelp.add(menuHelpTopic);
		menuBar.add(menuScenario);
		menuBar.add(menuEdit);
		menuBar.add(menuControls);
		menuBar.add(menuHelp);
		setJMenuBar(menuBar);

		menuAbout.addActionListener(this);
		menuHelpTopic.addActionListener(this);


		// Initial direction values to help moving the ball
		directionX = 10;
		directionY = 0;

		this.requestFocus();
		jTSquare.setText(canvas.getCurrentBox());
		setResizable(false);
		this.addKeyListener(this);
		setVisible(true);

	}

	
	public void actionPerformed(ActionEvent e){
		// Ball Movement

		if (e.getSource() == timerBallMove) {
			//Timer helps ball to move automatically by calling kickBall method.
			kickBall();
		}

		if (e.getSource() == jBRun) {
			if (jBRun.getText() == "Run") {
			// Ball starts to move & button display changed to pause.
				timerBallMove.start();
				timerEplased.start();
				timerBabyMover.start();
				jBRun.setText("Pause");
				jBRun.setIcon(new ImageIcon("images\\pause.png"));

			} else {
			// Ball stops & button display changed to run.
				timerBallMove.stop();
				timerEplased.stop();
				timerBabyMover.stop();
				jBRun.setText("Run");
				jBRun.setIcon(new ImageIcon("images\\run.png"));
			}
		}

		// Checking if the ball intersects with Baby # 3
		if ((canvas.Xlocation >= canvas.XlocationB1 - 17 && canvas.Xlocation <= canvas.XlocationB1 + 40)
			&& (canvas.Ylocation >= canvas.YlocationB1 - 17 && canvas.Ylocation <= canvas.YlocationB1 + 45)) {

			//Adding score
			nLeftScore++;
			directionX = -directionX;
			directionY = -directionY;
			jTLeftScore.setText(nLeftScore + "");
		}
		

		// Checking if the ball intersects with Baby # 4
		if ((canvas.Xlocation >= canvas.XlocationB2 - 17 && canvas.Xlocation <= canvas.XlocationB2 + 40)
				&& (canvas.Ylocation >= canvas.YlocationB2 - 17 && canvas.Ylocation <= canvas.YlocationB2 + 15)) {

			//Adding score
			nRightScore++;
			directionX = -directionX;	
			directionY = -directionY;
			jTRightScore.setText(nRightScore + "");

		}

		//Check condition if two players are playing.
		if(canvas.BabyNum >= 4){
			// Checking if the ball intersects with Baby # 1
			if ((canvas.Xlocation >= canvas.XlocationB3 - 17 && canvas.Xlocation <= canvas.XlocationB3 + 40)
					&& (canvas.Ylocation >= canvas.YlocationB3 - 17 && canvas.Ylocation <= canvas.YlocationB3 + 45)) {
	
				nLeftScore++;	
				directionX = -directionX;
				directionY = -directionY;
				jTLeftScore.setText(nLeftScore + "");
	
			}
	

			// Checking if the ball intersects with Baby # 6
			if ((canvas.Xlocation >= canvas.XlocationB4 - 17 && canvas.Xlocation <= canvas.XlocationB4 + 40)
					&& (canvas.Ylocation >= canvas.YlocationB4 - 17 && canvas.Ylocation <= canvas.YlocationB4 + 15)) {
	
				nRightScore++;
				directionX = -directionX;	
				directionY = -directionY;
				jTRightScore.setText(nRightScore + "");
			
			}

			//Check condition if multi players are playing.
			if(canvas.BabyNum >= 6){

				// Checking if the ball intersects with Baby # 5
				if ((canvas.Xlocation >= canvas.XlocationB6 - 17 && canvas.Xlocation <= canvas.XlocationB6 + 40)
						&& (canvas.Ylocation >= canvas.YlocationB6 - 17 && canvas.Ylocation <= canvas.YlocationB6 + 45)) {
		
					nLeftScore++;	
					directionX = -directionX;
					directionY = -directionY;
					jTLeftScore.setText(nLeftScore + "");
				}
				
				// Checking if the ball intersects with Baby # 2
				if ((canvas.Xlocation >= canvas.XlocationB5 - 17 && canvas.Xlocation <= canvas.XlocationB5 + 40)
					&& (canvas.Ylocation >= canvas.YlocationB5 - 17 && canvas.Ylocation <= canvas.YlocationB5 + 45)) {
	
					nRightScore++;
					directionX = -directionX;	
					directionY = -directionY;
					jTRightScore.setText(nRightScore + "");
		
				}
				
			}
		}

		//Exit Button
		if (e.getSource() == jBExit) {
			System.exit(0);
		}

		//Switching on two player option
		if (e.getSource() == jBPlayer2) {
			canvas.BabyNum = 2;
			jTOption.setText("2 Player");
			canvas.repaint();
		}

		//Switching on four player option
		if (e.getSource() == jBPlayer4) {
			canvas.BabyNum = 4;
			jTOption.setText("4 Player");
			canvas.repaint();
		}

		//Swtiching on multi player option
		if (e.getSource() == jBMultiPlayer) {
			canvas.BabyNum = 6;
			jTOption.setText("Multi Player");
			canvas.repaint();
		}

		//Calling kickBall method on Act Button.
		if (e.getSource() == jBAct) {
			kickBall();
		}

		//Reseting the game.
		if (e.getSource() == jBReset) {

			dispose();
			timerBallMove.stop();
			timerEplased.stop();
			timerBabyMover.stop();
			new CBabyBallBounce().setVisible(true);

		}

		// Moving ball 1 block above.
		if (e.getSource() == jBUp) {

			if(canvas.Ylocation <= 0)
				canvas.Ylocation = 0;
			else{
				if(canvas.Ylocation < 32)
					canvas.Ylocation = 0;
				else
					canvas.Ylocation -= 32;
			}
			
			

			canvas.repaint();

		}

		// Moving ball 1 block down.
		if (e.getSource() == jBDown) {

			
			if (canvas.Ylocation >= (canvas.getHeight() - 32)){
				
					canvas.Ylocation = canvas.getHeight() - 32;
			}
			else{
				if((canvas.getHeight()-32)-canvas.Ylocation < 32){
					canvas.Ylocation = canvas.getHeight()-32;
				}else
					canvas.Ylocation += 32;
			}
	
			canvas.repaint();

		}

		//Moving ball 1 block right.
		if (e.getSource() == jBForward) {

			if(canvas.Xlocation >= (canvas.getWidth() - 32)){
				canvas.Xlocation = canvas.getWidth() - 32;
			}else{
				if((canvas.getWidth()-32)-canvas.Xlocation < 32){
					canvas.Xlocation = canvas.getWidth()-32;
				}else
					canvas.Xlocation += 32;
			}
		
			canvas.repaint();
			
		}

		//Moving ball 1 block left.
		if (e.getSource() == jBBackward) {

			if(canvas.Xlocation <= 0)
				canvas.Xlocation = 0;
			else{
				if(canvas.Xlocation < 32)
					canvas.Xlocation = 0;
				else
					canvas.Xlocation -= 32;
			}
			
			
			canvas.repaint();

		}

		//About menu coding.
		if(e.getSource() == menuAbout){
			JOptionPane.showMessageDialog(this, "This is about myself");
		}

		//Help Topic Menu coding.
		if(e.getSource() == menuHelpTopic){
			JOptionPane.showMessageDialog(this, "This is about help topic.");
		}		


		this.requestFocus();

		//checking and setting on which square the ball currently is.
		jTSquare.setText(canvas.getCurrentBox());

	}

	public void kickBall(){

		//Moving ball by changing X and Y direction
		canvas.Ylocation += directionY;
		canvas.Xlocation += directionX;


		// Check if the user has pressed Act button & changed initial 
		// position of the ball, then it should move randomly instead in line.
		if(canvas.Ylocation != 205 && bRunOnceOnly){
			directionY = 5;
			bRunOnceOnly = false;
		}

		//Check if ball meets the baby at the other side, it should bounce
		//and break the straight movement.
		if(canvas.Xlocation == 325 && bRunOnceOnly){
			directionY = 5;
			bRunOnceOnly = false;
		}

			
		//If ball hits the top wall of frame, change the direction of the ball down
		if(canvas.Ylocation <= 0)
			move("down");

		//If ball hits the left wall of the frame, bounce the ball right
		if(canvas.Xlocation <= 0)
			move("right");
		
		//If ball hits the right wall of the frame, bounce it left
		if (canvas.Xlocation >= canvas.getWidth() - 32) 
			move("left");
		
		//If ball meets the bottom boundry of frame, bounce it back up.
		if (canvas.Ylocation >= canvas.getHeight() - 32) 
			move("up");
		

		canvas.repaint();


	/* Basic Ball Movement (180 degree + bounce between babies forever) */

	/*
		//Change the X direction but let remain Y direction unchanged
		canvas.Xlocation += directionX;
		
		// Check if the user has pressed Act button & changed initial 
		// position of the ball, then it should move randomly instead in line.
		if(canvas.Ylocation != 205 && bRunOnceOnly){
			directionY = 5;
			bRunOnceOnly = false;
		}

		//Check if ball meets the baby at the other side, it should bounce
		//and break the straight movement.
		if(canvas.Xlocation == 325 && bRunOnceOnly){
			directionY = 5;
			bRunOnceOnly = false;
		}

	
		//If ball hits the left wall of the frame, bounce the ball by increasing X only
		if(canvas.Xlocation <= 0){
			directionX = -directionX;
		}
		
		//If ball hits the right wall of the frame, deduct the Y only
		if (canvas.Xlocation >= canvas.getWidth() - 32){
			directionX = -directionX;
		}
		
	*/

	}

	//Move method
	public void move(String direction){

		if(direction == "up")
			directionY = -directionY;
		if(direction == "left")
			directionX = -directionX;
		if(direction == "right")
			directionX = -directionX;
		if(direction == "down")
			directionY = -directionY;
	}
	
	public void moveBabies(){
		//Moving babies movement on Two Player Mode
		if(canvas.BabyNum >= 4){
			
			//0 means up, 1 means down

			if(nBabyDirection2 == 1){
				canvas.YlocationB3 += 5;
				canvas.YlocationB4 += 5;
			}else{
				canvas.YlocationB3 -= 5;
				canvas.YlocationB4 -= 5;
			}

			if(canvas.YlocationB3 == canvas.getHeight()-45)
				nBabyDirection2 = 0;
			
			if(canvas.YlocationB3 == 0)
				nBabyDirection2 = 1;


			//Moving babies movement on Multi Player Mode
			if(canvas.BabyNum >= 6){
			
			//0 means up, 1 means down

				if(nBabyDirection4 == 1){
					canvas.YlocationB5 += 5;
					canvas.YlocationB6 += 5;
				}else{
					canvas.YlocationB5 -= 5;
					canvas.YlocationB6 -= 5;
				}

				if(canvas.YlocationB5 == canvas.getHeight()-45)
					nBabyDirection4 = 0;
			
				if(canvas.YlocationB5 == 0)
					nBabyDirection4 = 1;
	
			}

		}
	}

	//Setting speed on slider change.
	public void stateChanged(ChangeEvent e){
		timerBallMove.setDelay(210 - speedSlider.getValue());
		timerBabyMover.setDelay(210 - speedSlider.getValue());
	}

	//Setting Key Press Events to Move Babies with Keyboard.
	public void keyReleased(KeyEvent k){}
	public void keyTyped(KeyEvent k){}
	public void keyPressed(KeyEvent k){
		
		//Moving left baby left on A
		if(k.getKeyCode() == KeyEvent.VK_A){
			if(canvas.XlocationB1 > 0)
				canvas.XlocationB1 -= 5;			
		}

		//Moving left baby up on W
		if(k.getKeyCode() == KeyEvent.VK_W){
			if(canvas.YlocationB1 > 0)
				canvas.YlocationB1 -= 5;
		}

		//Moving left baby up on S
		if(k.getKeyCode() == KeyEvent.VK_S){
			if(canvas.YlocationB1 < canvas.getHeight()-45)
				canvas.YlocationB1 += 5;
		}

		//Moving left baby right on D
		if(k.getKeyCode() == KeyEvent.VK_D){
			if(canvas.XlocationB1 < 185)
				canvas.XlocationB1 += 5;
		}

		//Moving right baby up on UP
		if(k.getKeyCode() == KeyEvent.VK_UP){
			if(canvas.YlocationB2 > 0)
				canvas.YlocationB2 -= 5;
		}

		//Moving right baby down on DOWN
		if(k.getKeyCode() == KeyEvent.VK_DOWN){
			if(canvas.YlocationB2 < canvas.getHeight()-45)
				canvas.YlocationB2 += 5;
		}

		//Moving right baby left on RIGHT
		if(k.getKeyCode() == KeyEvent.VK_RIGHT){
			if(canvas.XlocationB2 < canvas.getWidth()-40)
				canvas.XlocationB2 += 5;
		}		

		//Moving right baby right on LEFT
		if(k.getKeyCode() == KeyEvent.VK_LEFT){
			if(canvas.XlocationB2 > 305)
				canvas.XlocationB2 -= 5;
		}

		canvas.repaint();
	}


	public static void main(String[] args) {
		
		new CBabyBallBounce();

	}	

}



class GameCanvas extends JPanel {


	Image image;
	//Default No. of Babies are 2.
	int BabyNum = 2;


	//Setting initial positions of babies and other designs.
	int Xlocation, Ylocation, XlocationB1 = 130, YlocationB1 = 200, XlocationB2 = 350, YlocationB2 = 200,
			XlocationB3 = 30, YlocationB3 = 370, XlocationB4 = 450, YlocationB4 = 370, 
			XlocationB5 = 70, YlocationB5 = 50, XlocationB6 = 400, YlocationB6 = 50, XlocationW = 230, YlocationW = 0,
			XlocationBOX = 0, YlocationBOX = 0;

	Image imgball, imgWhiteBaby, imgBlackBaby, imgWall, imgBox;

	public GameCanvas() {
		//Creating images from files.
		imgBox = Toolkit.getDefaultToolkit().getImage("images\\white.jpg");
		imgWhiteBaby = Toolkit.getDefaultToolkit().getImage("images\\baby1.png");
		imgBlackBaby = Toolkit.getDefaultToolkit().getImage("images\\baby2.png");
		imgWall = Toolkit.getDefaultToolkit().getImage("images\\bricks2.jpg");
		imgball = Toolkit.getDefaultToolkit().getImage("images\\ball.png");

		//Setting the default location of the ball just after the first baby.
		this.Xlocation = XlocationB1+45;
		this.Ylocation = YlocationB1+5;		
		
	}

	public void paint(Graphics g) {

		//Clearing the canvas
		g.clearRect(0, 0, getWidth(), getHeight());

		//Drawing the 13 x 16 Grid.
		for (YlocationBOX = 0; YlocationBOX <= 470; YlocationBOX += 32) {

			for (XlocationBOX = 0; XlocationBOX <= 520; XlocationBOX += 32) {
				g.drawImage(imgBox, XlocationBOX, YlocationBOX, this);
			}
		}

		//Displaying first 2 babies
		g.drawImage(imgWhiteBaby, XlocationB1, YlocationB1, this);
		g.drawImage(imgBlackBaby, XlocationB2, YlocationB2, this);


		//If Two player mode is switched on.
		if (BabyNum >= 4) {

			g.drawImage(imgBlackBaby, XlocationB3, YlocationB3, this);
			g.drawImage(imgWhiteBaby, XlocationB4, YlocationB4, this);

			//If multi player mode is switched on.
			if (BabyNum >= 6) {
	
				g.drawImage(imgBlackBaby, XlocationB5, YlocationB5, this);
				g.drawImage(imgWhiteBaby, XlocationB6, YlocationB6, this);
			}
		}




		//Drawing the middle wall boundary
		for (YlocationW = 0; YlocationW <= 470; YlocationW += 72) {

			g.drawImage(imgWall, XlocationW, YlocationW, this);

		}

		//Displaying the ball.
		g.drawImage(imgball, Xlocation, Ylocation, this);
		
	}

	//Detecting on which box the ball currently is.
	public String getCurrentBox(){
		int horizontal = (Xlocation/32)+2;
		int vertical   = (Ylocation/32);
	
		return String.valueOf((((vertical*16)-1)+horizontal));
	}

}