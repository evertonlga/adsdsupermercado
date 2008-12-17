package util;

public class Point {

	 private double x;
	 private double y1;
	 private double y2;	
	/**
	 * @param x
	 * @param y
	 */
	public Point(double x, double y) {
		
		this.x = x;
		this.y1 = y;
	}
	
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public double getY1() {
		return y1;
	}
	/**
	 * @param y the y to set
	 */
	public void setY1(double y) {
		this.y1 = y;
	}
	
	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}
	 
}
