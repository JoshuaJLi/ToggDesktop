package ca.joshuajli.togglrdesktop;

import ch.simas.jtoggl.JToggl;
import ch.simas.jtoggl.TimeEntry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {
    private JToggl toggl = null;

    public Test() {
        try {
            toggl = new JToggl(Files.readString(Paths.get("ApiKey.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test() {
        startEntry();
        getProjectInfo();
    }

    private void getProjectInfo() {
        System.out.println(toggl.getProjects());
    }

    private void startEntry() {
        checkRunning();

        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setDescription("Does this work?");
        timeEntry.setCreated_with("TogglrTest");
        timeEntry.setPid(178208313L);

        toggl.startTimeEntry(timeEntry);
    }

    private void checkRunning() {
        TimeEntry running = toggl.getCurrentTimeEntry();
        if (running != null) {
            toggl.stopTimeEntry(running);
        }
    }
}
