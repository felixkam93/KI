package leonardoTangram;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class GeomEllipse extends GeometricObject {

GeneralPath mPath;
	
	public GeomEllipse() 
	{
		super();
		
		mPath=new GeneralPath();
		mPath.moveTo(0,0);
		mPath.curveTo(10,0,30,40,0, 80);
		mPath.curveTo(-10,80,-30,40,0, 0);
		
		mPath.closePath();
		
	}

	@Override
	public void paint(Graphics2D g2,int aX, int aY,float aScale, float aRotation) 
	{
		AffineTransform t=new AffineTransform();
		t.translate(aX, aY);
		t.rotate(aRotation);
		t.scale(aScale, aScale);
		g2.setColor(Color.green);
		
		g2.fill(mPath.createTransformedShape(t));
		
	}

}
