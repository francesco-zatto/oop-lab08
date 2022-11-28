package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser { 
    private static final int PROPORTION = 3;
    private static final String SAVE_TEXT = "Save";
    private static final String BROWSE_TEXT = "Browse...";
    private final JFrame frame = new JFrame();
    private final JPanel panel;
    private final JPanel northPanel;
    private final JTextArea textToWrite;
    private final JButton saveButton;
    private final JTextField currentFile;
    private final JButton browseButton;
    private final Controller controller = new Controller();

    public SimpleGUIWithFileChooser() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.northPanel = new JPanel();
        this.northPanel.setLayout(new BorderLayout());
        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.currentFile = new JTextField(controller.getPath());
        this.currentFile.setEditable(false);
        this.browseButton = new JButton(BROWSE_TEXT);
        this.northPanel.add(this.currentFile, BorderLayout.CENTER);
        this.northPanel.add(this.browseButton, BorderLayout.EAST);
        this.textToWrite = new JTextArea();
        this.saveButton = new JButton(SAVE_TEXT);
        this.panel.add(this.northPanel, BorderLayout.NORTH);
        this.panel.add(this.textToWrite, BorderLayout.CENTER);
        this.panel.add(this.saveButton, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.panel);
        this.browseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent ae) {
                final var fChooser = new JFileChooser();
                if (fChooser.showOpenDialog(browseButton) == JFileChooser.APPROVE_OPTION) {
                    controller.setCurrentFile(fChooser.getSelectedFile());
                    currentFile.setText(controller.getPath());
                } else {
                    JOptionPane.showMessageDialog(browseButton, ae);
                }      
            }
        });
        this.saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    controller.writeOnCurrentFile(textToWrite.getText());
                    textToWrite.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }                
            }
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        this.frame.setLocationByPlatform(true);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        final var myGUI = new SimpleGUIWithFileChooser();
        myGUI.display();
    }

}
