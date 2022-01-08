package ca.joshuajli.toggdesktop.view;

import ca.joshuajli.toggdesktop.controller.FavController;
import ca.joshuajli.toggdesktop.controller.PlainTextSaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;

public class MainUI implements WindowFocusListener {
    private final Dimension MAIN_FRAME_MIN_SIZE = new Dimension(400, 600);

    private final FavController favEntries = FavController.getInstance();

    private final JFrame mainFrame = new JFrame("Togg Desktop");

    public MainUI() {

        getProjects();
        setUpWindow();
        setUpAddButton();
    }

    private void getProjects() {
    }

    private void setUpAddButton() {
        JButton addButton = new JButton("Add New Entry");
        addButton.addActionListener(e -> new AddEntryUI());

        mainFrame.add(addButton);
    }

    private void setUpWindow() {
        mainFrame.setMinimumSize(MAIN_FRAME_MIN_SIZE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.addWindowFocusListener(this);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                PlainTextSaver.save(favEntries.saveEntries());
                super.windowClosing(e);
            }
        });
    }



    @Override
    public void windowGainedFocus(WindowEvent e) {

    }

    @Override
    public void windowLostFocus(WindowEvent e) {

    }
}
