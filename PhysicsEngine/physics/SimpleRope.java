package physics;


/**
 * Write a description of class Rope here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SimpleRope implements System
{
    public Ball ball1;
    public Ball ball2;
    public double length=0;
    double previousX=0;
    public double k=.1;
    
    public SimpleRope(Ball b1,Ball b2,double len,double k) {
        ball1=b1;
        ball2=b2;
        length=len;
        this.k=k;
        double l=ball1.r().sub(ball2.r()).mag();
        if(l>length) {
            double x=(l-length);
            previousX=x;
        }
    }
    
    @Override public void step(double t,World w) {
        double l=ball1.r().sub(ball2.r()).mag();
        if(l>length) {
            double x=(l-length);
            double W=k*x*x/2-k*previousX*previousX/2;
            
            Vector N=ball1.r().sub(ball2.r()).norm();
            double v1Par=ball1.v().comp(N);
            double v2Par=ball2.v().comp(N);
            
            double M1=ball1.M;
            double M2=ball2.M;
            
            double p=v1Par*M1+v2Par*M2;
            double K=(v1Par*v1Par*M1+v2Par*v2Par*M2)/2;
            double vParEq=p/(M1+M2);
            double KNew=((v1Par-x*k/M1)*(v1Par-x*k/M1)*M1+(v2Par+x*k*M1)*(v2Par+x*k/M1)*M2)/2;
            K=2*K-KNew;
            double sign1=1;
            if(v1Par<0) sign1=-1;
            double sign2=1;
            if(v2Par<0) sign2=-1;
            
            double v1ParNew=(p*M1+sign1*Math.sqrt(p*p*M1*M1-M1*(M1+M2)*(p*p-2*K*M2)))/(M1*(M1+M2));
            double v2ParNew=(p*M2+sign2*Math.sqrt(p*p*M2*M2-M2*(M1+M2)*(p*p-2*K*M1)))/(M2*(M1+M2));
            if((p*p*M1*M1-M1*(M1+M2)*(p*p-2*K*M2))<0) v1ParNew=0;
            if((p*p*M2*M2-M2*(M1+M2)*(p*p-2*K*M1))<0) v2ParNew=0;
            ball1.addVel(N.mult(-v1Par+v1ParNew-x*k/M1));
            ball2.addVel(N.mult(-v2Par+v2ParNew+x*k/M2));
            
            previousX=x;
        } else {
            previousX=0;
        }
       
    }
    
    
    @Override public double U() {
        double l=ball1.r().sub(ball2.r()).mag();
        if(l>length) {
            double x=(l-length);
            return k*x*x/2;
        }
        return 0;
    }
}
