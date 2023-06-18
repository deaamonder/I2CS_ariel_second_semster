package geo;

/**
 * This class represents a 2D axis parallel rectangle.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect_2D implements GeoShape {
	private Point_2D p1;
	private Point_2D p2;
	private Point_2D p3;
	private Point_2D p4;
	public Rect_2D(Point_2D p1, Point_2D p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = new Point_2D(p1.x(), p2.y());
		this.p4 = new Point_2D(p2.x(), p1.y());
	}
	public Rect_2D(Rect_2D t1) {
		this.p1 = t1.p1;
		this.p2 = t1.p2;
	}

	@Override
	public String toString(){
		return "Rect_2D," + this.p1 + "," + this.p2 + "," + this.p3 + "," + this.p4;
	}

	@Override
	public boolean contains(Point_2D ot) {
		//check if the point is within the rect
		double xMin = Math.min(p1.x(), p2.x());
		double yMin = Math.min(p1.y(), p2.y());
		double xMax = Math.max(p1.x(), p2.x());
		double yMax = Math.max(p1.y(), p2.y());
		if((ot.x() >= xMin && ot.x() <= xMax && ot.y() >= yMin && ot.y() <=yMax)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public double area() {
		//width * height
		return Math.abs(this.p1.iy()-this.p2.iy()) * Math.abs(this.p2.ix()-this.p1.ix());
	}

	@Override
	public double perimeter() {
		//2*height + 2*width
		return 2*Math.abs(this.p1.iy()-this.p2.iy()) + 2*Math.abs(this.p2.ix()-this.p1.ix());
	}

	@Override
	public void translate(Point_2D vec) {
		this.p1.move(vec);
		this.p2.move(vec);
	}

	@Override
	public GeoShape copy() {
		GeoShape ans = new Rect_2D(this.p1,this.p2);
		return ans;
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		this.p1.scale(center, ratio);
		this.p2.scale(center, ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this.p1.rotate(center, angleDegrees);
		this.p2.rotate(center, angleDegrees);
	}

	public Point_2D[] getAllPoints() {
		Point_2D[] ans = new Point_2D[4];
		ans[0] = new Point_2D(p1);
		ans[1] = new Point_2D(p2);
		ans[2] = new Point_2D(p3);
		ans[3] = new Point_2D(p4);
		return ans;
	}

	//to get the rectangle Width
	public double Width() {
		double maxX = Math.max(p1.x(), p2.x());
		double minX = Math.min(p1.x(), p2.x());
		return Math.abs(maxX - minX);
	}

	//to get the rectangle height
	public double Height() {
		double maxY = Math.max(p1.y(), p2.y());
		double minY = Math.min(p1.y(), p2.y());
		return Math.abs(maxY - minY);
	}
}
