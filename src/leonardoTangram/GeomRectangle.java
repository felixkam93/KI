package leonardoTangram;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class GeomRectangle extends GeometricObject {

	GeneralPath mPath;
	
	public GeomRectangle() 
	{
		super();
		
		mPath=new GeneralPath();
		mPath.moveTo(0,0);
		mPath.lineTo(20, 0);		
		mPath.lineTo(20, 80);
		mPath.lineTo(0, 80);		
		mPath.closePath();
		
	}

	@Override
	public void paint(Graphics2D g2, int aX, int aY,float aScale, float aRotation) 
	{
		AffineTransform t=new AffineTransform();
		t.translate(aX, aY);
		t.rotate(aRotation);
		t.scale(aScale, aScale);
		g2.setColor(Color.red);
		
		g2.fill(mPath.createTransformedShape(t));
		
	}

}
