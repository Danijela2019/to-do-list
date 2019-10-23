package ipproject;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**Test class for ToDoList
 * @version 2019-10-22
 * @author Danijela Milenkovic
 */
public class TestToDoList {
    //creating an instance of the ToDoList for testing
    ToDoList tdl = new ToDoList();

    @Test
    public void testAddTask() {
        // Act
        tdl.addTask("t1", parseStringToDate("2019-12-15"), "p1");

        // Assert
        assertEquals(1, tdl.toDoList.size());
        assertNotEquals(0, tdl.toDoList.size());
    }

    @Test
    public void testRemoveTask() {
        // Act
        tdl.addTask("t1", parseStringToDate("2019-12-15"), "p1");
        tdl.addTask("t2", parseStringToDate("2011-11-15"), "p2");

        tdl.removeTask(2);

        // Assert
        assertEquals(1, tdl.toDoList.size());
        assertFalse(tdl.toDoList.size() == 100);
    }

    @Test
    public void testRemoveTaskOnEmptyList() {
        // Act
        boolean success = tdl.removeTask(1);

        // Assert
        assertFalse(success);

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
