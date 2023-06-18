package geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle_2D implements GeoShape{
	private Point_2D p1;
	private Point_2D p2;
	private Point_2D p3;
	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	public Triangle_2D(Triangle_2D t1) {
		this.p1 = t1.p1;
		this.p2 = t1.p2;
		this.p3 = t1.p3;
	}

	@Override
	public String toString(){
		return "Triangle_2D," + p1 + "," + p2 + "," + p3;
	}

	public Point_2D[] getAllPoints() {
		Point_2D[] ans = new Point_2D[3];
		ans[0] = this.p1;
		ans[1] = this.p2;
		ans[2] = this.p3;
		return ans;
	}
	@Override
	public boolean contains(Point_2D ot) {
		double a = ((p2.y() - p3.y()) * (ot.x() - p3.x()) + (p3.x() - p2.x()) * (ot.y() - p3.y())) /
				((p2.y() - p3.y()) * (p1.x() - p3.x()) + (p3.x() - p2.x()) * (p1.y() - p3.y()));
		double b = ((p3.y() - p1.y()) * (ot.x() - p3.x()) + (p1.x() - p3.x()) * (ot.y() - p3.y())) /
				((p2.y() - p3.y()) * (p1.x() - p3.x()) + (p3.x() - p2.x()) * (p1.y() - p3.y()));
		double c = 1 - a - b;

		// Check if the point is inside the triangle
		return (a >= 0) && (a <= 1) && (b >= 0) && (b <= 1) && (c >= 0) && (c <= 1);

	}

	@Override
	public double area() {
		//we will ise the heron's formula to calculate the area . https://en.wikipedia.org/wiki/Heron%27s_formula

		//getting the distances
		double dis1 = p2.distance(this.p1);
		double dis2 = p3.distance(this.p2);
		double dis3 = p1.distance(this.p3);

		double s = (dis1+dis2+dis3)/2;
		return Math.sqrt(s*(s-dis1)*(s-dis2)*(s-dis3));
	}

	@Override
	public double perimeter() {
		//simply the sum of the 3 sides of the triangle
		double side1 = p1.distance(p2);
		double side2 = p1.distance(p3);
		double side3 = p2.distance(p3);
		return side1+side2+side3;

	}

	@Override
	public void translate(Point_2D vec) {
		//move every point of the triangle
		for(int i =0; i<this.getAllPoints().length; i++){
			getAllPoints()[i].move(vec);
		}
	}

	@Override
	public GeoShape copy() {
		GeoShape ans = new Triangle_2D(this.p1,this.p2,this.p3);
		return ans;
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		//we should scale every point of the triangle
		for(int  i =0; i<getAllPoints().length; i++){
			getAllPoints()[i].scale(center,ratio);
		}
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		//we should rotate every point of the triangle
		for(int i =0; i<getAllPoints().length; i++){
			getAllPoints()[i].rotate(center, angleDegrees);
		}
	}
}
