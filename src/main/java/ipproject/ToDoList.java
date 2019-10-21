package ipproject;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 * A class for creating new tasks and manipulating with the task list(To-do list)
 * Contains methods for manipulating with the tasks(the elements)of the ArrayList(To-do List)
 * Contains methods for manipulating with the ArrayList( To-do List) as a whole
 * It is a part of a larger application "to-do list".
 * @author Danijela Milenkovic
 * @version 2019-10-09
 */

public class ToDoList {
    // A list that stores the tasks created by the user
    ArrayList<Task> toDoList = new ArrayList<>();

    /**
     * Creating a new task
     *
     * @param title   Name/title of the task
     * @param dueDate Due date for that task
     * @param project Name of the project where the created task belong to
     */
    public void addTask(String title, Date dueDate, String project) {
        Task newTask = new Task(getNextTaskId(), title, dueDate, project, "Not Done");
        toDoList.add(newTask);
    }

    /**
     * Creating an ID for every new task
     *
     * @return Next available ID number
     */
    private int getNextTaskId() {
        int nextId = 1;
        for (Task t : toDoList) {
            if (t.getId() >= nextId) {
                nextId = t.getId() + 1;
            }
        }
        return nextId;
    }

    /**
     * Checks for existence of active (not done) task with a certain ID.
     * If the task is active, changes the status to done.
     * @param taskId ID number of the task
     * @return True if the task exists and is successfully updated as done
     */
    public boolean markTaskAsDone(int taskId) {
        Task t = getTaskById(taskId);
        if (t != null) {
            t.markAsDone();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Getting a certain task from the to-do list of tasks
     * @param taskId ID number of the task
     * @return the task that contains that ID number
     */
    public Task getTaskById(int taskId) {
        for (Task t : toDoList) {
            if (t.getId() == taskId) {
                return t;
            }
        }
        return null;
    }

    /**
     * Removing a task from the to-do list
     * @param taskId ID number of the task
     * @return True if the task exists in the first place and is successfully removed
     */
    public boolean removeTask(int taskId) {
        Task t = getTaskById(taskId);
        if (t != null) {
            toDoList.remove(t);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Prints the task list by project name or date(furthers date on the top/ soonest on the bottom)
     * @param sortChoice Users choice (D)ate or (P)roject
     */
    public void printTaskList(String sortChoice) {
        System.out.println("Id|Title|Due Date|Project|Status");
        sortList(sortChoice);
        for (Task t : toDoList) {
            System.out.println(t);
        }
    }

    /**
     * Sorts the list by project name or date
     * @param sortChoice Users choice (D)ate or (P)roject
     */
    private void sortList(String sortChoice) {
        if (sortChoice.equals("P")) {
            toDoList.sort(Comparator.comparing(Task::getProject));
        }
        else {
            toDoList.sort(Comparator.comparing(Task::getDueDate).reversed());
        }
    }

    /**
     * Updates a concrete task
     * @param taskId The ID of the task
     * @param title New tittle of the task
     * @param dueDate New due date of the task
     * @param project New project of the task
     */
    public void updateTask(int taskId, String title, Date dueDate, String project) {
        Task t = getTaskById(taskId);
        if (title.isEmpty()) { title = t.getTitle();}
        if (dueDate == null) { dueDate = t.getDueDate();}
        if (project.isEmpty()) {project = t.getProject();}
        t.updateTask(title, dueDate, project);
    }


}