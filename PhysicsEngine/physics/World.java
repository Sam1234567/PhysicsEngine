package physics;
import java.util.*;

public class World extends HashSet<System> implements System
{
    public boolean bounceOffWalls=false;
    public double width=Double.NaN;
    public double height=Double.NaN;
    
    double heat=0;
    
    public double gravity=0;
    public World() {
        super();
    }
    
    
    
    public void step(double t) {
        step(t,this);
        update();
    }
    
    @Override public void step(double t,World w) {
        for(System s:this) s.step(t,w);
    }
    
    @Override public void update() {
        for(System s:this) s.update();
    }
    
    @Override public Vector p() {
        Vector p=new Vector(0,0);
        for(System s:this) p=p.add(s.p());
        return p;
    }
    
    @Override public double K() {
        double K=0;
        for(System s:this) K+=s.K();
        return K;
    }
    
    @Override public double U() {
        double U=0;
        for(System s:this) {
            if(s instanceof Ball) {
                Ball b=(Ball)s;
                U+=(height-b.r().y)*b.M*gravity;
            }
            U+=s.U();
        }
        return U;
    }
    
    public double H() {
        return heat;
    }
    
    public double Emech() {
        return K()+U();
    }
    
    public double Etotal() {
        return K()+U()+H();
    }
    
}
