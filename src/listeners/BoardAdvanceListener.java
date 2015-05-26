/*
 * TCSS 305 - Assignment 6
 * 
 * Timer listener for assignment 6 - Tetris
 */


package listeners;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model.Board;

/**
 * Listener class for timer in tetris GUI.
 * 
 * @author Josh Rueschenberg
 * @version 1.0
 */
@SuppressWarnings("serial")
public class BoardAdvanceListener extends AbstractAction {

    /** My tetris board. */
    private final Board myBoard;
    
    /**
     * Instantiates a new board advance listener.
     *
     * @param theBoard the tetris board.
     */
    public BoardAdvanceListener(final Board theBoard) {
        super();
        myBoard = theBoard;
    }
    
    /** 
     * @param theEvent the action event.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myBoard.step();
    }

}
