/*
 * TCSS 305 - Assignment 6
 * 
 * Score information panel for assignment 6 - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Board.CompletedLines;

/**
 * The Class ScorePanel.
 * 
 * @author Josh Rueschenberg
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ScorePanel extends JPanel implements Observer, PropertyChangeListener {

    /** The Constant string for Message formatter about lines needed to level up. */
    private static final String LINES_CLEARED_MESS_STRING = "Lines Cleared: ({0}/{1})";
    
    /** The Constant string for score. */
    private static final String SCORE_STRING = "Current Score: ";
    
    /** The Constant for current level. */
    private static final String LEVEL_STRING = "Current Level: ";

    /** The Constant multiple for clearing 1 line. */
    private static final int ONE_ROW_MULT = 40;
    
    /** The Constant multiple for clearing 2 lines. */
    private static final int TWO_ROW_MULT = 100;
    
    /** The Constant multiple for clearing 3 lines. */
    private static final int THREE_ROW_MULT = 300;
    
    /** The Constant multiple for clearing 4 lines. */
    private static final int FOUR_ROW_MULT = 1200;
    
    /** The Constant for the initial number of lines needed to level up. */
    private static final int INITIAL_LINES_TO_LEVEL = 5;
    
    /** The Constant for vertical strut padding. */
    private static final int PADDING = 10;
    
    /** The Constant for FONT_SIZE. */
    private static final Font SCORE_FONT = new Font("Helvetica", Font.BOLD, 12);
    
    /** The Constant SCORE_SIZE for score display. */
    private static final Dimension SCORE_SIZE = new Dimension(190, 90);
    
    /** The Constant CONTROL_SIZE for control display. */
    private static final Dimension CONTROL_SIZE = new Dimension(190, 170);
    
    /** My next piece preview panel. */
    private final NextPiecePanel myPreview;
    
    /** The number of lines cleared. */
    private int myLinesCleared;
    
    /** The my lines to level. */
    private int myLinesToLevel;
    
    /** My score. */
    private int myScore;
    
    /** My current level. */
    private int myLevel;
    
    /** My lines label. */
    private final JLabel myLinesLabel;
    
    /** My score label. */
    private final JLabel myScoreLabel;
    
    /** My level label. */
    private final JLabel myLevelLabel;
    
    
    /**
     * Instantiates a new score panel.
     *
     * @param thePreview the piece preview panel
     */
    public ScorePanel(final NextPiecePanel thePreview) {
        super();
        
        myPreview = thePreview;
        myLinesCleared = 0;
        myLinesToLevel = INITIAL_LINES_TO_LEVEL; 
        myScore = 0;
        myLevel = 1;
        myLinesLabel = new JLabel(MessageFormat.format(LINES_CLEARED_MESS_STRING
                                                       , myLinesCleared, myLinesToLevel));
        myScoreLabel = new JLabel(SCORE_STRING + myScore);
        myLevelLabel = new JLabel(LEVEL_STRING + myLevel);
        
        addComponents();
    }
    
    /**
     * Setup labels with proper font and color, add title border.
     */
    private void setupLabels() {
        final TitledBorder title = new TitledBorder("Game Information");
        title.setTitleColor(Color.WHITE);
        title.setTitleJustification(TitledBorder.CENTER);
        setBorder(title);
        
        myLinesLabel.setFont(SCORE_FONT);
        myLinesLabel.setForeground(Color.WHITE);

        myScoreLabel.setFont(SCORE_FONT);
        myScoreLabel.setForeground(Color.WHITE);

        myLevelLabel.setFont(SCORE_FONT);
        myLevelLabel.setForeground(Color.WHITE);
    }
    
    /**
     * Adds the components to the panel.
     */
    private void addComponents() {
        setupLabels();
        
        setBackground(Color.BLACK);
        
        final JPanel info = new JPanel();
        info.setPreferredSize(SCORE_SIZE);
        info.setBackground(Color.BLACK);
        
        final TitledBorder infoTitle = new TitledBorder("Score:");
        infoTitle.setTitleColor(Color.WHITE);
        infoTitle.setTitleJustification(TitledBorder.CENTER);
        
        info.add(myLinesLabel);
        info.add(myScoreLabel);
        info.add(myLevelLabel);
        info.setBorder(infoTitle);
        
        final JPanel controls = new JPanel();
        final List<JLabel> controlLabels = createControlLabels();
        
        for (final JLabel l : controlLabels) {
            l.setForeground(Color.WHITE);
            controls.add(l);
        }
        
        controls.setPreferredSize(CONTROL_SIZE);

        controls.setBackground(Color.BLACK);
        controls.setForeground(Color.WHITE);
        final TitledBorder controlTitle = new TitledBorder("Controls: ");
        controlTitle.setTitleColor(Color.WHITE);
        controlTitle.setTitleJustification(TitledBorder.CENTER);
        controls.setBorder(controlTitle);
        
        
        final Box container = new Box(BoxLayout.PAGE_AXIS);
        container.add(myPreview);
        container.add(Box.createVerticalStrut(PADDING));
        container.add(info);
        container.add(controls);
        
        add(container);
        
    }
    
    /**
     * Creates the key control labels.
     *
     * @return the list of control labels
     */
    private List<JLabel> createControlLabels() {
        final List<JLabel> labels = new ArrayList<>();
     
     // extra spaces to force line wrap
        labels.add(new JLabel("        Move Left: A        ")); 
        
        labels.add(new JLabel("Move Right: D"));
        
        labels.add(new JLabel("Drop Piece 1 Block: S"));
        
        labels.add(new JLabel("Rotate Clockwise: E"));
        
        labels.add(new JLabel("Rotate Counter Clockwise: Q"));
        
        labels.add(new JLabel("Hard Drop Piece: Space"));
        
        labels.add(new JLabel("Pause Game: P"));
        
        return labels;
    }
    
    /**
     * Increment cleared lines, formula found on the tetris wiki.
     *
     * @param theLines the lines that were completed.
     */
    private void incrementClearedLines(final CompletedLines theLines) {
        myLinesCleared += theLines.getCompletedLines();
        
        if (myLinesCleared >= myLinesToLevel) {
            myLevel++;
            myLevelLabel.setText(LEVEL_STRING + myLevel);
            firePropertyChange("level up", null, null);
            myLinesToLevel += INITIAL_LINES_TO_LEVEL; 
        } 
        myLinesLabel.setText(MessageFormat.format(LINES_CLEARED_MESS_STRING,
                                                  myLinesCleared, myLinesToLevel));
        
    }
    
    /**
     * Calculate score.
     *
     * @param theLines the lines that were completed.
     */
    private void calculateScore(final CompletedLines theLines) {
        
        if (theLines != null) {
            switch (theLines.getCompletedLines()) {
                case 1: 
                    myScore += ONE_ROW_MULT * (myLevel + 1);
                    break;
                
                case 2:
                    myScore += TWO_ROW_MULT * (myLevel + 1);
                    break;
               
                case INITIAL_LINES_TO_LEVEL - 2: // evaluates to 3
                    myScore += THREE_ROW_MULT * (myLevel + 1);
                    break;
                
                case INITIAL_LINES_TO_LEVEL - 1: // evaluates to 4
                    myScore += FOUR_ROW_MULT * (myLevel + 1);
                    break;
                
                default:
                    myScore = myLevel * (myLinesCleared * myLevel);
                    break;
            }
        } 
        
        myScoreLabel.setText(SCORE_STRING + myScore);
    }
    
    /**
     * Resets score and all labels for a new game.
     */
    private void reset() {
        myLinesCleared = 0;
        myLinesToLevel = INITIAL_LINES_TO_LEVEL; 
        myScore = 0;
        myLevel = 1;
        myLinesLabel.setText(MessageFormat.format(LINES_CLEARED_MESS_STRING,
                                                  myLinesCleared, myLinesToLevel));
        myScoreLabel.setText(SCORE_STRING + myScore);
        myLevelLabel.setText(LEVEL_STRING + myLevel);
    }

    /**
     * Update method that recieves notifications from the Board class.
     *
     * @param theObserve the the observe
     * @param theObject the the object
     */
    @Override
    public void update(final Observable theObserve, final Object theObject) {
        
        if (theObject instanceof CompletedLines) {
            incrementClearedLines((CompletedLines) theObject);
            calculateScore((CompletedLines) theObject);
        } 
        
    }

    /** 
     * Property change listener for detecting new game commands.
     * 
     * @param theProp the fired property change.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theProp) {
        if ("new game".equals(theProp.getPropertyName())) {
            reset();
        }
        
    }
}
