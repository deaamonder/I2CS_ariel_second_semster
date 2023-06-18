package ex4;

import geo.Circle_2D;
import geo.Point_2D;
import geo.Rect_2D;
import geo.ShapeComp;
import gui.GUIShape;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ShapeCollectionTest {
    @Test
    void testRemoveElementAt() {
        //adding shapes to the collection(the map)
        Rect_2D rect = new Rect_2D(new Point_2D(1, 1), new Point_2D(3, 3));
        Rect_2D rect2 = new Rect_2D(new Point_2D(3, 3), new Point_2D(5, 5));
        Circle_2D circle = new Circle_2D(new Point_2D(3, 3), rect.getAllPoints()[0].distance(rect2.getAllPoints()[1]));
        GUIShape shape1 = new GUIShape(rect, false, Color.BLACK, 0);
        GUIShape shape2 = new GUIShape(rect2, false, Color.BLACK, 1);
        GUIShape shape3 = new GUIShape(circle, false, Color.BLACK, 2);
        ShapeCollection shapes = new ShapeCollection();
        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        //Checking the previous size was 3
        assertTrue(shapes.size() == 3);
        shapes.removeElementAt(0);
        shapes.removeElementAt(1);
        //Checking the size after removing the shapes
        assertTrue(shapes.size() == 1);
    }

    @Test
    void testAddAt() {
        //adding shapes to the collection(the map)
        Rect_2D rect = new Rect_2D(new Point_2D(1, 1), new Point_2D(3, 3));
        Rect_2D rect2 = new Rect_2D(new Point_2D(3, 3), new Point_2D(5, 5));
        Circle_2D circle = new Circle_2D(new Point_2D(3, 3), rect.getAllPoints()[0].distance(rect2.getAllPoints()[1]));
        GUIShape shape1 = new GUIShape(rect, false, Color.BLACK, 0);
        GUIShape shape2 = new GUIShape(rect2, false, Color.BLACK, 1);
        GUIShape shape3 = new GUIShape(circle, false, Color.BLACK, 2);
        ShapeCollection shapes = new ShapeCollection();
        shapes.add(shape1);
        shapes.add(shape2);
        shapes.addAt(shape3, 0);
        //checking if the function add the shape at the place we want 
        assertTrue(shapes.size() == 3 && shapes.get(0) == shape3);
    }

    @Test
    void testAdd() {
        //adding shapes to the collection(the map)
        Rect_2D rect = new Rect_2D(new Point_2D(1, 1), new Point_2D(3, 3));
        Rect_2D rect2 = new Rect_2D(new Point_2D(3, 3), new Point_2D(5, 5));
        GUIShape shape1 = new GUIShape(rect, false, Color.BLACK, 0);
        GUIShape shape2 = new GUIShape(rect2, false, Color.BLACK, 1);
        ShapeCollection shapes = new ShapeCollection();
        //Checking the array list was empty
        assertTrue(shapes.size() == 0);
        shapes.add(shape1);
        shapes.add(shape2);
        //checking the new size
        assertTrue(shapes.size() == 2);
    }

    @Test
    void testCopy() {
        //adding shapes to the collection(the map)
        Rect_2D rect = new Rect_2D(new Point_2D(1, 1), new Point_2D(3, 3));
        Rect_2D rect2 = new Rect_2D(new Point_2D(3, 3), new Point_2D(5, 5));
        Circle_2D circle = new Circle_2D(new Point_2D(3, 3), rect.getAllPoints()[0].distance(rect2.getAllPoints()[1]));
        GUIShape shape1 = new GUIShape(rect, false, Color.BLACK, 0);
        GUIShape shape2 = new GUIShape(rect2, false, Color.BLACK, 1);
        GUIShape shape3 = new GUIShape(circle, false, Color.BLACK, 2);
        ShapeCollection shapes = new ShapeCollection();
        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        ShapeCollection shapesCopy = (ShapeCollection) shapes.copy();
        //the new shapes collection must be the same size
        assertTrue(shapes.size() == shapesCopy.size());
    }

    @Test
    void testSort() {
        //adding shapes to the collection(the map)
        Rect_2D rect = new Rect_2D(new Point_2D(1, 1), new Point_2D(3, 3));
        Rect_2D rect2 = new Rect_2D(new Point_2D(3, 3), new Point_2D(5, 5));
        Circle_2D circle = new Circle_2D(new Point_2D(3, 3), rect.getAllPoints()[0].distance(rect2.getAllPoints()[1]));
        GUIShape shape1 = new GUIShape(rect, false, Color.BLACK, 0);
        GUIShape shape2 = new GUIShape(rect2, false, Color.BLACK, 1);
        GUIShape shape3 = new GUIShape(circle, false, Color.BLACK, 2);
        ShapeCollection shapes = new ShapeCollection();
        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        //sorting by anti area
        shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Area));
        //the circle has the biggest area
        assertTrue(shapes.get(0) == shape3);
        //sorting by area
        shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Area));
        //the circle has the biggest area
        assertTrue(shapes.get(2) == shape3);
        //sorting by perimeter
        shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Perimeter));
        //the circle has the biggest perimeter
        assertTrue(shapes.get(2) == shape3);
        //sorting by anti perimeter
        shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter));
        //the circle has the biggest perimeter
        assertTrue(shapes.get(0) == shape3);
        //sorting by toString
        shapes.sort(new ShapeComp(Ex4_Const.Sort_By_toString));
        //rect1 must have the shortest toString
        assertTrue(shapes.get(0) == shape1);
        //sorting by anti toString
        shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_toString));
        //rect1 must have the shortest toString
        assertTrue(shapes.get(2) == shape1);
        //sorting by tag
        shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Tag));
        //the circle has the biggest tag
        assertTrue(shapes.get(2) == shape3);
        //sorting by anti tag
        shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Tag));
        //the circle has the biggest tag
        assertTrue(shapes.get(0) == shape3);
    }

    @Test
    void testRemoveAll() {
        //adding shapes to the collection(the map)
        Rect_2D rect = new Rect_2D(new Point_2D(1, 1), new Point_2D(3, 3));
        Rect_2D rect2 = new Rect_2D(new Point_2D(3, 3), new Point_2D(5, 5));
        Circle_2D circle = new Circle_2D(new Point_2D(3, 3), rect.getAllPoints()[0].distance(rect2.getAllPoints()[1]));
        GUIShape shape1 = new GUIShape(rect, false, Color.BLACK, 0);
        GUIShape shape2 = new GUIShape(rect2, false, Color.BLACK, 1);
        GUIShape shape3 = new GUIShape(circle, false, Color.BLACK, 2);
        ShapeCollection shapes = new ShapeCollection();
        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        //before removing the shapes the size must be 3
        assertTrue(shapes.size() == 3);
        shapes.removeAll();
        //after removing all shapes the size must be 0
        assertTrue(shapes.size() == 0);
    }

    //we can test both save and load in one test
    @Test
    void testSaveLoad() {
        //adding shapes to the collection(the map)
        Rect_2D rect = new Rect_2D(new Point_2D(1, 1), new Point_2D(3, 3));
        Rect_2D rect2 = new Rect_2D(new Point_2D(3, 3), new Point_2D(5, 5));
        Circle_2D circle = new Circle_2D(new Point_2D(3, 3), rect.getAllPoints()[0].distance(rect2.getAllPoints()[1]));
        GUIShape shape1 = new GUIShape(rect, false, Color.BLACK, 0);
        GUIShape shape2 = new GUIShape(rect2, false, Color.BLACK, 1);
        GUIShape shape3 = new GUIShape(circle, false, Color.BLACK, 2);
        ShapeCollection shapes = new ShapeCollection();
        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        //saving the current shapes
        shapes.save("C:\\Users\\USER\\Desktop/shapes");
        //creating a new shapes collection and load the shapes we have saved
        ShapeCollection newShapes = new ShapeCollection();
        newShapes.load("C:\\Users\\USER\\Desktop/shapes");
        for(int i=0;i<2;i++);
        assertEquals(shapes.size(), newShapes.size());
    }
}
    /*@Test
    void testToString() {
        //adding shapes to the collection(the map)
        Rect_2D rect = new Rect_2D(new Point_2D(1,1),new Point_2D(3,3));
        GUIShape shape1 = new GUIShape(rect, false, Color.BLACK, 0);
        ShapeCollection shapes = new ShapeCollection();
        shapes.add(shape1);
        assertEquals("GUIShape ,-16777216,false,0,Rect_2D 1.0,1.0 3.0,3.0 1.0,3.0 3.0,1.0",shapes.toString());
    }*/

