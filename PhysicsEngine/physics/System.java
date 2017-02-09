package physics;

public interface System
{
    default void step(double t,World w) {}
    default void update() {}
    
    default Vector p()  {
        return new Vector(0,0);}
    default double K()  {
        return 0;}
    default double U() {
        return 0;}
}
