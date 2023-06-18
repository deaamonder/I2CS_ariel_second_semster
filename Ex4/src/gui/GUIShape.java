package gui;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */

import geo.*;

import java.awt.*;
import java.util.ArrayList;


public class GUIShape implements GUI_Shape{
	private GeoShape _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;

	public GUIShape(GeoShape g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}

	//will use it in the load function
	public GUIShape(String string) {
		init(string.split(","));
	}

	@Override
	public GeoShape getShape() {
		return this._g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;

	}

	@Override
	public GUIShape copy() {
		GUIShape cp = new GUIShape(this);
		return cp;
	}
	@Override
	public String toString() {
		return "GUIShape," + _color.getRGB() +"," + _fill + "," + _tag + "," +_g;
	}
	private void init(String[] ww) {
		_color = new Color(Integer.parseInt(ww[1]));
		_fill = Boolean.parseBoolean(ww[2]);
		_tag = Integer.parseInt(ww[3]);
		String info = ww[4];

		Point_2D p =new Point_2D(0,0);

		if(info.compareTo("Point_2D") == 0) {
			p = new Point_2D(Double.parseDouble(ww[5]),Double.parseDouble(ww[6]));
		}

		else if (info.compareTo("Circle_2D") == 0) {
			double x = 0, y = 0;
			x = Double.parseDouble(ww[5]);
			y = Double.parseDouble(ww[6]);
			Point_2D center = new Point_2D(x,y);
			double radius = Double.parseDouble(ww[7]);
			_g = new Circle_2D(center,radius);
		}

		else if (info.compareTo("Rect_2D") == 0) {

			double x1 = 0, y1 = 0, x2 = 0, y2 = 0;
			x1=Double.parseDouble(ww[5]);
			y1=Double.parseDouble(ww[6]);
			x2=Double.parseDouble(ww[7]);
			y2=Double.parseDouble(ww[8]);
			//we only need 2 points to build the rectangle
			Point_2D p1,p2;
			p1 = new Point_2D(x1,y1);
			p2 = new Point_2D(x2,y2);
			_g = new Rect_2D(p1,p2);
		}

		else if (info.compareTo("Segment_2D") == 0) {
			double x1 = 0, y1 = 0, x2 = 0, y2 = 0;

			x1=Double.parseDouble(ww[5]);
			y1=Double.parseDouble(ww[6]);
			x2=Double.parseDouble(ww[7]);
			y2=Double.parseDouble(ww[8]);

			Point_2D p1,p2;
			p1 = new Point_2D(x1,y1);
			p2 = new Point_2D(x2,y2);
			_g = new Segment_2D(p1, p2);
		}
		else if (info.compareTo("Triangle_2D") == 0) {
			double x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0;
			x1=Double.parseDouble(ww[5]);
			y1=Double.parseDouble(ww[6]);
			x2=Double.parseDouble(ww[7]);
			y2=Double.parseDouble(ww[8]);
			x3=Double.parseDouble(ww[9]);
			y3=Double.parseDouble(ww[10]);

			Point_2D p1, p2, p3;
			p1 =new Point_2D(x1,y1);
			p2 = new Point_2D(x2,y2);
			p3 = new Point_2D(x3,y3);

			_g = new Triangle_2D(p1, p2, p3);
		}

		else if (info.compareTo("Polygon_2D") == 0) {
			ArrayList<Point_2D> polyPoints = new ArrayList<Point_2D>();
			for (int i = 5; i<ww.length; i = i+2) {
				Point_2D pol = new Point_2D(Double.parseDouble(ww[i]),Double.parseDouble(ww[i+1]));
				polyPoints.add(pol);
			}
			_g = new Polygon_2D(polyPoints);
		}
	}
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
	@Override
	public void setShape(GeoShape g) {
		if(g instanceof Rect_2D) {
			this._g = (Rect_2D)g;
		}
		if(g instanceof Circle_2D) {
			this._g = (Circle_2D)g;
		}
		if(g instanceof Polygon_2D) {
			this._g = (Polygon_2D)g;
		}
		if(g instanceof Triangle_2D) {
			this._g = (Triangle_2D)g;
		}
		if(g instanceof Segment_2D) {
			this._g = (Segment_2D)g;
		}

	}
}
