import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        //Creating an instance of ToDoList task which will be used to store tasks and call methods
        ToDoList tdl = new ToDoList();
        //Variable for adding/editing a task title while creating/editing a task
        String title;
        // Variable for adding/editing a date in String format by the user when creating/editing a task
        String dueDateStr;
        //Variable that stores the converted String date format
        Date dueDate = null;
        //Variable for adding/editing the tasks project name
        String project;
        //Variable for searching for the ID of a certain task
        String taskIdStr;
        int taskId;

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
                    System.out.print("Enter Title: ");
                    title = userInput.nextLine();
                    // Instructions for the user how to enter the date in the correct format
                    System.out.print("Enter due date in this format (e.g 2019-09-25): ");
                    dueDateStr = userInput.nextLine();

                    // Making sure that the user entry is a valid date before continuing to the next step
                    boolean validDate = false;
                    while (!validDate) {
                        validDate = isValidDate(dueDateStr);
                        // formatting the date from String type to Date type
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        /* Created try/catch statement.
                        Can be ignored since we make sure the string is in a valid date format before this point */
                        if (isValidDate(dueDateStr)) {
                            try {
                                dueDate = dateFormat.parse(dueDateStr);
                            } catch (ParseException pe) {
                            }
                        }
                        // creating an option for the user to exit and return to the main menu
                        else {
                            System.out.print("Please enter date in a valid format (e.g. 2019-09-25) or (Q) "
                                    + "to Quit and return to main menu: ");
                            dueDateStr = userInput.nextLine();
                        }
                        if (dueDateStr.equals("Q")) {
                            break;
                        }
                        // exiting the add a new task option in case the user decides not to proceed with the entry
                    }
                    if (dueDateStr.equals("Q")) {
                        break;
                    }
                    //Creating the next entry(project name) for the user after he/she enters a valid date format
                    System.out.print("Enter Project: ");
                    project = userInput.nextLine();

                    // Adding the newly created task in the ArrayList
                    tdl.addTask(title, dueDate, project);
                    System.out.println("Task successfully added to the list!");
                    //System.out.println("To do list has " + tdl.toDoList.size() + " tasks.");
                    // Returning the user to the main menu again
                    pressEnterToContinue();
                    break;
                // Editing a task
                case "3":
                    System.out.println("Please enter the Id number of the task you want to edit: ");
                    break;
                case "4":
                    System.out.println("Please enter the Id number of the task you want to mark as done: ");
                    boolean success = false;
                    while (!success) {
                        taskIdStr = userInput.nextLine();
                        try {
                            taskId = Integer.parseInt(taskIdStr);
                            success = tdl.markTaskAsDone(taskId);
                        }
                        catch (NumberFormatException nfe) {
                            success = false;
                        }
                        if (success) {
                            System.out.println("Task successfully marked as done!");
                        } else {
                            System.out.println("Please enter existing task id: ");
                        }
                    }
                    pressEnterToContinue();
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
    /**
     * Checking if the users date entry is in valid format
     *
     */
    private static boolean isValidDate (String datum){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(datum.trim());
        }
        catch (ParseException pe) {
            return false;
        }
        catch (NullPointerException pe) {
            return false;
        }
        return true;
    }

    /**
     * Returning the user to the main menu
     *
     */
    private static void pressEnterToContinue() {
        System.out.println();
        System.out.println("Press Enter/Return key to continue...");
        try {
            System.in.read();
        }
        catch(Exception e) {
        }
    }


}