import org.example.Arrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrowsTest {
    Arrows arrows = new Arrows();
    @Test
    public void testSetShootArrow() {
        arrows.setShootArrow(true);

        assertTrue(arrows.countArrows() <
                arrows.getDefaultArrows());
        assertTrue(arrows.countArrows() >= 0);
        assertTrue(arrows.countArrows() ==
                arrows.getDefaultArrows() - 1);
        assertTrue(arrows.countArrows() ==
                arrows.getDefaultArrows() - arrows.countArrows());

        assertTrue(arrows.countArrows() >= 0);
        assertEquals(arrows.countArrows(),
                arrows.getDefaultArrows() - 1);
    }

    @Test
    public void testLoseArrow() {
        arrows.loseArrow();

        assertEquals(arrows.countArrows(),
                arrows.getDefaultArrows() - 1);

        assertTrue(arrows.countArrows() >= 0);
    }

    @Test
    public void testCountArrows() {
        assertEquals(arrows.countArrows(),
                arrows.getDefaultArrows());

        arrows.loseArrow();
        assertEquals(arrows.countArrows(),
                arrows.getDefaultArrows());
    }
}
