import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import gui.GUIShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geo.Circle_2D;
import geo.GeoShape;
import geo.Point_2D;
import geo.Rect_2D;
import geo.Segment_2D;
import geo.Triangle_2D;

public class GUIShapeTest {
    private GUIShape guiShape;
    private GeoShape geoShape;

    @BeforeEach
    public void setup() {
        // Create a sample GeoShape for testing
        Point_2D center = new Point_2D(0, 0);
        double radius = 5;
        geoShape = new Circle_2D(center, radius);

        // Create a GUIShape object for testing
        boolean fill = true;
        Color color = Color.RED;
        int tag = 1;
        guiShape = new GUIShape(geoShape, fill, color, tag);
    }

    @Test
    public void testIsFilled() {
        assertTrue(guiShape.isFilled());
    }

    @Test
    public void testSetFilled() {
        guiShape.setFilled(false);
        assertFalse(guiShape.isFilled());
    }

    @Test
    public void testGetColor() {
        Color color = guiShape.getColor();
        assertEquals(Color.RED, color);
    }

    @Test
    public void testSetColor() {
        Color newColor = Color.BLUE;
        guiShape.setColor(newColor);
        assertEquals(newColor, guiShape.getColor());
    }

    @Test
    public void testGetTag() {
        int tag = guiShape.getTag();
        assertEquals(1, tag);
    }

    @Test
    public void testSetTag() {
        int newTag = 2;
        guiShape.setTag(newTag);
        assertEquals(newTag, guiShape.getTag());
    }

    @Test
    public void testSetSelected() {
        guiShape.setSelected(true);
        assertTrue(guiShape.isSelected());
    }

    @Test
    public void testSetShape() {
        GeoShape newShape = new Rect_2D(new Point_2D(1, 1), new Point_2D(5, 5));
        guiShape.setShape(newShape);
        assertEquals(newShape, guiShape.getShape());
    }
}
