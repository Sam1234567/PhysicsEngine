package physics;


public class Vector
{
    public double x;
    public double y;
    
    public Vector(double x,double y) {
        this.x=x;
        this.y=y;
    }
    
    public Vector add(Vector v) {
        return new Vector(x+v.x,y+v.y);
    }
    
    public Vector sub(Vector v) {
        return new Vector(x-v.x,y-v.y);
    }
    
    public Vector mult(double m) {
        return new Vector(x*m,y*m);
    }
    
    public Vector rotate(double theta) {
        return new Vector(Math.cos(theta)*x+Math.sin(theta)*y,-Math.sin(theta)*x+Math.cos(theta)*y);
    }
    
    
    public double mag() {
        return Math.sqrt(x*x+y*y);
    }
    
    public double mag2() {
        return x*x+y*y;
    }
    
    public Vector norm() {
        double mag=mag();
        return new Vector(x/mag,y/mag);
    }
    
    public double angle() {
        return Math.atan2(y,x);
    }
    
    public double dot(Vector v) {
        return v.x*x+v.y*y;
    }
    
    public double comp(Vector v) {
        return dot(v)/v.mag();
    }
    
    
    @Override public String toString() {
        return "<"+x+","+y+">";
    }
}
