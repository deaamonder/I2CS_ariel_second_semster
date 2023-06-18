package geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Circle_2DTest {
    @Test
    void testArea() {
        Point_2D center = new Point_2D(5,5);
        Circle_2D circle = new Circle_2D(center,1);
        double area = Math.PI;
        assertEquals(area,circle.area());
    }

    @Test
    void testContains() {
        Point_2D center = new Point_2D(5,5);
        Circle_2D circle = new Circle_2D(center,1);
        assertTrue(circle.contains(center));
    }

    @Test
    void testPerimeter() {
        Point_2D center = new Point_2D(5,5);
        Circle_2D circle = new Circle_2D(center,1);
        double perimeter = 2*Math.PI;
        assertEquals(perimeter,circle.perimeter());
    }

    @Test
    void testTranslate() {
        Point_2D center = new Point_2D(5,5);
        Circle_2D circle = new Circle_2D(center,1);
        Point_2D vec = new Point_2D(1,1);
        Point_2D newPoint = new Point_2D(6,6);
        circle.translate(vec);
        double dis = circle.getCenter().distance(newPoint);
        assertFalse(dis!=0.0);
    }

    @Test
    void testCopy() {
        Point_2D center = new Point_2D(5,5);
        Circle_2D circle = new Circle_2D(center,1);
        Circle_2D newCircle = (Circle_2D) circle.copy();
        assertEquals(circle.getRadius(),newCircle.getRadius());
    }

    @Test
    void testScale() {
        Point_2D center = new Point_2D(5,5);
        Circle_2D circle = new Circle_2D(center,1);
        double SmallRadius = 1*0.9;
        double BigRadius = 0.9900000000000001;
        circle.scale(center, 90);
        double newSmallRadius = circle.getRadius();
        circle.scale(center, 110);
        double newBigRadius = circle.getRadius();
        assertEquals(SmallRadius,newSmallRadius);
        assertEquals(BigRadius, newBigRadius);
    }

    @Test
    void testRotate() {
        Point_2D center = new Point_2D(5,5);
        Circle_2D circle  = new Circle_2D(center,1);
        circle.rotate(center, 360);
        double dis = circle.getCenter().distance(center);
        assertEquals(0,dis);
    }
}