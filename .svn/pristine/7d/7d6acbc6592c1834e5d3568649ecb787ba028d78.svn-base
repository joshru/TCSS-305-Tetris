/*
 * TCSS 305 - Assignment 6
 * 
 * Keyboard listener for assignment 6 - Tetris
 */


package listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Board;


/**
 * Key listener class for controlling pieces on the tetris board.
 * 
 * @author Josh Rueschenberg
 * @version 1.0
 */
public class MyKeyListener extends KeyAdapter {

    /** My Tetris board. */
    private final Board myBoard;
    
    /**
     * Instantiates a new my key listener.
     *
     * @param theBoard the tetris board
     */
    public MyKeyListener(final Board theBoard) {
        super();
        myBoard = theBoard;
    }
    
    /**
     *  Handles key presses and manipulates pieces on the board accordingly. 
     *
     * @param theEvent the key event
     */
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        switch (theEvent.getKeyCode()) {
            case KeyEvent.VK_A:
                myBoard.left();
                break;

            case KeyEvent.VK_D:
                myBoard.right();
                break;

            case KeyEvent.VK_E:
                myBoard.rotateCW();
                break;

            case KeyEvent.VK_Q:
                myBoard.rotateCCW();
                break;

            case KeyEvent.VK_S:
                myBoard.down();
                break;

            case KeyEvent.VK_SPACE:
                myBoard.drop();
                break;

            default:
                break;
        }
        
    }    
    
}

