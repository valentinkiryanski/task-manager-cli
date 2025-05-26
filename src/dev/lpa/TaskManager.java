package dev.lpa;

import java.sql.SQLException;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        Ui theUi = new Ui(new SqlConnect(),new MenuOptions(),new Scanner(System.in));

        theUi.start();
    }
}

