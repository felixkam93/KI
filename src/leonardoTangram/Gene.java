package leonardoTangram;

public class Gene 
{
	public int x;
	public int y;
	public float scale;
	public float rotation;
	
	public Gene(int aX, int aY, float aScale, float aRotation)
	{
		x=aX;
		y=aY;
		scale=aScale;
		rotation=aRotation;
	}
	
	public static int getRandPos()
	{
		int pos=0;
		
		pos=(int)(Math.random()*World.WORLDSIZE);
		
		return pos;
	}
	
	public static float getRandScale()
	{
		float scale=0;
		
		scale=(float)(Math.random()*2)+0.2f;
		
		return scale;
	}
	
	public static float getRandRot()
	{
		float rot=0;
		
		rot=(float)(Math.random()*2*Math.PI);
		
		return rot;
	}
	
	public static float checkScale(float aScale)
	{
		float scale=aScale;
		
		if (scale<0.2)
		{
			scale=0.2f;
		}
		
		if (scale>2.2)
		{
			scale=2.2f;
		}
		
		return scale;
	}
	
	public static float checkRot(float aRot)
	{
		float rot=aRot;
		
		if (rot<0)
		{
			rot=(float)Math.PI*2+rot;
		}
		
		if (rot>Math.PI*2)
		{
			rot=(float)Math.PI*2-rot;
		}
		
		return rot;	
	}
	
	public static int checkPos(int aPos)
	{
		int pos=aPos;
		
		if (pos<0)
		{
			pos=0;
		}
		
		if (pos>World.WORLDSIZE)
		{
			pos=World.WORLDSIZE;
		}
		
		return pos;
	}
	
	public Gene clone()
	{
		Gene gene=new Gene(x,y,scale,rotation);
		return gene;
	}
	
	public void set(int aX, int aY, float aScale, float aRotation)
	{
		x=aX;
		y=aY;
		scale=aScale;
		rotation=aRotation;
	}
	
	public void set(Gene aGene)
	{
		set(aGene.x,aGene.y,aGene.scale,aGene.rotation);
	}
}
