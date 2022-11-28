package it.unibo.mvc;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 3;
    private static final String PRINT_TEXT = "Print";
    private static final String HISTORY_TEXT = "Show history";
    private final JFrame frame = new JFrame();

    private SimpleGUI(final SimpleController ctrl) {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final var panel = new JPanel(new BorderLayout());
        final var buttonsPanel = new JPanel(new FlowLayout());
        final var txtField = new JTextField();
        final var txtArea = new JTextArea();
        txtArea.setEditable(false);
        final var printButton = new JButton(PRINT_TEXT);
        final var historyButton = new JButton(HISTORY_TEXT);
        buttonsPanel.add(printButton);
        buttonsPanel.add(historyButton);
        panel.add(txtField, BorderLayout.NORTH);
        panel.add(txtArea, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);
        this.frame.add(panel);
        printButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent ae) {
                ctrl.setNextString(txtField.getText());
                ctrl.print();
            }
            
        });
        historyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent ae) {
                ctrl.getHistory().stream()
                        .map(s -> s + "\n")
                        .forEach(s -> txtArea.append(s));
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
        final var myGUI = new SimpleGUI(new SimpleController());
        myGUI.display();
    }

}
