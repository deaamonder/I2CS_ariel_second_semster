package geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rect_2DTest {
    @Test
    void testContains() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(2,2);
        Rect_2D rect = new Rect_2D(p1,p2);
        assertTrue(rect.contains(new Point_2D(1,1)));
    }

    @Test
    void testArea() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(2,2);
        Rect_2D rect = new Rect_2D(p1,p2);
        assertEquals(4,rect.area());
    }

    @Test
    void testPerimeter() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(2,2);
        Rect_2D rect = new Rect_2D(p1,p2);
        assertEquals(8,rect.perimeter());
    }

    @Test
    void testTranslate() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(2,2);
        Rect_2D rect = new Rect_2D(p1,p2);
        Point_2D vec = new Point_2D(2,0);
        rect.translate(vec);
        double dis = p2.distance(rect.getAllPoints()[2]);
        assertEquals(4,dis);
    }

    @Test
    void testCopy() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(2,2);
        Rect_2D rect = new Rect_2D(p1,p2);
        Rect_2D newRect = (Rect_2D)rect.copy();
        assertEquals(rect.Width(), newRect.Width());
        assertEquals(rect.Height(), newRect.Height());
    }

    @Test
    void testScale() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(2,2);
        Point_2D p3 = new Point_2D(5,5);
        Rect_2D rect = new Rect_2D(p1,p2);
        double height = rect.Height();
        Rect_2D rect1 = new Rect_2D(p3,p1);
        double height1 = rect1.Height();
        rect.scale(p1, 110);
        assertEquals(1.1*height, rect.Height());
        rect1.scale(p3, 90);
        assertEquals(0.9*height1, rect1.Height());
    }

    @Test
    void testRotate() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(2,2);
        Rect_2D rect = new Rect_2D(p1,p2);
        rect.rotate(p2, 360);
        double dis=rect.getAllPoints()[0].distance(p1);
        assertEquals(0,dis);
    }

}