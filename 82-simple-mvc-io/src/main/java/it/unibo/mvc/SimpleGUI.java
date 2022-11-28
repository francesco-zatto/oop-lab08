package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 3;
    private static final String BUTTON_TEXT = "Save";
    private final JFrame frame = new JFrame();
    private final JPanel panel;
    private final JTextArea textArea;
    private final JButton button;
    private final Controller controller = new Controller();

    public SimpleGUI() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.textArea = new JTextArea();
        this.button = new JButton(BUTTON_TEXT);
        this.panel.add(this.textArea, BorderLayout.CENTER);
        this.panel.add(this.button, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.panel);
        this.button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    controller.writeOnCurrentFile(textArea.getText());
                    textArea.setText("");
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
        final var myGUI = new SimpleGUI();
        myGUI.display();
    }

}
