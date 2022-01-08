package ca.joshuajli.toggdesktop;

import ca.joshuajli.toggdesktop.view.MainUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        System.out.print("Hello World!");
//        Test test = new Test();
//        test.test();

        SwingUtilities.invokeLater(MainUI::new);
    }


}
