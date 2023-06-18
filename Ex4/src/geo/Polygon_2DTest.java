package geo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Polygon_2DTest {
    @Test
    void testContains() {
        ArrayList<Point_2D> points = new ArrayList<Point_2D>();
        Point_2D p1 = new Point_2D(0,0);
        points.add(p1);
        Point_2D p2 = new Point_2D(0,4);
        points.add(p2);
        Point_2D p3 = new Point_2D(3,4);
        points.add(p3);
        Polygon_2D triPoly = new Polygon_2D(points);
        assertTrue(triPoly.contains(new Point_2D(0,2)));
    }

    @Test
    void testArea() {
        ArrayList<Point_2D> points = new ArrayList<Point_2D>();
        Point_2D p1 = new Point_2D(0,0);
        points.add(p1);
        Point_2D p2 = new Point_2D(0,4);
        points.add(p2);
        Point_2D p3 = new Point_2D(3,4);
        points.add(p3);
        Polygon_2D triPoly = new Polygon_2D(points);
        assertEquals(6,triPoly.area());
    }

    @Test
    void testPerimeter() {
        ArrayList<Point_2D> points = new ArrayList<Point_2D>();
        Point_2D p1 = new Point_2D(0,0);
        points.add(p1);
        Point_2D p2 = new Point_2D(0,4);
        points.add(p2);
        Point_2D p3 = new Point_2D(3,4);
        points.add(p3);
        Polygon_2D triPoly = new Polygon_2D(points);
        assertEquals(12,triPoly.perimeter());
    }

    @Test
    void testTranslate() {
        ArrayList<Point_2D> points = new ArrayList<Point_2D>();
        Point_2D p1 = new Point_2D(0,0);
        points.add(p1);
        Point_2D p2 = new Point_2D(0,4);
        points.add(p2);
        Point_2D p3 = new Point_2D(4,4);
        points.add(p3);
        Point_2D p4 = new Point_2D(4,0);
        points.add(p4);
        Polygon_2D triPoly = new Polygon_2D(points);
        Point_2D vec = new Point_2D(5,5);
        assertFalse(triPoly.contains(vec));
        triPoly.translate(vec);
        assertTrue(triPoly.contains(vec));
    }

    @Test
    void testCopy() {
        ArrayList<Point_2D> points = new ArrayList<Point_2D>();
        Point_2D p1 = new Point_2D(0,0);
        points.add(p1);
        Point_2D p2 = new Point_2D(0,4);
        points.add(p2);
        Point_2D p3 = new Point_2D(4,4);
        points.add(p3);
        Polygon_2D triPoly = new Polygon_2D(points);
        Polygon_2D triPoly2 = (Polygon_2D)triPoly.copy();
        assertEquals(triPoly.area(),triPoly2.area());
    }

    @Test
    void testScale() {
        ArrayList<Point_2D> points = new ArrayList<Point_2D>();
        Point_2D p1 = new Point_2D(0,0);
        points.add(p1);
        Point_2D p2 = new Point_2D(0,4);
        points.add(p2);
        Point_2D p3 = new Point_2D(3,4);
        points.add(p3);
        Polygon_2D triPoly = new Polygon_2D(points);
        ArrayList<Point_2D> anotherPoints = new ArrayList<Point_2D>();
        Point_2D p11 = new Point_2D(0,0);
        anotherPoints.add(p11);
        Point_2D p12 = new Point_2D(0,4);
        anotherPoints.add(p12);
        Point_2D p13 = new Point_2D(4,6);
        anotherPoints.add(p13);
        Polygon_2D triPoly2 = new Polygon_2D(anotherPoints);
        triPoly.scale(p1, 110);
        triPoly2.scale(p13, 90);
        assertEquals(Math.round(1.1*3),Math.round(triPoly.getAllPoints()[1].distance(triPoly.getAllPoints()[2])));
        assertEquals(Math.round(0.9*4),Math.round(triPoly2.getAllPoints()[0].distance(triPoly2.getAllPoints()[1])));
    }

    @Test
    void testRotate() {
        ArrayList<Point_2D> points = new ArrayList<Point_2D>();
        Point_2D p1 = new Point_2D(0,0);
        points.add(p1);
        Point_2D p2 = new Point_2D(0,4);
        points.add(p2);
        Point_2D p3 = new Point_2D(3,4);
        points.add(p3);
        Polygon_2D triPoly = new Polygon_2D(points);
        triPoly.rotate(p1, 360);
        double dis = p1.distance(triPoly.getAllPoints()[0]);
        assertEquals(0,dis);
    }
}