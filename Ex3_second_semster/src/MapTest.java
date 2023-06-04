import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    static final int[][] map = new int[5][5];
    static final Map mainMap = new Map(map);
    static final Index2D zeroIndex = new Index2D(0,0);

    @Test
    void fill() {
        //trying to fill the map with the color 0
        mainMap.fill(zeroIndex,0);
        //generate random point within the map
        int random = (int)(Math.random()* map.length);
        int random2 = (int)(Math.random()*map[0].length);
        assertEquals(0,map[random][random2]);
    }

    @Test
    void shortestPath() {
        //testing the shortest path from the zero point to the end of the map
        final int ZERO =0;
        Pixel2D[] shortestPath = mainMap.shortestPath(zeroIndex,new Index2D(0,map[0].length-1),-1);
        Pixel2D[] points = new Index2D[4];
        for(int i =0; i< points.length; i++){
            points[i] = new Index2D(ZERO,i+1);
        }
        for(int i=0; i<shortestPath.length; i++){
            assertEquals(shortestPath[i].getX(),points[i].getX());
            assertEquals(shortestPath[i].getY(), points[i].getY());
        }
    }

    @Test
    void isInside() {
        Index2D point ;
        //generate random x and y within the map borders
        Random random = new Random();
        int randomX = random.nextInt(4);
        int randomY = random.nextInt(4);
        point = new Index2D(randomX,randomY);
        //the point must be inside the map
        assertTrue(mainMap.isInside(point));
    }

    @Test
    void isInside2(){
        Index2D point ;
        //generate random x and y not in the map borders
        Random random = new Random();
        int randomX = random.nextInt(5,20);
        int randomY = random.nextInt(5,20);
        point = new Index2D(randomX,randomY);
        //the point must be outside the map
        assertFalse(mainMap.isInside(point));
    }

    @Test
    void allDistance() {
        Map newMap = (Map)mainMap.allDistance(zeroIndex,-1);
        assertEquals(4,newMap.getMap()[4][0]);
        assertEquals(4,newMap.getMap()[0][4]);
        assertEquals(0,newMap.getMap()[0][0]);
        assertEquals(8,newMap.getMap()[4][4]);
    }
}