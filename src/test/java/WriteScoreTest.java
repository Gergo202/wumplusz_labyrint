import org.example.WriteScore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WriteScoreTest {

    @Test
    public void testWrite() {
        WriteScore writeScore =
                new WriteScore("Proba", 5000);
        String expected = "Username: 'Proba' Highscore: 1000";
        assertEquals(expected, writeScore.toString());
    }
}
