package ui;

import model.EventLog;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new FangInputTest();
        } catch (FileNotFoundException e) {
            System.out.println("Error: JSON file not found.");
        }
    }
}
