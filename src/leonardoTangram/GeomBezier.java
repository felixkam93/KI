package leonardoTangram;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class GeomBezier extends GeometricObject {

	GeneralPath mPath;
	
	public GeomBezier() 
	{
		super();
		mPath=new GeneralPath();
		mPath.moveTo(0, 0);
		mPath.quadTo(30, 30, 30, 0);
		mPath.moveTo(0, 0);
		mPath.quadTo(-30, -30,-30,0);
		mPath.moveTo(0, 0);
		mPath.quadTo(-30, -30,0,-30);
		mPath.moveTo(0, 0);
		mPath.quadTo(30, 30, 0, 30);
		mPath.closePath();
		
	}

	@Override
	public void paint(Graphics2D g2, int aX,int aY,float aScale, float aRotation) 
	{
		AffineTransform t=new AffineTransform();
		t.translate(aX, aY);
		t.rotate(aRotation);
		t.scale(aScale, aScale);
		g2.setColor(Color.blue);
		
		g2.fill(mPath.createTransformedShape(t));

		
		
	}

}
