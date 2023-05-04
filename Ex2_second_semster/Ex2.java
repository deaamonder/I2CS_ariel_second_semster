package Ex2_second_semster;
import java.util.*;

/**
 * Introduction to Computer Science 2023, Ariel University,
 * Ex2: arrays, static functions and JUnit
 *
 * This class represents a set of functions on a polynom - represented as array of doubles.
 * The array {0.1, 0, -3, 0.2} represents the following polynom: 0.2x^3-3x^2+0.1
 * This is the main Class you should implement (see "add your code here")
 * 
 * ID : 212510739
 *
 * @author boaz.benmoshe
 */
public class Ex2 {
	/** Epsilon value for numerical computation, it serves as a "close enough" threshold. */
	public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
	/** The zero polynom is represented as an array with a single (0) entry. */
	public static final double[] ZERO = {0};
	/**
	 * Computes the f(x) value of the polynom at x.
	 * @param poly
	 * @param x
	 * @return f(x) - the polynom value at x.
	 */
	public static double f(double[] poly, double x) {
		double ans = 0;
		for(int i=0;i<poly.length;i++) {
			double c = Math.pow(x, i);
			ans +=c*poly[i];
		}
		return ans;
	}
	/** Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x1) <= 0. 
	 * This function should be implemented recursively.
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	public static double root_rec(double[] p, double x1, double x2, double eps) {
		double f1 = f(p,x1);
		double f2 = f(p,x2);
		double x12 = (x1+x2)/2;
		double f12 = f(p,x12);
		if (f1*f2<=0 && Math.abs(f12)<eps) {return x12;}
		if(f12*f1<=0) {return root_rec(p, x1, x12, eps);}
		else {return root_rec(p, x12, x2, eps);}
	}
	/**
	 * This function computes a polynomial representation from a set of 2D points on the polynom.
	 * The solution is based on: //	http://stackoverflow.com/questions/717762/how-to-calculate-the-vertex-of-a-parabola-given-three-points
	 * Note: this function only works for a set of points containing up to 3 points, else returns null.
	 * @param xx
	 * @param yy
	 * @return an array of doubles representing the coefficients of the polynom.
	 */
	public static double[] PolynomFromPoints(double[] xx, double[] yy) {
		double [] ans = null;
		int lx = xx.length;
		int ly = yy.length;
		if(xx!=null && yy!=null && lx==ly && lx>1 && lx<4) {
			ans = new double[3];
			double a,b,c;
			a = (xx[0]*(yy[2]-yy[1])+xx[1]*(yy[0]-yy[2])+xx[2]*(yy[1]-yy[0]))/((xx[0]-xx[1])*(xx[0]-xx[2])*(xx[1]-xx[2]));
			b = ((yy[1]-yy[0])/(xx[1]-xx[0])-(a*(xx[0]+xx[1])));
			c = yy[0] - (a*(Math.pow(xx[0], 2))) - (b*xx[0]);
			ans[0] = c;
			ans[1] = b;
			ans[2] = a;
		}		
		return ans;
	}
	/** Two polynoms are equal if and only if the have the same values f(x) for 1+n values of x, 
	 * where n is the max degree (over p1, p2) - up to an epsilon (aka EPS) value.
	 * @param p1 first polynom
	 * @param p2 second polynom
	 * @return true iff p1 represents the same polynom as p2.
	 */
	public static boolean equals(double[] p1, double[] p2) {
		boolean ans = true;
		int onePlusN = Math.max(p1.length, p2.length);
		for(int x = 0; x<onePlusN; x++) {
			if(Math.abs(f(p1,x)-f(p2,x))>=EPS) {
				ans = false;
			}
		}
		return ans;
	}

