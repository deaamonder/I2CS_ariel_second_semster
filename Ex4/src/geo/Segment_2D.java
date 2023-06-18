package geo;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment_2D implements GeoShape{
	private Point_2D a;
	private Point_2D b;
	public Segment_2D(Point_2D a, Point_2D b) {
		this.a = a;
		this.b = b;
	}
	public Segment_2D(Segment_2D t1) {
		this.a = t1.a;
		this.b = t1.b;
	}

	public Point_2D get_p1() {
		return a;
	}
	public Point_2D get_p2() {
		return b;
	}

	@Override
	public boolean contains(Point_2D ot) {
		//we will use the triangle inequality
		double dis1 = ot.distance(a);
		double dis2 = ot.distance(b);
		return (dis1 + dis2) == this.perimeter();
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		return 2*a.distance(b);
	}

	@Override
	public void translate(Point_2D vec) {
		a.move(vec);
		b.move(vec);
	}

	@Override
	public GeoShape copy() {
		GeoShape ans = new Segment_2D(this.a,this.b);
		return ans;
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		this.a.scale(center,ratio);
		this.b.scale(center,ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this.a.rotate(center,angleDegrees);
		this.b.rotate(center, angleDegrees);
	}

	@Override
	public String toString(){
		return "Segment_2D," + a + "," + b;
	}
}