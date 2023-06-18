package geo;

import java.util.Comparator;

import ex4.Ex4_Const;
import gui.GUI_Shape;

/**
 * This class represents a Comparator over GUI_Shapes -
 * as a linear order over GUI_Shapes.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeComp implements Comparator<GUI_Shape>{
    public static final Comparator<GUI_Shape> CompByArea = new ShapeComp(Ex4_Const.Sort_By_Area);
    public static final Comparator<GUI_Shape> CompByAntiArea = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
    public static final Comparator<GUI_Shape> CompByPerimeter = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
    public static final Comparator<GUI_Shape> CompByAntiPerimeter = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
    public static final Comparator<GUI_Shape> CompByAntitoString = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
    public static final Comparator<GUI_Shape> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
    public static final Comparator<GUI_Shape> CompByTag = new ShapeComp(Ex4_Const.Sort_By_Tag);
    public static final Comparator<GUI_Shape> CompByAntiTag = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);

    private int _flag;
    public ShapeComp(int flag) {
        _flag = flag;

    }
    @Override
    public int compare(GUI_Shape o1, GUI_Shape o2) {
        int ans=0;
        if(_flag == Ex4_Const.Sort_By_toString) {
            ans = o1.toString().compareTo(o2.toString());
        }
        else if(_flag == Ex4_Const.Sort_By_Anti_toString) {
            ans = o2.toString().compareTo(o1.toString());
        }
        if(_flag == Ex4_Const.Sort_By_Area) {
            ans = Double.compare(o1.getShape().area(), o2.getShape().area());
        }
        else if(_flag == Ex4_Const.Sort_By_Anti_Area) {
            ans = Double.compare(o2.getShape().area(), o1.getShape().area());
        }
        if(_flag == Ex4_Const.Sort_By_Perimeter) {
            ans = Double.compare(o1.getShape().perimeter(), o2.getShape().perimeter());
        }
        else if(_flag == Ex4_Const.Sort_By_Anti_Perimeter) {
            ans = Double.compare(o2.getShape().perimeter(), o1.getShape().perimeter());
        }
        if(_flag == Ex4_Const.Sort_By_Tag) {
            ans = Integer.compare(o1.getTag(), o2.getTag());
        }
        else if(_flag == Ex4_Const.Sort_By_Anti_Tag) {
            ans = Integer.compare(o2.getTag(), o1.getTag());
        }


        return ans;
    }

}
