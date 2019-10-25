package sda.ip.project;

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
        ToDoList tdl = new ToDoList();
        tdl.toDoList = readFromFileIntoList("toDoList");
        String title;
        String dueDateStr;
        Date dueDate = null;
        String taskIdStr;
        String project;
        int taskId;
        // Variable that represents users success to manipulate with a task( mark as done, remove task etc)
        boolean success;

        System.out.println("*** Welcome to ToDoList ***");
        Scanner userInput = new Scanner(System.in);

        // Manipulating the task menu with the user input through the choice variable.Options are from 1 to 6
        String choice = "0";
        while (!choice.equals("6")) {
            int completeTasks = tdl.getNumberOfCompleteTasks();
            int incompleteTasks = tdl.getNumberOfIncompleteTasks();
            printNoOfCompleteIncompleteTasks(completeTasks, incompleteTasks);
            printMenu();
            choice = userInput.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("List tasks by (D)ate or (P)roject?: ");
                    String printChoice = "X";
                    while (!printChoice.matches("D|P") ) {
                        printChoice = userInput.nextLine();
                        if (printChoice.matches("D|P")) {
                            break;
                        }
                        System.out.print("Please enter a valid choice: (D)ate or (P)roject. ");
                    }
                    tdl.printTaskList(printChoice);
                    pressEnterToContinue();
                    break;
                case "2":
                    System.out.print("Enter Title: ");
                    title = userInput.nextLine();
                    System.out.print("Enter due date in this format (e.g 2019-09-25): ");
                    dueDateStr = userInput.nextLine();
                    dueDate = convertStringToDate(dueDateStr);
                    // If user decides to press Q dueDate will be null and program returns to main menu
                    if (dueDate == null) {
                        break;
                    }
                    System.out.print("Enter Project: ");
                    project = userInput.nextLine();
                    tdl.addTask(title, dueDate, project);
                    System.out.println("Task successfully added to the list!");
                    pressEnterToContinue();
                    break;
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
                    System.out.print("Enter new due date in this format (e.g 2019-09-25) or press Enter to keep existing one: ");
                    dueDateStr = userInput.nextLine();
                    if (dueDateStr.isEmpty()) {
                        dueDate = null;
                    }
                    else {
                        dueDate = convertStringToDate(dueDateStr);
                    }
                    if (!dueDateStr.isEmpty() && dueDate == null) {
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
                    System.out.println("Please enter the Id number of the task you want to remove from list: ");
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
                    saveToFile(tdl.toDoList, "toDoList");
                    System.out.println();
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
    static boolean isValidDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        }
        catch (ParseException | NullPointerException pe) {
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
        catch(Exception ignored) {
        }
    }

    /** Turns String type into int
     *
     * @param s String type data
     * @return True if the value is int type
     */
    static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException nfe) {
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

     /**
      * Methods that prints the number of complete or incomplete tasks.
      * @param completeTasks Number of completed tasks
      * @param incompleteTasks Number of incomplete tasks
      */
     private static void printNoOfCompleteIncompleteTasks(int completeTasks, int incompleteTasks) {
        System.out.println();
        if (completeTasks + incompleteTasks != 0) {
            System.out.println("You have " + incompleteTasks + " incomplete and " + completeTasks + " complete tasks!");
        }
        else {
            System.out.println("To Do List is empty!");
        }
        System.out.println();
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();
    }

     /**
      * Converts the string date entry into a Date format
      * @param s Date in a form of string
      * @return The date in a Date format
      */

    public static Date convertStringToDate(String s) {
        boolean validDate = false;
        Date date = null;
        Scanner userInput = new Scanner(System.in);
        while (!validDate) {
            validDate = isValidDate(s);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            /* Can be ignored since we make sure the string is in a valid date format
             before this point */
            if (isValidDate(s)) {
                try {
                    date = dateFormat.parse(s);
                } catch (ParseException ignored) {
                }
            }
            else {
                System.out.print("Please enter date in a valid format (e.g. 2019-09-25) or (Q) "
                        + "to Quit and return to main menu: ");
                s = userInput.nextLine();
            }
            if (s.equals("Q")) {
                break;
            }
        }
        return date;
    }

}