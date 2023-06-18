package geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point_2DTest {
    @Test
    void testAdd() {
        Point_2D point = new Point_2D(0,0);
        Point_2D p = new Point_2D(2,2);
        Point_2D newPoint = point.add(p);
        double dis = newPoint.distance(p);
        //the distance must be 0
        assertEquals(0,dis);
    }

    @Test
    void testDistance() {
        Point_2D p = new Point_2D(0,2);
        //the distance from 0,0 must be 2
        assertTrue(p.distance()==2);

    }

    @Test
    void testDistancePoint_2D() {
        Point_2D point = new Point_2D(0,0);
        Point_2D p = new Point_2D(0,2);
        //the distance between the two points must be 2
        assertTrue(point.distance(p)==2);

    }

    @Test
    void testEqualsObject() {
        Point_2D point = new Point_2D(0,0);
        Point_2D p = new Point_2D(0,0);
        //the two points must be equal to each other
        assertEquals(true,point.equals(p));

    }

    @Test
    void testTranslate() {
        Point_2D point = new Point_2D(0,0);
        Point_2D vec = new Point_2D(0,2);
        point.move(vec);
        double dis = point.distance(vec);
        //the distance must be 0
        assertEquals(0,dis);
    }

    @Test
    void testScale() {
        Point_2D point = new Point_2D(0,0);
        double pointX = point.x();
        double pointY = point.y();
        point.scale(point, 110);
        //saving the new point  , will use it when we make it smaller
        double updatedX = point.x();
        double updatedY = point.y();
        assertEquals(1.1*pointX,point.x());
        assertEquals(1.1*pointY,point.y());
        point.scale(point, 90);
        assertEquals(0.9*updatedX,point.x());
        assertEquals(0.9*updatedY,point.y());
    }

    @Test
    void testRotate() {
        Point_2D point = new Point_2D(0,0);
        point.rotate(point, 360);
        //must stay at the same place
        assertTrue(point.x()==0);
        assertTrue(point.y()==0);
    }
}