/*
 * TCSS 305 - Assignment 6
 * 
 * Score information action for assignment 6 - Tetris
 */


package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * The Class ScoringAction.
 * 
 * @author Josh Rueschenberg
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ScoringAction extends AbstractAction {

    /**
     * Instantiates a new scoring action.
     */
    public ScoringAction() {
        super("Scoring...");
    }
    
    /** 
     * @param theEvent the action event
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        JOptionPane.showMessageDialog(null, "Scoring formula pulled from official tetris wiki."
                        + "\nClearing 1 line = 40 * (current level + 1)"
                        + "\nClearing 2 lines = 100 * (current level + 1)"
                        + "\nClearing 3 lines = 300 * (current level + 1)"
                        + "\nClearing 4 lines = 1200 * (current level + 1)");

    }

}
