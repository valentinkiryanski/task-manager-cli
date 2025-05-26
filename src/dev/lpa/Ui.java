package dev.lpa;

import java.sql.*;
import java.util.Scanner;

public class Ui {

    private SqlConnect connection;
    private MenuOptions menu;
    private Scanner scanner;



    public Ui(SqlConnect connection,MenuOptions menu,Scanner scanner){
        this.connection = connection;
        this.menu = menu;
        this.scanner = scanner;
    }


    public void start() {
        try(Connection conn = DriverManager.getConnection(connection.getUrl(),connection.getUser(),connection.getPassword())){
            PreparedStatement ps = conn.prepareStatement("INSERT INTO tasks(title) VALUES(?)");
            PreparedStatement psList = conn.prepareStatement("SELECT title FROM tasks WHERE title IS NOT NULL;");
            PreparedStatement updateTable = conn.prepareStatement("UPDATE tasks SET title = ? WHERE title = ?;");
            PreparedStatement deleteTask = conn.prepareStatement("DELETE FROM tasks WHERE title = ?");
            PreparedStatement setDone = conn.prepareStatement("UPDATE tasks SET is_completed = 1 WHERE title = ?;");
            PreparedStatement listTaskNotComplete = conn.prepareStatement("SELECT title FROM tasks WHERE is_completed = 0;");
            while (true) {
                menu.printOptions();

                int userInput = Integer.parseInt(scanner.nextLine());
                if (userInput == 1) {
                    System.out.println("Enter task name: ");
                    String taskName = scanner.nextLine();
                    if (taskName.length() > 50) {
                        System.out.println("Task too long to fit!");
                    } else {
                        ps.setString(1, taskName);

                        int rowsAffected = ps.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Task added successfully!");
                        } else {
                            System.out.println("Failed to add task.");
                        }
                    }
                } else if (userInput == 2) {
                    System.out.println("Tasks: ");
                    ResultSet result = psList.executeQuery();
                    while (result.next()) {
                        System.out.println(result.getString("title"));
                    }


                } else if (userInput == 3) {
                    System.out.println("Enter a task name to update: ");
                    String toUpdate = scanner.nextLine();
                    if (toUpdate.length() > 50) {
                        System.out.println("Task name too long! Try again!");
                        continue;
                    }
                    System.out.println("Enter the new task name: ");
                    String updatedTask = scanner.nextLine();
                    if (updatedTask.length() > 50) {
                        System.out.println("Task name too long! Try again!");
                        continue;
                    }
                    updateTable.setString(1, updatedTask);
                    updateTable.setString(2, toUpdate);
                    int rowsAffected = updateTable.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Task updated!");
                    } else {
                        System.out.println("Failed to update task!");
                    }
                } else if (userInput == 4) {
                    System.out.println("Which task to delete?");
                    String toDelete = scanner.nextLine();
                    if (toDelete.length() > 50) {
                        System.out.println("Task name too long! Try again!");
                        continue;
                    }
                    deleteTask.setString(1, toDelete);
                    int rowsAffected = deleteTask.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Task deleted successfully!");
                    } else {
                        System.out.printf("Deletion of task with name %s failed", toDelete);
                    }

                } else if (userInput == 5) {
                    System.out.println("Which task to be set as done?");
                    String task = scanner.nextLine();
                    setDone.setString(1, task);
                    int rowsAffected = setDone.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Task set as done!");
                    } else {
                        System.out.println("Failed to set task as done!");
                    }
                } else if (userInput == 6) {
                    System.out.println("Tasks to be completed: ");
                    ResultSet result = listTaskNotComplete.executeQuery();
                    while (result.next()) {
                        System.out.println(result.getString("title"));
                    }
                } else if (userInput == 7) {
                    System.out.println("Exiting...");
                    break;
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("There was an error connecting to the database. Please try again!");
        }

    }
}

