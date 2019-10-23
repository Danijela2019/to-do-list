package ipproject;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

/**Test class for Main
 * @version 2019-10-22
 * @author Danijela Milenkovic
 */
public class TestMain {



    @Test
    public void testIsValidDate() {
        // Act
        boolean isDate1 = Main.isValidDate("2019-25-25");
        boolean isDate2 = Main.isValidDate("2019-12-25");

        // Assert
        assertFalse(isDate1==true);
        assertTrue(isDate2==true);
    }
    @Test
    public void testIsInteger() {
        // Act
        boolean isInteger1 = Main.isInteger("A");
        boolean isInteger2 = Main.isInteger("2");


        //Assert
        assertFalse(isInteger1==true);
        assertTrue(isInteger2==true);
    }
}
