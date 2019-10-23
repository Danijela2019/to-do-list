package ipproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Test class for Task
 * @version 2019-10-17
 * @author Danijela Milenkovic
 */

public class TestTask {
    Task testTask1 = new Task();
    Task testTask2 =  new Task();
    // setting the environment for testing
    @Before
    public void setUp() {
        testTask1.setId(1);
        testTask1.setTitle("t1");
        testTask1.setDueDate(parseStringToDate("2020-01-01"));
        testTask1.setProject("p1");
        testTask1.setStatus("Not Done");

        testTask2.setId(2);
        testTask2.setTitle("t2");
        testTask2.setDueDate(parseStringToDate("2001-01-01"));
        testTask2.setProject("p2");
        testTask2.setStatus("Done");
    }

    @Test
    public void testGetId() {
        // Assert
        assertEquals(1, testTask1.getId());
        assertTrue(testTask2.getId()==2);
        assertFalse(testTask2.getId()==3);
    }

    @Test
    public void testUpdateTask() {
        // Act
        testTask2.updateTask("tu2", parseStringToDate("2001-01-01"), "pu3");

        // Assert
        assertEquals("tu2", testTask2.getTitle());
        assertTrue(testTask2.getProject().equals("pu3"));
        assertFalse(testTask2.getProject().equals("p5"));
    }
    // creating a method to parse the date when we create new instances
    public Date parseStringToDate(String s) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dueDate = null;
        try {
            dueDate = sdf.parse(s);
        } catch (ParseException pe) {
        }
        return dueDate;
    }
}
