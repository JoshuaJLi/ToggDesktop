package ca.joshuajli.toggdesktop.controller;

import ch.simas.jtoggl.JToggl;
import ch.simas.jtoggl.Project;
import ch.simas.jtoggl.TimeEntry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FavController {
    private final ArrayList<TimeEntry> favEntries = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

    private static FavController instance;

    private JToggl toggl;

    public static FavController getInstance() {
        if (instance == null) {
            instance = new FavController();
        }
        return instance;
    }

    private FavController() {
        getFavs();
        authAccount();
        loadProjects();
        getFavs();
    }

    private void getFavs() {
        try {
            stringToList(PlainTextSaver.load());
        } catch (IOException e) {
            System.out.println("No File Found, loading empty entries");
        }
    }

    private void stringToList(String favStrings) {
        if (favStrings == null) {
            return;
        }

        favEntries.clear();
        Scanner scanner = new Scanner(favStrings);
        while (scanner.hasNextLine()) {
            favEntries.add(new TimeEntry(scanner.nextLine()));
        }
    }


    private void loadProjects() {
        projects = toggl.getProjects();
    }

    private void authAccount() {
        try {
            toggl = new JToggl(Files.readString(Paths.get("ApiKey.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String saveEntries() {
        StringBuilder jsonFavs = new StringBuilder();
        for (TimeEntry entry : favEntries) {
            jsonFavs.append(entry.toJSONString());
        }

        return jsonFavs.toString();
    }

    public void addEntry(TimeEntry entry) {
        favEntries.add(entry);
    }

    public void addEntry(String desc, Long projId) {
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setCreated_with("ToggDesk");

        timeEntry.setDescription(desc);
        timeEntry.setPid(projId);

        favEntries.add(timeEntry);
    }

    public List<Project> getProjects() {
        return projects;
    }
}
