/*
 * TCSS 305 - Assignment 6
 * 
 * Menu bar for assignment 6 - Tetris
 */


package view;

import actions.ControlAction;
import actions.ScoringAction;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import model.Board.GameStatus;


/**
 * The Menu Class for Tetris.
 * 
 * @author Josh Rueschenberg
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TetrisMenu extends JMenuBar implements Observer {

    /** My new game menu button. */
    private JMenuItem myNewGame;
    
    /** My game was ended. */
    private boolean myGameEnded;
    
    /**
     * Instantiates a new tetris menu.
     */
    public TetrisMenu() {
        super();
        myGameEnded = false;
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        add(createGame());
        add(createOptions());
        add(createFeatures());
        add(createHelp());

    }
    
    /**
     * Creates the game menu.
     *
     * @return the j menu
     */
    private JMenu createGame() {
        final JMenu game = new JMenu("Game");
        game.setMnemonic(KeyEvent.VK_G);
        game.setForeground(Color.WHITE);
        
        final JMenuItem pause = new JMenuItem("Pause");
        pause.setMnemonic(KeyEvent.VK_P);
        
        final JMenuItem endGame = new JMenuItem("End Game");
        endGame.setMnemonic(KeyEvent.VK_E);
        
        myNewGame = new JMenuItem("New Game");
        myNewGame.setMnemonic(KeyEvent.VK_N);
        myNewGame.setEnabled(false);
        
        final JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_X);
        
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("pause", null, null);
            }
        });
        
        endGame.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("end game", null, null);
                myGameEnded = true;
                toggleNewGame();
            }
        });
        
        myNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("new game", null, null);
                myGameEnded = false;
                toggleNewGame();
            }
        });
        
        exit.addActionListener(new ActionListener() {
            
            /** 
             * Fires a property change when the exit button is pressed.
             * 
             * @param theEvent the action event
             */
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("exit", null, null);
            }
        });
        
        game.add(pause);
        game.addSeparator();
        game.add(endGame);
        game.add(myNewGame);
        game.addSeparator();
        game.add(exit);
        
        for (final Component c : game.getMenuComponents()) {
            c.setBackground(Color.BLACK);
            c.setForeground(Color.WHITE);
        }
        
        return game;
        
    }
    
    /**
     * Creates the options menu.
     *
     * @return the j menu
     */
    private JMenu createOptions() {
        final JMenu options = new JMenu("Options");
        options.setForeground(Color.WHITE);
        options.setMnemonic(KeyEvent.VK_O);
        
        final ButtonGroup group = new ButtonGroup();
        
        final JMenu size = new JMenu("Size");
        
        size.setMnemonic(KeyEvent.VK_S);
        size.setOpaque(true);
        
        final JRadioButtonMenuItem small = new JRadioButtonMenuItem("Small (200 x 400)");
        
        final JRadioButtonMenuItem med = new JRadioButtonMenuItem("Medium (300 x 600)");
        
        final JRadioButtonMenuItem large = new JRadioButtonMenuItem("Large (400 x 800)");
        
        med.setSelected(true);
        group.add(small);
        group.add(med);
        group.add(large);
        
        small.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("small", null, null);
            }
        });
        
        med.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("medium", null, null);
            }
        });
        
        large.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("large", null, null);
            }
        });
        
        size.add(small);
        size.add(med);
        size.add(large);
        
        final JCheckBoxMenuItem grid = new JCheckBoxMenuItem("Grid    ");
        grid.setMnemonic(KeyEvent.VK_G);
        
        grid.addItemListener(new ItemListener() {

            /** 
             * Enables or disables the grid when the box is checked or unchecked.
             * 
             * @param theEvent the item event
             */
            public void itemStateChanged(final ItemEvent theEvent) {
                firePropertyChange("grid", null, null);
            }
        });
        
        options.add(size);
        options.add(grid);
        
        for (final Component c : size.getMenuComponents()) {
            c.setBackground(Color.BLACK);
            c.setForeground(Color.WHITE);
        }
        
        for (final Component c : options.getMenuComponents()) {
            c.setBackground(Color.BLACK);
            c.setForeground(Color.WHITE);
        }
       
        return options;
    }
    
    /**
     * Creates the features menu.
     *
     * @return the features j menu
     */
    private JMenu createFeatures() {
        final JMenu features = new JMenu("Features");
        features.setForeground(Color.WHITE);
        features.setMnemonic(KeyEvent.VK_F);
        
        final JCheckBoxMenuItem rave = new JCheckBoxMenuItem("Rave Mode");
        rave.setMnemonic(KeyEvent.VK_R);
        
        final JCheckBoxMenuItem music = new JCheckBoxMenuItem("Music");
        music.setMnemonic(KeyEvent.VK_M);
        music.setSelected(true);
        
        rave.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent theEvent) {
                firePropertyChange("rave", null, null);
            }
        });
        
        music.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent theEvent) {
                firePropertyChange("music", null, null);
            }
        });
        
        
        features.add(rave);
        features.add(music);
        
        
        for (final Component c : features.getMenuComponents()) {
            c.setBackground(Color.BLACK);
            c.setForeground(Color.WHITE);
        }
        
        return features;
    }
    
    /**
     * Creates the help menu.
     *
     * @return the help j menu
     */
    private JMenu createHelp() {
        final JMenu help = new JMenu("Help");
        help.setForeground(Color.WHITE);
        help.setMnemonic(KeyEvent.VK_H);
        
        help.add(new ControlAction());
        help.add(new ScoringAction());
        
        for (final Component c : help.getMenuComponents()) {
            c.setBackground(Color.BLACK);
            c.setForeground(Color.WHITE);
        }
        
        return help;
    }
    
    /**
     * Toggles the new game on or off if the current game has been ended.
     */
    private void toggleNewGame() {
        if (myGameEnded) {
            myNewGame.setEnabled(true);
        } else {
            myNewGame.setEnabled(false);
        }
        
    }

    /** 
     * Observer method to handle enabling/disabling the new game button. 
     * 
     * @param theObserve the observable source object
     * @param theObject the object being passed from observable class
     */
    @Override
    public void update(final Observable theObserve, final Object theObject) {        
        
        if (theObject instanceof GameStatus && ((GameStatus) theObject).isGameOver()) {
            myGameEnded = true;
            toggleNewGame();
        }

    }
    
    
}
