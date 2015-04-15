package leonardoTangram;

import java.awt.Graphics2D;
import java.awt.Point;

public abstract class GeometricObject 
{
	
	public abstract void paint(Graphics2D g2,int aX, int aY, float aScale, float aRotation);	
}
