import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Index2DTest {

    static final Index2D zeroIndex = new Index2D(0,0);


    @Test
    void distance2D() {
        Random random = new Random();
        int x = random.nextInt(100);
        Index2D newPoint = new Index2D(x,0);
        //the distance must be "x"
        assertEquals(x,zeroIndex.distance2D(newPoint));
    }

    @Test
    void testEquals() {
        Index2D newPoint = new Index2D(0,0);
        assertTrue(zeroIndex.equals(newPoint));
    }
}