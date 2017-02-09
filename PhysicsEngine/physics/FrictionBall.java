package physics;



public class FrictionBall extends Ball
{
    double angularSpeed;
    
    
    public FrictionBall(Vector r,Vector v,double R,double M) {
        super(r,v,R,M);
        angularSpeed=0;
    }
}
