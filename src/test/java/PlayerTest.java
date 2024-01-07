import org.example.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player player = new Player();

    @Test
    public void testLookAndMove() {
        char[][] mapContent = {
                {'_', '_', '_', '_'},
                {'_', '*', '_', '_'},
                {'_', 'O', '_', '_'},
                {'_', '_', '_', '_'}
        };

        assertEquals('→', player.move('→', "w",
                mapContent, 0, 0));

        assertEquals('→', player.move('→', "w",
                mapContent, 0, 0));

        assertEquals('→', player.move('→', "s",
                mapContent, 0, 0));

        assertEquals('→', player.move('→', "a",
                mapContent, 0, 0));

        assertEquals('↓', player.move('→', "d",
                mapContent, 0, 0));

        assertEquals('↓', player.move('↓', "d",
                mapContent, 3, 3));

        assertEquals('←', player.move('↓', "w",
                mapContent, 3, 3));

        assertEquals('←', player.move('←', "a",
                mapContent, 2, 1));

        assertEquals('←', player.move('←', "a",
                mapContent, 1, 1));

        assertEquals(1, player.getHeroX());
        assertEquals(1, player.getHeroY());
    }

    @Test
    public void testIsValidPosition() {
        Player player = new Player();

        char[][] mapContent = {
                {'_', '_', '_', '_'},
                {'_', '*', '_', '_'},
                {'_', 'O', '_', '_'},
                {'_', '_', '_', '_'}
        };

        assertTrue(player.isValidPosition(0, 0, mapContent));

        assertFalse(player.isValidPosition(1, 1, mapContent));

        assertFalse(player.isValidPosition(2, 1, mapContent));

        assertFalse(player.isValidPosition(5, 5, mapContent));
    }
}
