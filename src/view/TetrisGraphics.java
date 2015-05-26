/*
 * TCSS 305 - Assignment 6
 * 
 * Game display panel for assignment 6 - Tetris
 */


package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JPanel;

import model.Board.BoardData;
import model.Board.GameStatus;

/**
 * The Class for displaying the actual tetris game.
 * 
 * @author Josh Rueschenberg
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TetrisGraphics extends JPanel implements Observer, PropertyChangeListener {
    
    /** The Constant string for font name. */
    private static final String FONT = "Helvetica";
    
    /** The Constant BLOCKS_PER_ROW. */
    private static final int BLOCKS_PER_ROW = 10;
    
    /** The Constant BLOCKS_PER_COLUMN. */
    private static final int BLOCKS_PER_COLUMN = 20;
    
    /** The Constant for centering the inner black rectangle. */
    private static final int INNER_BLOCK_PAD = 4;
    
    /** The Constant for centering the pause message. */
    private static final int PAUSE_PAD = 3;
    
    /** The Constant for centering the game over message. */
    private static final int GAME_OVER_PAD = 5; 
    
    /** My board data object. */
    private BoardData myBoardData;
    
    /** The collection of color arrays from the board data. */
    private List<Color[]> myRawBoardData;
    
    /** My grid is enabled. */
    private boolean myGridIsEnabled;
    
    /** My game is paused. */
    private boolean myGameIsPaused;
    
    /** My game is over. */
    private boolean myGameIsOver;
    
    /** The my rave mode enabled. */
    private boolean myRaveEnabled;
    
    /**
     * Instantiates a new tetris game display.
     */
    public TetrisGraphics() {
        super();
        myGridIsEnabled = false;
        myGameIsPaused = false;
        myGameIsOver = false;
        myRaveEnabled = false;
        setBackground(Color.BLACK);
        setVisible(true);
    }
    
    /** 
     * Paints the pieces, pause, grid, and game over on the display for the tetris game.
     * 
     * @param theGraphics the graphics that will be drawn. 
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D gfx = (Graphics2D) theGraphics;
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);        
        
        final int scale = getWidth() / BLOCKS_PER_ROW;
        final int rounding = scale / 2;       
        
        if (myGridIsEnabled && !myGameIsOver) {
            drawGrid(gfx, scale);
        }
        
        if (myRaveEnabled) {
            drawRave(gfx, scale);
        }
        
        if (myBoardData != null) {
            drawNormalPieces(gfx, scale, rounding);
        }
        
        if (myGameIsOver) {
            drawGameOver(gfx, scale);
        }
        
        if (myGameIsPaused) {
            drawPaused(gfx, scale);
        }
        

    }
    
    /**
     * Draws the normal game board with pieces.
     *
     * @param theGraphics the graphics object
     * @param theScale the scale factor
     * @param theRounding the rounding factor
     */
    private void drawNormalPieces(final Graphics2D theGraphics, final int theScale, 
                                  final int theRounding) {
        final Graphics2D gfx = theGraphics;
        final Iterator<Color[]> itr = myRawBoardData.iterator();
        
        for (int i = BLOCKS_PER_COLUMN - 1; i >= 0; i--) {
            final Color[] rowColors = itr.next();
                
            for (int j = rowColors.length - 1; j >= 0; j--) {
                
                if (rowColors[j] != null) {
                    gfx.setPaint(rowColors[j]);
                    gfx.fillRoundRect(j * theScale, i * theScale, theScale - 1, theScale - 1, 
                                      theRounding, theRounding);

                    gfx.setPaint(Color.BLACK);
                    gfx.fillRoundRect(j * theScale + 2, i * theScale + 2, 
                                      theScale - INNER_BLOCK_PAD, theScale - INNER_BLOCK_PAD, 
                                      theRounding, theRounding);

                }
                    
            }
        }
    }
    
    /**
     * Draws the grid.
     *
     * @param theGraphics the graphics object
     * @param theScale the scale factor
     */
    private void drawGrid(final Graphics2D theGraphics, final int theScale) {
        final Graphics2D gfx = theGraphics;
        gfx.setStroke(new BasicStroke(1));
        gfx.setColor(Color.GRAY);
        for (int i = 0; i <= (getWidth() / theScale); i++) {
            for (int j = 0; j <= (getHeight() / theScale); j++) {
                gfx.drawRect(i * theScale, j * theScale, theScale + 1, theScale + 1);
            }
        }
    }
    
    /**
     * Draw paused game screen.
     *
     * @param theGraphics the graphics object
     * @param theScale the scale factor
     */
    private void drawPaused(final Graphics2D theGraphics, final int theScale) {
        final Graphics2D gfx = theGraphics;
        final Color gameOver = new Color(0, 0, 0, 190);
        gfx.setPaint(gameOver);
        gfx.fillRect(0, 0, getWidth(), getHeight());
        gfx.setPaint(Color.WHITE);
        gfx.setFont(new Font(FONT, Font.BOLD, theScale));
        gfx.drawString("PAUSED", getWidth() / PAUSE_PAD, getHeight() / PAUSE_PAD);
    }
    
    /**
     * Draw game over screen.
     *
     * @param theGraphics the graphics object
     * @param theScale the scale factor
     */
    private void drawGameOver(final Graphics2D theGraphics, final int theScale) {
        final Graphics2D gfx = theGraphics;
        final Color gameOver = new Color(0, 0, 0, 190);
        gfx.setPaint(gameOver);
        gfx.fillRect(0, 0, getWidth(), getHeight());
        gfx.setPaint(Color.WHITE);
        gfx.setFont(new Font(FONT, Font.BOLD, theScale));
        gfx.drawString("GAME OVER", getWidth() / GAME_OVER_PAD , getHeight() / 2);
    }
    
    /**
     * Draw rave mode.
     *
     * @param theGraphics the graphics object
     * @param theScale the scale factor
     */
    private void drawRave(final Graphics2D theGraphics, final int theScale) {
        final Graphics2D gfx = theGraphics;
        gfx.setStroke(new BasicStroke(1));
        gfx.setColor(Color.GRAY);
        for (int i = 0; i <= (getWidth() / theScale); i++) {
            for (int j = 0; j <= (getHeight() / theScale); j++) {
                gfx.setPaint(randomColor());
                gfx.fillRect(i * theScale, j * theScale, theScale + 1, theScale);
                gfx.setPaint(Color.BLACK);
                gfx.fillRect(i * theScale + 2, j * theScale + 2, theScale - INNER_BLOCK_PAD, 
                                                                   theScale - INNER_BLOCK_PAD);
             // infinite recursion intentional for faster color flashing   
                repaint();
            }
        }
    }
    

    
    /**
     * Creates a random color.
     *
     * @return the random color
     */
    private Color randomColor() {
        final Random rand = new Random();
        final int r = rand.nextInt(255);
        final int g = rand.nextInt(255);
        final int b = rand.nextInt(255);
        
        return new Color(r, g, b).brighter();
    }

    /** 
     * Observer method to detect changes on the board and game over notifications. 
     * 
     * @param theObserve the observable source object
     * @param theObject the object passed by the observable class
     */
    @Override
    public void update(final Observable theObserve, final Object theObject) {
        if (theObject instanceof BoardData) {
            myBoardData = (BoardData) theObject;
            myRawBoardData = myBoardData.getBoardData();
            repaint();
        }
        
        if (theObject instanceof GameStatus && ((GameStatus) theObject).isGameOver()) {
            myGameIsOver ^= true;
            repaint();
        }
        
    }

    /** 
     * Property change listener for detecting grid, game over, new game, pause, and rave mode.
     * 
     * @param theProp the fired property change.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theProp) {
        if ("grid".equals(theProp.getPropertyName())) {
            myGridIsEnabled ^= true;
            repaint();
        } else if ("end game".equals(theProp.getPropertyName())) {
            myGameIsOver = true;
            repaint();
        } else if ("new game".equals(theProp.getPropertyName())) {
            myGameIsOver = false;
            repaint();
        } else if ("pause".equals(theProp.getPropertyName()) 
                        || "pause key".equals(theProp.getPropertyName())) {
            myGameIsPaused ^= true;
            repaint();
        } else if ("rave".equals(theProp.getPropertyName())) {
            myRaveEnabled ^= true;
            repaint();
        } 
        
    }
    
}