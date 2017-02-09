package physics;
import java.awt.*;

public class Drawer
{
    World w;
    
    public Drawer(World w) {
        this.w=w;
    }
    
    public void draw(Graphics g) {
        for(System s:w) {
            if(s instanceof SimpleRope) {
                SimpleRope r=(SimpleRope)s;
                g.setColor(new Color(100,0,0));
                g.drawLine((int)r.ball1.r().x,(int)r.ball1.r().y,(int)r.ball2.r().x,(int)r.ball2.r().y);
            }
        }
        for(System s:w) {
            if(s instanceof Ball) {
                Ball b=(Ball)s;
                g.setColor(new Color(0,0,0));
                g.drawOval((int)(b.r().x-b.R),(int)(b.r().y-b.R),(int)b.R*2,(int)b.R*2);
                g.setColor(new Color(0,0,155));
                g.fillOval((int)(b.r().x-b.R),(int)(b.r().y-b.R),(int)b.R*2,(int)b.R*2);
            }
            
        }
    }
}
