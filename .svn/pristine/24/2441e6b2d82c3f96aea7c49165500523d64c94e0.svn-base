/*
 * TCSS 305 - Assignment 6
 * 
 * GUI/Frame class for assignment 6 - Tetris
 */


package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.Timer;

import listeners.MyKeyListener;
import model.Board.GameStatus;

/**
 * The Class Tetris GUI.
 * 
 * @author Josh Rueschenberg
 * @version 2.0
 */
@SuppressWarnings("serial")
public class TetrisGUI extends JFrame implements Observer, PropertyChangeListener {
    
    /** The Constant for the swing timer delay. */
    private static final int TIMER_DELAY = 800;
    
    /** The Constant number to subtract from TIMER_DELAY when game levels up. */
    private static final int TIMER_LEVEL_UP = 100;
    
    /** The Constant for a SMALL_GAME. */
    private static final Dimension SMALL_GAME = new Dimension(200, 400);
    
    /** The Constant for a MEDIUM_GAME. */
    private static final Dimension MEDIUM_GAME = new Dimension(300, 600);
    
    /** The Constant for a LARGE_GAME. */
    private static final Dimension LARGE_GAME = new Dimension(400, 800);
    
    /** The my menu bar. */
    private final JMenuBar myMenuBar;
    
    /** My display. */
    private final TetrisGraphics myDisplay;
    
    /** My info pane. */
    private final ScorePanel myInfoPane;
    
    /** My key listener. */
    private final MyKeyListener myKeyListener;
    
    /** My game is paused. */
    private boolean myGameIsPaused;
    
    /** My board timer. */
    private final Timer myTimer;
    
    /** My actions. */
    private final Action myTimerAction;
    
    
    /**
     * Instantiates a new tetris gui.
     *
     * @param theMenu the frame menu
     * @param theTimerAction the timer action
     * @param theInfo the info/score panel
     * @param theDisplay the tetris game display
     * @param theListener the key listener
     */
    public TetrisGUI(final TetrisMenu theMenu, final Action theTimerAction, 
                     final ScorePanel theInfo, final TetrisGraphics theDisplay, 
                     final MyKeyListener theListener) {
        super();
        myMenuBar = theMenu;
        myDisplay = theDisplay;
        myInfoPane = theInfo;
        myKeyListener = theListener;
        myGameIsPaused = false;
        myTimer = new Timer(TIMER_DELAY, null);
        myTimerAction = theTimerAction;
        
    }
    
    /**
     * Start, gets things going in the GUI.
     */
    public void start() {
        setTitle("Tetris - Josh Rueschenberg");
        final ImageIcon icon = new ImageIcon("support_files/icons/tIcon2.png");
        setIconImage(icon.getImage());
        
        myTimer.addActionListener(myTimerAction);
        
        myTimer.start();
        
        myMenuBar.addPropertyChangeListener(this);
        addKeyListener(myKeyListener);
        addKeyListener(new MyPauseListener());
        setJMenuBar(myMenuBar);
        
        add(myDisplay, BorderLayout.CENTER);
   
        add(myInfoPane, BorderLayout.EAST);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pack();
        setResizable(false);
        setBoardSize(MEDIUM_GAME);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    /**
     * Sets the board size.
     *
     * @param theSize the new board size
     */
    private void setBoardSize(final Dimension theSize) {
        myDisplay.setPreferredSize(theSize);
        pack();
    }
    
    /**
     * Level up and decrease timer delay.
     */
    private void levelUp() {
        
        final int timer = myTimer.getDelay();
        if (timer >= TIMER_LEVEL_UP) {
            myTimer.setDelay(timer - TIMER_LEVEL_UP);
        }        
    }
    
    /**
     * Pause.
     */
    private void pause() {
        if (myGameIsPaused) {
            myTimer.stop();
            removeKeyListener(myKeyListener);
        } else {
            myTimer.start();
            addKeyListener(myKeyListener);
            
        }
    }
    
    /**
     * End game procedure.
     */
    private void endGame() {
        removeKeyListener(myKeyListener);
        myTimer.stop();
    }
    
    /**
     * Resets the timer and removes/adds the key listener.
     */
    private void reset() {
        removeKeyListener(myKeyListener);
        myTimer.setDelay(TIMER_DELAY);
        pause();
    }
    
    /** 
     * Observer method that detects game over notifications.
     * 
     * @param theObserve the observable source object
     * @param theObject the object being passed from observable class
     */
    @Override
    public void update(final Observable theObserve, final Object theObject) {        
        
        if (theObject instanceof GameStatus && ((GameStatus) theObject).isGameOver()) {
            myTimer.stop();
            removeKeyListener(myKeyListener);
        }

    }
    
    /** 
     * Property change listener for detecting exit, pause, end game, new game, level up, 
     * and size change commands from the menu.
     * 
     * @param theProp the fired property
     */
    public void propertyChange(final PropertyChangeEvent theProp) {
        if ("exit".equals(theProp.getPropertyName())) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if ("pause".equals(theProp.getPropertyName())) {
            myGameIsPaused ^= true;
            pause();
        } else if ("end game".equals(theProp.getPropertyName())) {
            endGame();
        } else if ("new game".equals(theProp.getPropertyName())) {
            reset();
        } else if ("level up".equals(theProp.getPropertyName())) {
            levelUp();
        } else if ("small".equals(theProp.getPropertyName())) {
            setBoardSize(SMALL_GAME);
        } else if ("medium".equals(theProp.getPropertyName())) {
            setBoardSize(MEDIUM_GAME);
        } else if ("large".equals(theProp.getPropertyName())) {
            setBoardSize(LARGE_GAME);
        }
    }
    
    /**
     * Inner class key listener for pause function allows me access to pause method in GUI.
     */
    private class MyPauseListener extends KeyAdapter {
        /**
         *  Handles presses of the P key and pauses/unpauses the game. 
         *
         * @param theEvent the key event
         */
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_P) {
                myGameIsPaused ^= true;
                pause();
                firePropertyChange("pause key", null, null);
            }
        }
    }

}
