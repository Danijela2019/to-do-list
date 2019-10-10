import java.util.Scanner;

/**
 *  A class that contains the main method.
 *  It used to print and navigate through the todo list option menu.
 * It is a part of a larger todo list application.
 *
 * @author Danijela Milenkovic
 * @version 09.10.2019
 *
 */

public class Main {

    public static void main(String[] args) {
        // Print a welcome message
        System.out.println("*** Welcome to ToDoList ***");

        // Create a scanner so we can read the command-line input
        Scanner userInput = new Scanner(System.in);

        // Manipulating the task menu with the user input through the choice variable.Options are from 1 to 6
        String choice = "0";
        while (!choice.equals("6")) {
            printMenu();
            choice = userInput.nextLine();
            switch (choice) {
                //first option(choice),show the task list
                case "1":
                    System.out.println("Showing task list.....");
                    break;
                //second option/choice is creating a new task by entering task name( title) and due date
                case "2":
                    System.out.println("Adding new task to the list.....");
                    break;
                // Editing a task
                case "3":
                    System.out.println("Editing task...");
                    // user enters the task Id in string format
                    break;
                case "4":
                    System.out.println("Completing task.....");

                    break;
                case "5":
                    System.out.println("Removing task form the list......");
                    break;
                case "6":
                    System.out.println("Bye bye!");
                    break;
                default:
                    System.out.println("Please enter a valid option!");
                    System.out.println();
            }
        }
    }
    /**
     * Showing the menu options to the user
     *
     */
    private static void printMenu () {
        System.out.println("Pick an option:");
        System.out.println("1 - Show Task List (by Date or Project)");
        System.out.println("2 - Add New Task");
        System.out.println("3 - Edit Task");
        System.out.println("4 - Mark Task as Done");
        System.out.println("5 - Remove Task from Task List");
        System.out.println("6 - Save and Exit the Program");
        System.out.println("Your choice: ");
    }

}