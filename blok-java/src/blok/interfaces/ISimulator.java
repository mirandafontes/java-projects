package blok.interfaces;


import java.awt.geom.Point2D;

public interface ISimulator {
	
	public abstract void init();
	
	public abstract void start();
	
	public abstract void stop();
	
	public abstract void removeBody( Point2D.Float body );

}
