/*
 * TCSS 305 - Assignment 6
 * 
 * Main class for assignment 6 - Tetris
 */


package view;

import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import listeners.BoardAdvanceListener;
import listeners.MyKeyListener;
import model.Board;


/**
 * The Main class for Tetris, gets everything going.
 * 
 * @author Josh Rueschenberg
 * @version 2.0
 */
public final class TetrisMain implements PropertyChangeListener {

    /** The private constant for my tetris board. */
    private static final Board MY_BOARD = new Board();
    
    /** My sound clip file. */
    private Clip myClip;
    
    /** My music is paused. */
    private boolean myMusicIsPaused;
    
    
    
    /**
     * Private constructor prevents external instantiation of tetris main class.
     */
    private TetrisMain() {
        //do nothing
    }
    
    /**
     * The main method, creates all objects for tetris game and starts the music.
     *
     * @param theArgs the arguments
     */
    public static void main(final String[] theArgs) {
        final TetrisMain tetris = new TetrisMain();
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               
                tetris.createComponents(tetris);
                tetris.playMusic();
                tetris.myMusicIsPaused = false;
                
            }
            
        });
    }
    
    /**
     * Adds the observers to the board.
     *
     * @param theMenu the tetris menu
     * @param theGUI the gui
     * @param theGraphics the graphical display of the board
     * @param theScore the score panel
     * @param thePreview the next piece preview
     */
    private void addObservers(final TetrisMenu theMenu, final TetrisGUI theGUI, 
                              final TetrisGraphics theGraphics,  final ScorePanel theScore, 
                              final NextPiecePanel thePreview) {
       
        MY_BOARD.addObserver(theMenu);
        MY_BOARD.addObserver(theGUI);
        MY_BOARD.addObserver(theGraphics);
        MY_BOARD.addObserver(theScore);
        MY_BOARD.addObserver(thePreview);
        
    }
    
    /**
     * Creates the components and attaches all property change listeners.
     * Clears board and starts the GUI.
     * 
     * Created to reduce amount of code in Runnable inner class.
     *
     * @param theMain reference to an instance of the main class.
     */
    private void createComponents(final TetrisMain theMain) {
        final TetrisMenu menu = new TetrisMenu();
        final TetrisGraphics gameDisplay = new TetrisGraphics();
        final NextPiecePanel piecePreview = new NextPiecePanel();
        final ScorePanel infoPane = new ScorePanel(piecePreview);
        final MyKeyListener keyListen = new MyKeyListener(MY_BOARD);

        final TetrisGUI gui = new TetrisGUI(menu, new BoardAdvanceListener(MY_BOARD), 
                                            infoPane, gameDisplay, keyListen);
        menu.addPropertyChangeListener(theMain);
        menu.addPropertyChangeListener(infoPane);
        menu.addPropertyChangeListener(gameDisplay);
        infoPane.addPropertyChangeListener(gui);
        gui.addPropertyChangeListener(gameDisplay);
        theMain.addObservers(menu, gui, gameDisplay, infoPane, piecePreview);
        
        MY_BOARD.clear();
        gui.start();
    }
    
    /**
     * Plays background music.
     */
    private void playMusic() {

        try {

            final File sound = new File("support_files/music/TetrisStep.wav");
            final AudioInputStream inputStream = AudioSystem.getAudioInputStream(sound);
            myClip = AudioSystem.getClip();

            myClip.open(inputStream);
            myClip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (final LineUnavailableException ex) {
            JOptionPane.showMessageDialog(null, "Program encountered a problem opening"
                                          + " sound file!");

        } catch (final IOException ex) {
            JOptionPane.showMessageDialog(null, "Program encountered a problem finding"
                            + " the sound file!");

        } catch (final UnsupportedAudioFileException ex) {
            JOptionPane.showMessageDialog(null, "Sound file format unsupported!");

        }
        
    }

    /** 
     * Property change listener to detect new game, and music pause commands.
     * 
     * @param theProp the fired property change
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theProp) {
        if ("new game".equals(theProp.getPropertyName())) {
            MY_BOARD.clear();
        } else if ("music".equals(theProp.getPropertyName())) {
            myMusicIsPaused ^= true;
            if (myMusicIsPaused) {
                myClip.stop();
            } else {
                myClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
        
    }

}
