package geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Triangle_2DTest {
    @Test
    void testContains() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(0,4);
        Point_2D p3 = new Point_2D(3,4);
        Triangle_2D tri = new Triangle_2D(p1,p2,p3);
        assertTrue(tri.contains(new Point_2D(0,2)));
    }

    @Test
    void testArea() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(0,4);
        Point_2D p3 = new Point_2D(3,4);
        Triangle_2D tri = new Triangle_2D(p1,p2,p3);
        assertEquals(6,tri.area());
    }

    @Test
    void testPerimeter() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(0,4);
        Point_2D p3 = new Point_2D(3,4);
        Triangle_2D tri = new Triangle_2D(p1,p2,p3);
        assertEquals(12,tri.perimeter());
    }

    @Test
    void testTranslate() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(0,4);
        Point_2D p3 = new Point_2D(3,4);
        Triangle_2D tri = new Triangle_2D(p1,p2,p3);
        Point_2D vec = new Point_2D(5,5);
        assertFalse(tri.contains(vec));
        tri.translate(vec);
        assertTrue(tri.contains(vec));
    }

    @Test
    void testCopy() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(0,4);
        Point_2D p3 = new Point_2D(3,4);
        Triangle_2D tri = new Triangle_2D(p1,p2,p3);
        Triangle_2D copy =(Triangle_2D) tri.copy();
        assertEquals(tri.area(),copy.area());
        assertEquals(tri.perimeter(),copy.perimeter());
        assertTrue(copy.contains(p3));

    }

    @Test
    void testScale() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(0,4);
        Point_2D p3 = new Point_2D(3,4);
        Point_2D p4= new Point_2D(4,6);
        Triangle_2D tri = new Triangle_2D(p1,p2,p3);
        Triangle_2D tri1 = new Triangle_2D(p1,p2,p4);
        tri.scale(p1, 110);
        tri1.scale(p1, 90);
        assertEquals(Math.round(1.1*3),Math.round(tri.getAllPoints()[1].distance(tri.getAllPoints()[2])));
        assertEquals(Math.round(0.9*4),Math.round(tri1.getAllPoints()[0].distance(tri1.getAllPoints()[1])));
    }

    @Test
    void testRotate() {
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(0,4);
        Point_2D p3 = new Point_2D(3,4);
        Triangle_2D tri = new Triangle_2D(p1,p2,p3);
        tri.rotate(p3, 360);
        assertTrue(tri.contains(p2));
    }
}