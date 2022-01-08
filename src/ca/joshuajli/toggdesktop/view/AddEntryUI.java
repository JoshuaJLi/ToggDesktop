package ca.joshuajli.toggdesktop.view;

import ca.joshuajli.toggdesktop.controller.FavController;
import ch.simas.jtoggl.Project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddEntryUI extends JDialog {
    private final Dimension MIN_SIZE = new Dimension(500, 350);
    private final Dimension MAX_SIZE = new Dimension(1000, 350);

    private final FavController favEntries = FavController.getInstance();
    private final List<Project> projects = favEntries.getProjects();

    private JComboBox<String> projectComboBox;

    AddEntryUI() {
        setUpComboBox();
        configWindow();
    }

    private void configWindow() {
        setTitle("Add a new Entry");

        setMinimumSize(MIN_SIZE);
        setMaximumSize(MAX_SIZE);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setUpComboBox() {
        ArrayList<String> projectNames = new ArrayList<>();
        for (Project project : projects) {
            projectNames.add(project.getName());
        }
        String[] projectArray = new String[projectNames.size()];

        projectArray = projectNames.toArray(projectArray);

        projectComboBox = new JComboBox<>(projectArray);
        add(projectComboBox);
    }
}
