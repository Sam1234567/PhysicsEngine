import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import physics.*;



public class Main extends JFrame
{
    
    World w;
    Drawer d;
    boolean going=true;
    
    double width() {
        return getContentPane().getSize().getWidth();
    }
    
    double height() {
        return getContentPane().getSize().getHeight();
    }
    
    Main() {
        w=new World();
        Ball b1;
        Ball b2;
        w.add(b1=new Ball(new Vector(200,300),new Vector(-2,0),40,4*4));
        w.add(b2=new Ball(new Vector(400,300),new Vector(2,0),40,4*4));
        //w.add(new SimpleRope(b1,b2,0,.01));
        d=new Drawer(w);
        getContentPane().add(new JPanel() {
            @Override public void paintComponent(Graphics g) {
                
                g.setColor(new Color(255,255,255));
                g.fillRect(0,0,(int)width(),(int)height());
                d.draw(g);
                g.setColor(new Color(0,0,0));
                ((Graphics2D)g).drawString("K= ~"+(int)w.K()+" U= ~"+(int)+w.U()+" H= ~"+(int)+w.H(),20,20);
                ((Graphics2D)g).drawString("Emech= ~"+(int)(w.K()+w.U())+" Etotal= ~"+(int)(w.Etotal()),20,40);
                ((Graphics2D)g).drawString("p= ~"+w.p(),20,60);
            }
        });
        
        
        
        getContentPane().setPreferredSize(new Dimension(600,600));
        pack();
        w.width=width();
        w.height=height();
        java.lang.System.out.println(w.width);
        w.bounceOffWalls=true;
        w.gravity=0.1;
        setVisible(true);
        final Thread t=new Thread(() -> {
            while(going) {
                repaint();
                revalidate();
                w.step(1);
                try {
                    Thread.sleep(10);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        
        addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                double size=10+Math.random()*50;
                w.add(new Ball(new Vector(e.getX(),e.getY()),new Vector(1-Math.random()*2,1-Math.random()*2),size,size*size/100));
            }
        });
        
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                going=false;
            }
        });
        
    }
    
    public static void main(String args[]) {
        new Main();
    }
    
}
