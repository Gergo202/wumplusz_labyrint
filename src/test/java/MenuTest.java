import org.example.Menu;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    @Test
    void showMenuTest() {

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            String input = "Test\n3\n4\n";
            ByteArrayInputStream in = new
                    ByteArrayInputStream(input.getBytes());
            System.setIn(in);

            ByteArrayOutputStream outContent =
                    new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            Menu menu = new Menu();
            menu.showMenu();

            String expectedOutput =
                    "Please input username: Starting game...Goodbye, Test!";
            assertEquals(expectedOutput.trim(), outContent.toString().trim());
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
