package ex4;

import geo.*;
import gui.Ex4_GUI;
import gui.GUIShape;
import gui.GUI_Shape;
import gui.StdDraw_Ex4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI {
	private  GUI_Shape_Collection _shapes = new ShapeCollection();
	private GUI_Shape _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private Point_2D _p1;
	//creating a new point called p2 for the triangle
	private Point_2D p2;
	//creating the arraylist to use in the polygon
	ArrayList<Point_2D> polygon = new ArrayList<Point_2D>();
	
	private  static Ex4 _winEx4 = null;
	
	private Ex4() {
			init(null);
	}
	public void init(GUI_Shape_Collection s) {
		if(s==null) {_shapes = new ShapeCollection();
		}
		else {_shapes = s;}// //shou,ld be s.copy();}
		_gs = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		 _p1 = null;
	}
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}
	
	public void drawShapes() {
		StdDraw_Ex4.clear();
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape sh = _shapes.get(i);
				
				drawShape(sh);
			}
			if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}
	private static void drawShape(GUI_Shape g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShape gs = g.getShape();
		boolean isFill = g.isFilled();
		//Circle
		if(gs instanceof Circle_2D) {
			Circle_2D c = (Circle_2D)gs;
			Point_2D cen = c.getCenter();
			double rad = c.getRadius();
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			else { 
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		//Segment
		if(gs instanceof Segment_2D){
			Segment_2D s = (Segment_2D) gs;
			Point_2D a = s.get_p1();
			Point_2D b = s.get_p2();
			StdDraw_Ex4.line(a.x(),a.y(),b.x(),b.y());
		}
		//Rectangle
		if(gs instanceof Rect_2D){
			Rect_2D r = (Rect_2D)gs;
			Point_2D p1 = r.getAllPoints()[0];
			Point_2D p2	 = r.getAllPoints()[1];
			if(isFill){
				StdDraw_Ex4.filledRectangle(Math.abs((p1.x()+p2.x())/2), Math.abs((p1.y()+p2.y())/2),r.Width()/2,r.Height()/2);
			}
			else {
				StdDraw_Ex4.rectangle(Math.abs((p1.x()+p2.x())/2), Math.abs((p1.y()+p2.y())/2), r.Width() / 2, r.Height() / 2);
			}
		}
		//Triangle
		if(gs instanceof Triangle_2D){
			Triangle_2D t = (Triangle_2D)gs;
			Point_2D p1 = t.getAllPoints()[0];
			Point_2D p2 = t.getAllPoints()[1];
			Point_2D p3 = t.getAllPoints()[2];
			double[] Xs = {p1.x(), p2.x(),p3.x()};
			double[] Ys = {p1.y(), p2.y(), p3.y()};
			if(isFill){
				StdDraw_Ex4.filledPolygon(Xs,Ys);
			}
			else{
				StdDraw_Ex4.polygon(Xs,Ys);
			}
		}
		//Polygon
		if(gs instanceof Polygon_2D){
			Polygon_2D p = (Polygon_2D) gs;
			double[] Xs = new double[p.	getAllPoints().length];
			double[] Ys = new double[p.getAllPoints().length];
			for(int i =0; i<p.getAllPoints().length;i++){
				Xs[i] = p.getAllPoints()[i].x();
				Ys[i] = p.getAllPoints()[i].y();
			}
			if (isFill){
				StdDraw_Ex4.filledPolygon(Xs,Ys);
			}
			else{
				StdDraw_Ex4.polygon(Xs,Ys);
			}
		}
	}
	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		//color
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		if(p.equals("Clear")) {_shapes.removeAll();}
		//sort
		if(p.equals("ByArea")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Area));}
		if(p.equals("ByAntiArea")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Area));}
		if(p.equals("ByPerimeter")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Perimeter));}
		if(p.equals("ByAntiPerimeter")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter));}
		if(p.equals("ByToStirng")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_toString));}
		if(p.equals("ByAntiToString")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_toString));}
		if(p.equals("ByTag")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Tag));}
		if(p.equals("ByAntiTag")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Tag));}

		//select All method
		if(p.equals("All")) {
			for(int i =0; i<_shapes.size(); i++) {
				GUI_Shape shape = _shapes.get(i);
				shape.setSelected(true);
			}
		}
		//none select method
		if(p.equals("None")) {
			for(int i=0; i<_shapes.size(); i++) {
				GUI_Shape shape = _shapes.get(i);
				shape.setSelected(false);
			}
		}
		//Anti select method
		if(p.equals("Anti")) {
			for(int i =0; i<_shapes.size(); i++) {
				GUI_Shape shape = _shapes.get(i);
				if(shape.isSelected()) {
					shape.setSelected(false);
				}
				else {
					shape.setSelected(true);
				}
			}
		}
		//info of the shapes
		if(p.equals("Info")) {
			String info = this.getInfo();
			System.out.println(info);
		}
		if(p.equals("Save")) {
			FileDialog chooser = new FileDialog(new JFrame(),"choose a location to save your file", FileDialog.SAVE);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			if(filename != null) {
				_shapes.save(chooser.getDirectory() + File.separator + chooser.getFile());
			}
		}

		if (p.equals("Load")) {
			FileDialog chooser = new FileDialog(new JFrame(),"choose a file to load", FileDialog.LOAD);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			if(filename != null) {
				_shapes.load(chooser.getDirectory() + File.separator + chooser.getFile());
			}
		}

		drawShapes();
		
	}

	
	public void mouseClicked(Point_2D p) {
		System.out.println("Mode: "+_mode+"  "+p);
		if(_mode.equals("Circle")) {
			if(_gs==null) {
				_p1 = new Point_2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		if(_mode.equals("Move")) {
			if(_p1==null) {_p1 = new Point_2D(p);}
			else {
				_p1 = new Point_2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
			}
		}
		if(_mode.equals("Point")) {
			select(p);
		}
		//Segment
		if(_mode.equals("Segment")){
			if(_gs == null){
				_p1 = new Point_2D(p);
			}
			else{
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		//Rectangle
		if(_mode.equals("Rect")){
			if(_gs == null){
				_p1 = new Point_2D(p);
			}
			else{
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		//Triangle
		if(_mode.equals("Triangle")){
			if(_gs==null){
				_p1 = new Point_2D(p);
			}
			else if(p2==null){
				p2 = new Point_2D(p);
			}
			else{
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		//Polygon
		if(_mode == "Polygon"){
			if(_gs==null){
				polygon.add(p);
				_p1 = new Point_2D(p);
			}
			else{
				polygon.add(p);
			}
		}
		//Rotate
		if (_mode.equals("Rotate")) {
			if (_p1 == null) {
				_p1 = new Point_2D(p);
			}
			else {
				p2 = new Point_2D(p);
				for (int i = 0; i < _shapes.size(); i++) {
					GUI_Shape shape = _shapes.get(i);
					GeoShape geoShape = shape.getShape();
					double dx = p2.x() - _p1.x();
					double dy = p2.y() - _p1.y();
					double atanDegree = Math.atan2(dy, dx);
					double angleBetween2Points = Math.toDegrees(atanDegree);
					if (shape.isSelected() && geoShape != null) {
						geoShape.rotate(_p1,angleBetween2Points);
					}
				}
				_p1 = null;
				p2 = null;
			}
		}
		//Scale 110%
		if(_mode.equals("Scale_110%")) {
			_p1 = new Point_2D(p);
			for(int i=0; i<_shapes.size(); i++) {
				GUI_Shape shape = _shapes.get(i);
				GeoShape geoShape = shape.getShape();
				if(shape.isSelected() && geoShape != null) {
					geoShape.scale(p, 110);
				}
			}
		}
		//Scale 90%
		if(_mode.equals("Scale_90%")){
			_p1 = new Point_2D(p);
			for(int i=0; i<_shapes.size(); i++) {
				GUI_Shape shape = _shapes.get(i);
				GeoShape geoShape = shape.getShape();
				if(shape.isSelected() && geoShape != null) {
					geoShape.scale(p, 90);
				}
			}
		}
		//copy
		if(_mode.equals("Copy")) {
			if(_p1 == null) {
				_p1 = new Point_2D(p);
			}
			else {
				Point_2D newPoint = new Point_2D(p.x()-_p1.x(),p.y()-_p1.y());
				copy(newPoint);
				_p1 = null;
			}
		}

		drawShapes();
	}
	
	private void select(Point_2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	private void move() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.translate(_p1);
			}
		}
	}
	private void copy(Point_2D Point) {
		for(int i=0; i<_shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				GUI_Shape shape = s.copy();
				shape.getShape().translate(Point);
				_shapes.add(shape);
			}
		}
	}
	
	public void mouseRightClicked(Point_2D p) {
		System.out.println("right click!");
		if (_gs!=null) {
			if(_mode.equals("Polygon")) {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				polygon = new ArrayList<Point_2D>();
				drawShapes();
			}
			if(_mode.equals("Circle") || _mode.equals("Rect" )||_mode.equals("Triangle" ) || _mode.equals("Segment")) {
				_gs = null;
				_p1 = null;
			}
		}
	}
	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShape gs = null;
		//	System.out.println("M: "+x1+","+y1);
			Point_2D p = new Point_2D(x1,y1);
			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle_2D(_p1,r);
			}
			//segment
			if(_mode.equals("Segment")){
				gs = new Segment_2D(_p1,p);
			}
			//rect
			if(_mode.equals("Rect")){
				gs = new Rect_2D(_p1,p);
			}
			//triangle
			if(_mode.equals("Triangle")){
				if(p2!=null) {
					gs = new Triangle_2D(_p1, p2, p);
				}
				else{
					gs = new Segment_2D(_p1,p);
				}
			}
			//polygon
			if(_mode.equals("Polygon")){
				ArrayList<Point_2D> temp = polygon;
				temp.add(p);
				gs = new Polygon_2D(polygon);
				temp.remove(p);
			}
			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}
	@Override
	public GUI_Shape_Collection getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}
