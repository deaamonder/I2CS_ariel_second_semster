public class Index2D implements Pixel2D{
    private int _x, _y;
    public Index2D() {this(0,0);}
    public Index2D(int x, int y) {_x=x;_y=y;}
    public Index2D(Pixel2D t) {this(t.getX(), t.getY());}
    @Override
    public int getX() {
        return _x;
    }
    @Override
    public int getY() {
        return _y;
    }
    public double distance2D(Pixel2D t) {
        double ans = 0;
        double deltaX = Math.abs(t.getX() - this._x);
        double deltaY = Math.abs(t.getY() - this._y);
        ans = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        return ans;
    }
    @Override
    public String toString() {
        return getX()+","+getY();
    }
    @Override
    public boolean equals(Object t) {
        boolean ans = false;
        if(t==null || !(t instanceof Index2D)) {return false;}
        Index2D p2 = (Index2D)t;
        ans = (this._x==p2._x) && (this._y==p2._y);
        return ans;
    }
}
