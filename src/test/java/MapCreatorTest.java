import org.example.MapCreator;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class MapCreatorTest {
    private void assertArrayEquals(char[][] expected, char[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, actual[i].length);
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], actual[i][j]);
            }
        }
    }

    @Test
    public void testSaveMapToFile() throws IOException {
        char[][] map = {
                {'W', 'W', 'W', 'W', 'W'},
                {'W', ' ', 'H', ' ', 'W'},
                {'W', ' ', 'P', ' ', 'W'},
                {'W', ' ', ' ', ' ', 'W'},
                {'W', 'W', 'W', 'W', 'W'}
        };
        String filename = "test_map";

        MapCreator.saveMapToFile(map, filename);
        String content = readContentFromFile("saved_maps/" + filename + ".txt");

        String expectedContent =
                "WWWWW\n" +
                "W H W\n" +
                "W P W\n" +
                "W   W\n" +
                "WWWWW\n";

        assertEquals(expectedContent, content);
    }

    private String readContentFromFile(String filepath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    @Test
    public void testInitMap() {
        int mapSize = 5;

        char[][] map = MapCreator.initMap(mapSize);

        char[][] expectedMap = {
            {'W', 'W', 'W', 'W', 'W'},
            {'W', ' ', ' ', ' ', 'W'},
            {'W', ' ', ' ', ' ', 'W'},
            {'W', ' ', ' ', ' ', 'W'},
            {'W', 'W', 'W', 'W', 'W'}
        };
        assertArrayEquals(expectedMap, map);
    }

    @Test
    public void testDrawMap() {
        char[][] map = {
            {' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' '}
        };

        int size = 5;
        int heroX = 2;
        int heroY = 2;
        char heroDirection = 'N';

        ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();
        PrintStream printStream =
                new PrintStream(outputStream);
        System.setOut(printStream);

        MapCreator.drawMap(map, size, heroX, heroY, heroDirection);
        String expectedOutput = "   _ _ _ _ _ \n" +
                                "   _ HN_ _ \n" +
                                "   _ _ _ _ _ \n" +
                                "   _ _ _ _ _ \n" +
                                "   _ _ _ _ _ \n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testGetElementSymbol() {
        char wall = 'W';
        char pit = 'P';
        char wumpus = 'M';
        char gold = 'G';
        char other = ' ';

        char symbolWall = MapCreator.getElementSymbol(wall);
        char symbolPit = MapCreator.getElementSymbol(pit);
        char symbolWumpus = MapCreator.getElementSymbol(wumpus);
        char symbolGold = MapCreator.getElementSymbol(gold);
        char symbolOther = MapCreator.getElementSymbol(other);

        assertEquals('+', symbolWall);
        assertEquals('O', symbolPit);
        assertEquals('M', symbolWumpus);
        assertEquals('*', symbolGold);
        assertEquals('_', symbolOther);
    }

    @Test
    public void testAddAndRemoveElement() {
        char[][] map = {
                {'W', 'W', 'W', 'W', 'W'},
                {'W', ' ', ' ', ' ', 'W'},
                {'W', ' ', ' ', ' ', 'W'},
                {'W', ' ', ' ', ' ', 'W'},
                {'W', 'W', 'W', 'W', 'W'}
        };
        int validX = 2;
        int validY = 2;
        int invalidX = 0;
        int invalidY = 6;
        int heroX = 1;
        int heroY = 1;

        MapCreator.addElement(map, validX, validY, 'P');
        MapCreator.addElement(map, invalidX, validY, 'M');
        MapCreator.addElement(map, validX, invalidY, 'G');
        MapCreator.addElement(map, heroX, heroY, 'W');

        char[][] expectedMap = {
                {'W', 'W', 'W', 'W', 'W'},
                {'W', ' ', ' ', ' ', 'W'},
                {'W', 'P', ' ', ' ', 'W'},
                {'W', ' ', ' ', ' ', 'W'},
                {'W', 'W', 'W', 'W', 'W'}
        };
        assertArrayEquals(expectedMap, map);

        MapCreator.removeElement(map, validX, validY);
        MapCreator.removeElement(map, invalidX, validY);
        MapCreator.removeElement(map, validX, invalidY);
        MapCreator.removeElement(map, heroX, heroY);

        char[][] nextExpectedMap = {
            {'W', 'W', 'W', 'W', 'W'},
            {'W', ' ', ' ', ' ', 'W'},
            {'W', ' ', ' ', ' ', 'W'},
            {'W', ' ', ' ', ' ', 'W'},
            {'W', 'W', 'W', 'W', 'W'}
        };
        assertEquals(nextExpectedMap, map);
    }
}
