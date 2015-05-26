/*
 * TCSS 305 - Assignment 6
 * 
 * Piece preview panel for assignment 6 - Tetris
 */


package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Point;
import model.TetrisPiece;

/**
 * The Class for the next piece preview.
 * 
 * @author Josh Rueschenberg
 * @version 2.0
 */
@SuppressWarnings("serial")
public final class NextPiecePanel extends JPanel implements Observer {
    
    /** The Constant for the size of each colored block in the piece. */
    private static final int BLOCK_SIZE = 20;
    
    /** The Constant used to make the piece appear more centered. */
    private static final int BLOCK_VERT_PADDING = 15;
    
    /** The Constant for rounding factor on round rectangles. */
    private static final int ROUNDING = 6;
    
    /** The Constant size for the inner black block for each piece. */
    private static final int SUB_BLOCK_SIZE = (BLOCK_SIZE / 2) + ROUNDING;
    
    /** The Constant for the size of the JPanel. */
    private static final Dimension SIZE = new Dimension(100, 100);

    /** The next piece to show as a preview. */
    private TetrisPiece myPiece; 
    
    /** My piece points. */
    private Point[] myPiecePoints;
    
    /**
     * Instantiates a new next piece preview panel.
     */
    public NextPiecePanel() {
        super();
        setPreferredSize(SIZE);
        setBackground(Color.BLACK);
        
        final TitledBorder title = new TitledBorder("Next Piece");
        title.setTitleColor(Color.WHITE);
        title.setTitleJustification(TitledBorder.CENTER);
        
        setBorder(title);
        
    }
    
    /** 
     * Paints the next piece that will presented to the user.
     * 
     * @param theGraphics the graphics that will be drawn. 
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D gfx = (Graphics2D) theGraphics;
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (myPiece != null && myPiecePoints != null) {
            for (final Point p : myPiecePoints) {
                
                gfx.setPaint(myPiece.color());
             /** 
              * Because the raw block points are only 1-2 pixels apart some confusing math is 
              * required to manipulate the blocks to not overlap each other.
              *
              * Multiply the X coordinate by the block size(20), then adding half the width of 
              * the preview panel to that. Finally subtract double the block size plus 2 allows
              * the piece to be approximately centered in the preview panel. I add 2 at the end
              * to further center the piece and try to make it as neat as possible. 
              * Adding a factor of 18 to the Y coordinate pushes the block away from the top of
              * the panel.    
              * Formula is quite long, might try and clean it up later but looks nice for now.
              * 
              * Examples of translation: (0, 1) --> (9, 58) | (2, 1) --> (49, 22)
              */
                gfx.fillRoundRect((p.x() * BLOCK_SIZE) + ((getWidth() / 2) 
                                                       - (2 * BLOCK_SIZE)) + 1,
                                  (-p.y() * BLOCK_SIZE) + (getHeight()) - (2 * BLOCK_SIZE) 
                                                                        + BLOCK_VERT_PADDING,
                                   BLOCK_SIZE - 1, BLOCK_SIZE - 1, ROUNDING, ROUNDING);
                
                gfx.setPaint(Color.BLACK);
   
                gfx.fillRoundRect((p.x() * BLOCK_SIZE) + ((getWidth() / 2) 
                                                       - (2 * BLOCK_SIZE) + 2),
                                  (-p.y() * BLOCK_SIZE) + ((getHeight()) 
                                                        - (2 * BLOCK_SIZE)) 
                                                        + (BLOCK_VERT_PADDING + 2), 
                                   SUB_BLOCK_SIZE, SUB_BLOCK_SIZE, ROUNDING, ROUNDING);
                
            }
        }
            
    }
    
    /** 
     * @param theObserve the observable source object
     * @param theObject the object sent by observable source object
     */
    @Override
    public void update(final Observable theObserve, final Object theObject) {
        if (theObject instanceof TetrisPiece) {
            myPiece = (TetrisPiece) theObject;
            myPiecePoints = myPiece.points();
            repaint();
        }
    }

}
