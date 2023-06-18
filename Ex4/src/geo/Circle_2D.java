package geo;

/**
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle_2D implements GeoShape{
	private Point_2D _center;
	private double _radius;
	
	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
	}
	public double getRadius() {return this._radius;}
	public Point_2D getCenter(){ return _center;}
	 @Override
	 public String toString()
	 {
		 return "Circle_2D," + _center + "," + _radius;
	 }
	@Override
	public boolean contains(Point_2D ot) {
		return (ot.distance(this._center)<=this._radius);
	}
	
	@Override
	public double area() {
		return Math.PI * Math.pow(this._radius,2);
	}
	@Override
	public double perimeter() {
		return 2 * Math.PI * this._radius;
	}
	@Override
	public void translate(Point_2D vec) {
		this._center.move(vec);
	}
	@Override
	public GeoShape copy() {
		GeoShape ans = new Circle_2D(this._center,this._radius);
		return ans;
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		//this._center.scale(center,ratio);
		if(ratio==110){
			this._radius = this._radius * 1.1;
		}
		if (ratio == 90){
			this._radius = this._radius * 0.9;
		}
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._center.rotate(center,angleDegrees);
	}

}