	/** 
	 * Computes a String representing the polynom.
	 * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
	 * @param poly the polynom represented as an array of doubles
	 * @return String representing the polynom: 
	 */
	public static String poly(double[] poly) {
		String ans = "";
		for(int i = poly.length-1; i>=0; i--) {
			double mekadem = poly[i];
			if(mekadem == 0) {
				continue;
			}
			if(mekadem < 0) {
				ans += "-";
				mekadem = -mekadem;
			}
			else {
				if(mekadem > 0 && !ans.isEmpty()) {
					ans += "+";
				}
			}
			ans+=mekadem;
			if(i>0) {
				ans+="x";
				if(i>1) {
					ans += "^"+ i;
				}
			}
		}
		return ans;
	}
	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
	 * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p1(x) - p2(x)| < eps.
	 */
	public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		for(int i =(int)x1; i<=x2; i++) {
			if(Math.abs(f(p1,x12)-f(p2,x12))>eps) {
				x12=(x12+x1)/2;
			}
		}
		return x12;
	}
	/**
	 * Given a polynom (p), a range [x1,x2] and an integer with the number (n) of sample points. 
	 * This function computes an approximation of the length of the function between f(x1) and f(x2) 
	 * using n inner sample points and computing the segment-path between them.
	 * assuming x1 < x2. 
	 * This function should be implemented iteratively (none recursive).
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param numberOfSegments - (A positive integer value (1,2,...).
	 * @return the length approximation of the function between f(x1) and f(x2).
	 */
	public static double length(double[] p, double x1, double x2, int numberOfSegments) {
		double ans = x1;
		double dx = (x2-x1) / numberOfSegments;
		double[] xx = new double[numberOfSegments + 1];
		double[] yy = new double[numberOfSegments + 1];
		for(int i =0; i<=numberOfSegments; i++) {
			xx[i] = x1 + i * dx;
			yy[i] = f(p, xx[i]);
		}
		for(int i=0; i< numberOfSegments; i++) {
			double length = Math.sqrt(Math.pow(xx[i+1]-xx[i], 2));
			ans += length;
		}
		return ans;
	}

	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an integer representing the number of Trapezoids between the functions (number of samples in on each polynom). 
	 * This function computes an approximation of the area between the polynoms within the x-range.
	 * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param numberOfTrapezoid - a natural number representing the number of Trapezoids between x1 and x2.
	 * @return the approximated area between the two polynoms within the [x1,x2] range.
	 */
	public static double area(double[] p1,double[]p2, double x1, double x2, int numberOfTrapezoid) {
		double ans = 0;
		double length = (Math.abs(x1-x2))/numberOfTrapezoid;
		while(x1<x2) {
			double anthorlength = Math.abs(f(p1,x1)-f(p2,x1));
			ans += length * anthorlength;
			x1 = length + x1;
		}
		return ans;
	}
	/**
	 * This function computes the array representation of a polynom from a String
	 * representation. Note:given a polynom represented as a double array,  
	 * getPolynomFromString(poly(p)) should return an array equals to p.
	 * 
	 * @param p - a String representing polynom.
	 * @return
	 */
	public static double[] getPolynomFromString(String p) {
		p = p.replace("-", "+-");
		p = p.replaceAll(" ", "");//dont want any space
		if (p.startsWith("+-"))
		{
			p.substring(1);//need only the "-" without the "+"
		}
		String[] terms = p.split("\\+");
		int len;
		if (p.contains("^"))//if the polynom has a power then the returned array length must be that power + 1
		{
			len = Integer.parseInt(terms[0].substring(terms[0].indexOf("^")+ 1)) +1;
		}
		else
			if (p.contains("x"))
			{
				len = 2;
			}
			else
				len = 1;
		double[] coefficients = new double[len];
		int index;
		for(int i =0; i<terms.length; i++) {
			int sign;
			String term = terms[i];
			//to know in which index i must insert the coefficient
			if(!term.contains("^")&&term.contains("x")) {
				index = 1; 
			}
			else if(!term.contains("^")&&!term.contains("x")) {
				index = 0;
			}
			else {
				index = Integer.parseInt(term.substring(term.indexOf("^")+ 1));
			}
			if(term.startsWith("-")) {
				sign = -1;
			}
			else {
				sign = 1;
			}
			if(term.startsWith("x")) {
				coefficients[index] = 1.0;
			}
			if(sign==-1 && term.contains("x")) {
				term = term.substring(1);
				if(term.startsWith("x")) {
					coefficients[index] = -1.0;
				}
				int power = term.indexOf("x");
				String coeff = term.substring(0,power);
				coefficients[index] = Double.parseDouble(coeff);
			}
			else if(sign==-1 && !term.contains("x")) {
				String coeff = term.substring(0,term.length());
				coefficients[index] = Double.parseDouble(coeff);
			}
			if(sign==1 && term.contains("x")) {
				int power = term.indexOf("x");
				String coeff = term.substring(0,power);
				coefficients[index] = Double.parseDouble(coeff);
			}
			else if(sign==1 && !term.contains("x")) {
				coefficients[index] = Double.parseDouble(term);
			}
		}
		return coefficients;
	}
	/**
	 * This function computes the polynom which is the sum of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] add(double[] p1, double[] p2) {
		double [] poly ;
		//if one of the polynoms id the zero polynom , return the other one.
		if(p1.length==1 && p1[0]==0.0) {
			return p2;
		}
		if(p2.length==1 && p2[0]==0.0) {
			return p1;
		}
		if(p1.length==p2.length) {
			poly = new double [p1.length];
			for(int i =0; i<p1.length;i++) {
				poly[i] = p1[i] + p2[i];
			}
		}
		else {
			if(p1.length > p2.length) {
				poly = new double [p1.length];
				for( int i =0; i<p2.length; i++) {
					poly[i] = p1[i] + p2[i];
				}
				for(int j = (p1.length-p2.length)+1;j<p1.length;j++) {
					poly[j] = p1[j];
				}
			}
			else {
				poly = new double[p2.length];
				for(int i = 0; i<p1.length; i++) {
					poly[i] = p1[i] + p2[i];
				}
				for(int j =(p2.length - p1.length)+1; j<p2.length; j++) {
					poly[j]=p2[j];
				}
			}
		}

		return poly;
	}
	/**
	 * This function computes the polynom which is the multiplication of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] mul(double[] p1, double[] p2) {
		//if one polynom is 1 i must return the other 
		if(p2.length==1 && p2[0]==1) {
			return p1;
		}
		if(p1.length==1 && p1[0]==1) {
			return p2;
		}
		double[] poly = new double[p1.length+p2.length-1];
		for(int i =0; i<p1.length;i++) {
			for(int j =0; j<p2.length; j++) {
				poly[i+j] += p1[i] * p2[j];
			}
		}
		return poly;	
	}
	/**
	 * This function computes the derivative polynom:.
	 * @param po
	 * @return
	 */
	public static double[] derivative (double[] po) {
		double[] poly = ZERO;
		if(po.length == 1 && po[0]==0) {
			return po;
		}
		if(po.length == 1) {
			poly = new double[1];
			poly[0]=0;//the derivative for any natural number is 0 
			return poly;
		}
		poly = new double[po.length-1];
		for(int i=0; i<po.length-1; i++) {
			poly[i] = po[i+1] * (i+1);
		}
		return poly;
	}
}
