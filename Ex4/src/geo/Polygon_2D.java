package geo;

import java.util.ArrayList;

public class Polygon_2D implements GeoShape {
	private ArrayList<Point_2D> points;
	public Polygon_2D(ArrayList<Point_2D> points)
	{
		this.points = new ArrayList<Point_2D>();
		for(int i =0; i<points.size();i++){
			this.points.add(new Point_2D(points.get(i)));
		}
	}
	public Polygon_2D(Polygon_2D po) {
		points = new ArrayList<Point_2D>();
		for(int i=0; i<po.points.size(); i++){
			this.points.add(po.points.get(i));
		}
	}
	public Point_2D[] getAllPoints() {
		Point_2D[] ans = new Point_2D[points.size()];
		for(int  i=0; i< ans.length; i++){
			ans[i] = points.get(i);
		}
		return ans;
	}

	public void add(Point_2D p) {
		points.add(p);
	}
	@Override
	public String toString()
	{
		String res = "Polygon_2D";
		for (Point_2D point : points) {
			res += "," + point;
		}
		return res;
	}
	@Override
	public boolean contains(Point_2D ot) {
		boolean result = false;
		for (int i = 0; i < points.size(); i++) {
			int j = (i + 1) % points.size(); // the next point and wrap around to 0 if i = last point.
			if ((points.get(i).y() > ot.y()) != (points.get(j).y() > ot.y()) &&
					(ot.x() < (points.get(j).x() - points.get(i).x())
							* (ot.y() - points.get(i).y())
							/ (points.get(j).y() - points.get(i).y())
							+ points.get(i).x())) {
				result = !result;
			}
		}
		return result;
	}

	@Override
	public double area() {
		//I use the shoelace formula to calculate the area of the polygon . https://en.wikipedia.org/wiki/Shoelace_formula
		double ans;
		double sum1=0,sum2=0;
		for(int i =0; i<points.size()-1; i++){
			sum1 += points.get(i).x()*points.get(i+1).y();
			sum2 += points.get(i).y()*points.get(i+1).x();
		}
		sum1 += points.get(points.size()-1).x()*points.get(0).y();
		sum2 += points.get(points.size()-1).y()*points.get(0).x();
		ans = Math.abs(sum1-sum2)/2;
		return ans;
	}
	@Override
	public double perimeter() {
		double ans = 0 ;
		for(int i=0; i<points.size()-1; i++){
			ans += points.get(i).distance(points.get(i+1));
		}
		//the distance between the last point and the first point has not counted.
		ans += points.get(points.size()-1).distance(points.get(0));
		return ans;
	}
	@Override
	public void translate(Point_2D vec) {
		for(int i =0; i<points.size(); i++){
			points.get(i).move(vec);
		}
	}
	@Override
	public GeoShape copy() {
		return new Polygon_2D(points);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		for(int i =0; i<points.size(); i++){
			points.get(i).scale(center, ratio);
		}
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		for(int i=0; i< points.size(); i++){
			points.get(i).rotate(center, angleDegrees);
		}
	}

}
