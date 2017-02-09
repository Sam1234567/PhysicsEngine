package physics;

public class Ball implements System
{
    private Vector r;
    private Vector v;
    private Vector a=new Vector(0,0);
    
    private boolean reflectX=false;
    private boolean reflectY=false;
    
    public double elasticity=1.0;
    
    public double R;
    public double M;
    
    double t=0;
    
    public Ball(Vector r,Vector v,double R,double M) {
        this.r=r;
        this.v=v;
        this.R=R;
        this.M=M;
    }
    
    @Override public void step(double t,World w) {
        
        
        if(w.bounceOffWalls) {
            if(r.x<R && v.x<0) reflectX=true;
            if(r.y<R && v.y<0) reflectY=true;
            if(r.x>w.width-R && v.x>0) reflectX=true;
            if(r.y>w.height-R && v.y>0) reflectY=true;
        }
        
        
        for(System s:w) {
            if(s!=this) {
                if(s instanceof Ball) {
                    Ball b=(Ball)s;
                    if(b.r().sub(r).mag2()<(R+b.R)*(R+b.R)) collide(b,w);
                }
            }
        }
        
        r=r.add(v.mult(t));
        r=r.add(a.mult(t*t/2));
        v=v.add(a.mult(t));
        a=new Vector(0,0);
        a=a.add(new Vector(0,w.gravity));
        
        
        this.t=t;
    }
    
    @Override public void update() {
        if(reflectX) v.x=-v.x;
        if(reflectY) v.y=-v.y;
        reflectX=false;
        reflectY=false;
    }
    
    void collide(Ball b,World w) {
        Vector N=b.r().sub(r);
        double v1Par=v.comp(N);
        double v2Par=b.v().comp(N);
        if(v2Par-v1Par<=0) {
            double E=0.5*(b.elasticity+elasticity);
            double M2=b.M;
            
            double p=v1Par*M+v2Par*M2;
            double K=(v1Par*v1Par*M+v2Par*v2Par*M2)/2;
            double vParEq=p/(M+M2);
            double KEq=vParEq*vParEq*(M+M2)/2;
            double EHeat=(K-KEq)*(1-E);
            w.heat+=EHeat;
            K=K-EHeat;
            double v1ParNew=(p*M-Math.sqrt(p*p*M*M-M*(M+M2)*(p*p-2*K*M2)))/(M*(M+M2));
            double v2ParNew=(p*M2+Math.sqrt(p*p*M2*M2-M2*(M+M2)*(p*p-2*K*M)))/(M2*(M+M2));
            
            v=v.add(N.norm().mult(-v1Par+v1ParNew));
            b.addVel(N.norm().mult(-v2Par+v2ParNew));
            
        }
    }
    
    public void applyForce(Vector v) {
        a=a.add(v.mult(1/M));
        java.lang.System.out.println(v);
    }
    
    public Vector r() {
        return r;
    }
    
    public Vector v() {
        return v;
    }
    
    public Vector a() {
        return a;
    }
    
    void addVel(Vector v_) {
        this.v=this.v.add(v_);
    }
    
    @Override public Vector p() {
        return v.mult(M);
    }
    
    @Override public double K() {
        return v.mag2()*M/2;
    }
    
    
    
}
