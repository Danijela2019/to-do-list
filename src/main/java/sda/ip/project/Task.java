package sda.ip.project;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A class that is a template to create tasks for a simple to-do list.
 * It contains a constructor and get/set methods to access the instance fields from other classes.
 * It contains other methods that need access to the instance fields.
 * It is a part of larger application "to-do list" that has other classes.
 *
 * @author Danijela Milenkovic
 * @version 2019-10-07
 */

public class Task implements Serializable {
    // ID number for every task
    private int id;
    // Name/title for every task
    private String title;
    // Due date for every task
    private Date dueDate;
    // A project name where each task belongs to
    private String project;
    // Status done/not done for every task
    private String status;

    /**
     * Creates a new task
     *
     * @param id The task ID number
     * @param title The task name
     * @param dueDate  The final/ due date of a task
     * @param project The project name
     * @param status The tasks done/active status
     */
    public Task(int id, String title, Date dueDate, String project, String status) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.project = project;
        this.status = status;
    }

    public Task() {
    }

    /**
     * Access the instance field id
     *
     * @return The ID number of the concrete task
     */
    public int getId() {
        return this.id;
    }


    /**
     * Updates the ID number of a task
     *
     * @param id The new ID of the task
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     *  Access the title instance field
     *
     * @return The title of the concrete  task
     */
    public String getTitle() {
        return this.title;
    }



    /**
     * Updates the name/title of the concrete task
     *
     * @param title The initial name/title of the task
     */
    public void setTitle(String title) {
        this.title = title;
    }



    /**
     * Access the dueDate instance field
     *
     * @return The due date of the concrete task
     */
    public Date getDueDate() {
        return this.dueDate;
    }


    /**
     *  Updates the due date of the given task
     *
     * @param dueDate The final date for a project
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


    /**
     * Access the project instance field
     *
     * @return The name of the project
     */
    public String getProject() {
        return this.project;
    }


    /**
     * Updates the tittle/name of the project
     *
     * @param project The initial project name
     */
    public void setProject(String project) {
        this.project = project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *  Update the title, due date or project name of a certain task
     *
     * @param title The new name of the project
     * @param dueDate The new due date
     * @param project The new name of the project
     */
    public void updateTask (String title,Date dueDate, String project){
        this.title = title;
        this.dueDate = dueDate;
        this.project = project;
    }


    /**
     * Updates the task status to completed/done
     *
     */
    public void markAsDone() {
        this.status = "Done";
    }

    /**
     * Prints the task as a nice readable text
     * @return
     */
    @Override
    public String toString() {
        String dueDateStr = new SimpleDateFormat("yyyy-MM-dd").format(dueDate);
        return this.id + "|" + this.title + "|" + dueDateStr + "|" + this.project + "|" + this.status;
    }
}
