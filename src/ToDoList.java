import java.util.ArrayList;
import java.util.Date;

/**
 * A class for creating new tasks and storing them in ArrayList.
 * It is a part of a larger application for a todo list.
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
        System.out.println("Task successfully added to the list!");
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
    private int getTaskById(String userInputTaskID,ArrayList<Task>){

    }














}