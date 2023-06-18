package geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Segment_2DTest {
    //note : we didnt test the area function because it always returns 0.

    @Test
    void testContains() {
        Point_2D start = new Point_2D(0,0);
        Point_2D end = new Point_2D(0,3);
        Segment_2D seg = new Segment_2D(start,end);
        assertTrue(seg.contains(new Point_2D(0,1)));
    }

    @Test
    void testPerimeter() {
        // that test isnt necessary
        Point_2D start = new Point_2D(0,0);
        Point_2D end = new Point_2D(0,3);
        Segment_2D seg = new Segment_2D(start,end);
        double dis = 3;
        assertEquals(dis,seg.perimeter());
    }

    @Test
    void testTranslate() {
        Point_2D start = new Point_2D(0,0);
        Point_2D end = new Point_2D(0,3);
        Segment_2D seg = new Segment_2D(start,end);
        Point_2D vec = new Point_2D(5,5);
        seg.translate(vec);
        assertTrue(seg.contains(vec));
    }

    @Test
    void testCopy() {
        Point_2D start = new Point_2D(0,0);
        Point_2D end = new Point_2D(0,3);
        Segment_2D seg = new Segment_2D(start,end);
        Segment_2D seg2 = (Segment_2D)seg.copy();
        //both must be the same length
        assertEquals(seg.perimeter(),seg2.perimeter());
    }

    @Test
    void testScale() {
        Point_2D start = new Point_2D(0,0);
        Point_2D end = new Point_2D(0,3);
        Point_2D secondEnd=new Point_2D(0,4);
        Segment_2D seg = new Segment_2D(start,end);
        double length = seg.perimeter();
        Segment_2D seg2 = new Segment_2D(start,secondEnd);
        double length2 = seg2.perimeter();
        seg.scale(start, 110);
        seg2.scale(start, 90);
        assertEquals(1.1*length,seg.perimeter());
        assertEquals(0.9*length2,seg2.perimeter());
    }

    @Test
    void testRotate() {
        Point_2D start = new Point_2D(0,0);
        Point_2D end = new Point_2D(0,3);
        Segment_2D seg = new Segment_2D(start,end);
        seg.rotate(start, 360);
        double dis = start.distance(seg.get_p1());
        //must stay in the same place
        assertEquals(0,dis);
    }

}