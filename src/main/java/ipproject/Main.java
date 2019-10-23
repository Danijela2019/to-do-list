package ipproject;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *  A class that contains the main method.
 *  It used to print and navigate through the to-do list option menu.
 *  It contains methods that are directly used for manipulating with the user data entry.
 *  It contains methods to save and read an already saved to-do list.
 *  It is a part of a larger "to-do" list application.
 *
 * @author Danijela Milenkovic
 * @version 2019-10-09
 *
 */

public class Main {

    public static void main(String[] args) {
        //Creating an instance of ToDoList task which will be used to store tasks and call methods
        ToDoList tdl = new ToDoList();
        tdl.toDoList = readFromFileIntoList("toDoList");
        //Variable for adding/editing a task title while creating/editing a task
        String title;
        // Variable for adding/editing a date in String format by the user when creating/editing a task
        String dueDateStr;
        //Variable that stores the converted String date format
        Date dueDate = null;
        //Variable for adding/editing the tasks project name
        String project;
        //Variable that the user inputs as task ID before converting it  into an int type
        String taskIdStr;
        // Variable used when the String type user input ID is converted into int
        int taskId;
        // Variable that represents users success to manipulate with a task( mark as done, remove task etc)
        boolean success;
        // Variable that confirms if the user entered a valid date format
        boolean validDate;

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
                //first option(choice),filter and print the list by date or project
                case "1":
                    System.out.print("List tasks by (D)ate or (P)roject?: ");
                   /*Creating a local variable printChoise set to initial value different
                   from D or P in order to enter the while loop */
                    String printChoice = "X";
                    while (!printChoice.matches("D|P") ) {
                        printChoice = userInput.nextLine();
                        //making sure that the user enters correct input D or P and exit the while loop
                        if (printChoice.matches("D|P")) {
                            break;
                        }
                        System.out.print("Please enter a valid choice: (D)ate or (P)roject. ");
                    }
                    //Printing the tasks by (D)ate or (P)roject depending on users choice(printChoice)
                    tdl.printTaskList(printChoice);
                    pressEnterToContinue();
                    break;
                //second option/choice is creating a new task by entering task name( title) and due date
                case "2":
                    System.out.print("Enter Title: ");
                    title = userInput.nextLine();
                    // Instructions for the user how to enter the date in the correct format
                    System.out.print("Enter due date in this format (e.g 2019-09-25): ");
                    dueDateStr = userInput.nextLine();

                    // Making sure that the user entry is a valid date before continuing to the next step
                    validDate = false;
                    while (!validDate) {
                        validDate = isValidDate(dueDateStr);
                        // Formatting the date from String type to Date type
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        /* Created try/catch statement.
                        Can be ignored since we make sure the string is in a valid date format before this point */
                        if (isValidDate(dueDateStr)) {
                            try {
                                dueDate = dateFormat.parse(dueDateStr);
                            } catch (ParseException pe) {
                            }
                        }
                        // Creating an option for the user to exit and return to the main menu
                        else {
                            System.out.print("Please enter date in a valid format (e.g. 2019-09-25) or (Q) "
                                    + "to Quit and return to main menu: ");
                            dueDateStr = userInput.nextLine();
                        }
                        if (dueDateStr.equals("Q")) {
                            break;
                        }
                        // Exiting the add a new task option in case the user decides not to proceed with the entry
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
                    // Returning the user to the main menu again
                    pressEnterToContinue();
                    break;
                // Editing a task
                case "3":
                    System.out.println("Please enter the Id number of the task you want to edit: ");
                    // Created a variable that represents the users success to edit the task.
                    boolean isValidTaskId = false;
                    taskId = 0;
                    taskIdStr = "";
                    while (!isValidTaskId) {
                        taskIdStr = userInput.nextLine();
                        if (taskIdStr.equals("Q")) {
                            break;
                        }
                        isValidTaskId = isInteger(taskIdStr);
                        if (isValidTaskId) {
                            try {
                                taskId = Integer.parseInt(taskIdStr);
                                if (tdl.getTaskById(taskId) == null) {
                                    isValidTaskId = false;
                                    System.out.print("Please enter existing Id number of the task or (Q) to return to main menu: ");
                                }
                            } catch (NumberFormatException nfe) {
                                //no need to handle error because we already made sure taskIdStr can be converted to integer
                            }
                        } else {
                            System.out.print("Please enter existing Id number of the task or (Q) to return to main menu: ");
                        }
                    }
                    if (taskIdStr.equals("Q")) {
                        break;
                    }
                    System.out.print("Enter new Title or press Enter to keep existing one: ");
                    title = userInput.nextLine();
                    // Instructions for the user how to enter the date in the correct format
                    System.out.print("Enter new due date in this format (e.g 2019-09-25) or press Enter to keep existing one: ");
                    dueDateStr = userInput.nextLine();

                    // Making sure that the user entry is a valid date before continuing to the next step
                    validDate = false;
                    while (!validDate) {
                        validDate = isValidDate(dueDateStr);
                        // Formatting the date from String type to Date type
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        /* Created try/catch statement.
                        Can be ignored since we make sure the string is in a valid date format before this point */
                        if (isValidDate(dueDateStr)) {
                            try {
                                dueDate = dateFormat.parse(dueDateStr);
                            } catch (ParseException pe) {
                            }
                        }
                        else if (dueDateStr.isEmpty()) {
                            dueDate = null;
                            validDate = true;
                        }
                        // Creating an option for the user to exit and return to the main menu
                        else {
                            System.out.print("Please enter date in a valid format (e.g. 2019-09-25) or (Q) "
                                    + "to Quit and return to main menu: ");
                            dueDateStr = userInput.nextLine();
                        }
                        if (dueDateStr.equals("Q")) {
                            break;
                        }
                        // Exiting the add a new task option in case the user decides not to proceed with the entry
                    }
                    if (dueDateStr.equals("Q")) {
                        break;
                    }
                    System.out.print("Enter new Project or press Enter to keep existing one: ");
                    project = userInput.nextLine();

                    if (title.isEmpty() && dueDateStr.isEmpty() && project.isEmpty()) {
                        System.out.print("No changes were made!");
                        System.out.println();
                    }
                    else {
                        tdl.updateTask(taskId, title, dueDate, project);
                        System.out.println("Task successfully updated!");
                        System.out.println();
                    }
                    pressEnterToContinue();
                    break;
                 // Marking a task as done
                case "4":
                    System.out.println("Please enter the Id number of the task you want to mark as done: ");
                    // Created a variable that represents the users success to update the task as done
                    success = false;
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
                    // Removing a task from the list
                    System.out.println("Please enter the Id number of the task you want to remove from list: ");
                    // Created a variable that represents the users success to remove a task with a certain ID
                    success = false;
                    while (!success) {
                        taskIdStr = userInput.nextLine();
                        if (taskIdStr.equals("Q")) {
                            break;
                        }
                        try {
                            taskId = Integer.parseInt(taskIdStr);
                            success = tdl.removeTask(taskId);
                        }
                        catch (NumberFormatException nfe) {
                            success = false;
                        }
                        if (success) {
                            System.out.println("Task successfully removed!");
                        } else {
                            System.out.println("Please enter existing task id or (Q) to Quit and return to main menu: ");
                        }
                    }
                    pressEnterToContinue();
                    break;
                case "6":
                    // Saving changes to file and printing a good bye message
                    saveToFile(tdl.toDoList, "toDoList");
                    System.out.println();
                    System.out.println("Bye bye!");
                    break;
                    // in case the user enters an option that is not valid
                default:
                    System.out.println("Please enter a valid option!");
                    System.out.println();
            }
        }
    }

    //method for counting
    //tasklist.filter(x -> x.isDone()).count()

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
    public static boolean isValidDate (String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
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
     * Asking the user to press enter in order to move on with program execution
     *
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

    /** Turns String type into int
     *
     * @param s String type data
     * @return True if the value is int type
     */

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException nfe) {
            return false;
        } catch(NullPointerException npe) {
            return false;
        }
        return true;
    }

    /**
     * Saves the ArrayList( to-do list) to a file
     * @param list The ArrayList that is saved it  the file
     * @param fileName The name of the file where the ArrayList( To-do List) is saved
     */

    private static void saveToFile(ArrayList list, String fileName) {
        try
        {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(list);

            oos.close();
            fos.close();

            System.out.println("Data saved!");
        }
        catch (IOException ioe)
        {
            System.out.println("Failed to save data!!!");
        }
    }

    /**
     * Reads from the file where the ArrayList( to-do List) is saved and shows the saved list
     * @param fileName the name of the file where the ArrayList(To-do list) is saved
     * @return ArrayList( the saved To-do List in that file)
     */
    private static ArrayList readFromFileIntoList(String fileName) {
        ArrayList list = new ArrayList();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            list = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            //ignore, it's OK to return empty list
        }
        return list;
    }


}