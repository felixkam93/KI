package leonardoTangram;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import java.util.Arrays;

public class World {


	/**
	 * the population of inviduals, each individual encodes geometric properties of the 6 world geometric objects
	 */
	public Individual[] mPopulation;

	public static GeometricObject[] mGeometricObjects;

	/**
	 * the size of the world (should be small than Desk.WINDOWSIZE)
	 */
	public final static int  WORLDSIZE=250;

	/**
	 * the number of individuals
	 */
	public final static int POPULATIONSIZE=10;

	/**
	 * the probability that a gene mutates
	 */
	public final static float MUTATIONRATIO=0.5f;

	public final static int GEOMETRICOBJECTS=6;




	public World ()
	{

		mPopulation=new  Individual[POPULATIONSIZE];
		mGeometricObjects=new GeometricObject[GEOMETRICOBJECTS];

		createGeometricObjects();

		for (int i=0;i<POPULATIONSIZE;i++)
		{
			Individual indi=new Individual(mGeometricObjects.length);
			mPopulation[i]=indi;
		}		
	}

	/**
	 * applies methods of mutation and recombination
	 */
	public void evolve()
	{	
		createOffspringSelection();

		//insert here crossOverOperation for some pairs of individuals
        for (int i = 0; i < mPopulation.length; i++) {
            Individual indi = mPopulation[i];
            for (int j = 0; j < indi.mGenes.length;) {
                Gene parentGene1 = indi.mGenes[j];
                Gene parentGene2 = indi.mGenes[j+1];

                float parentGene1Scale = parentGene1.scale;
                float parentGene1rot = parentGene1.rotation;

                parentGene1.scale = parentGene2.scale;
                parentGene2.scale = parentGene1Scale;

                parentGene1.rotation = parentGene2.rotation;
                parentGene2.rotation = parentGene1rot;

                j = j + 2;
            }
        }

		for (Individual indi:mPopulation)
		{
			indi.mutate(MUTATIONRATIO);
		}	
		computeFitnesses();
		Arrays.sort(mPopulation);
	}


	/**
	 * selects the fittest individuals for creation of a new population
	 * using Tournament selection 
	 * using elitims for the best individual
	 */
	public void createOffspringSelection() 
	{
		Individual[] newPopulation=new Individual[POPULATIONSIZE];



		for (int i=0;i<POPULATIONSIZE-1;i++)
		{		
			int first=(int)(Math.random()*POPULATIONSIZE);
			int second=(int)(Math.random()*POPULATIONSIZE);
			int third=(int)(Math.random()*POPULATIONSIZE);

			int fittest=getFittestInTournament(first,second,third);
			Individual winner=mPopulation[fittest];
			//System.out.println("select number "+fittest +" with fittnes="+winner.mFitness);
			newPopulation[i]=winner.clone();
		}

		//make sure that the best individual survives the tournament
		newPopulation[POPULATIONSIZE-1]=mPopulation[0].clone();

		mPopulation=newPopulation;


	}

	/**
	 * return the fittest individual among three candidates
	 * @param first
	 * @param second
	 * @param third
	 * @return
	 */
	private int getFittestInTournament(int first, int second, int third) 
	{
		Individual indiFirst=mPopulation[first];
		Individual indiSecond=mPopulation[second];
		Individual indiThird=mPopulation[third];

		if (indiFirst.mFitness>indiSecond.mFitness)
		{
			if (indiThird.mFitness>indiFirst.mFitness)
			{
				return third;
			}else
			{
				return first;
			}
		}else
		{
			if (indiThird.mFitness>indiSecond.mFitness)
			{
				return third;
			}else
			{
				return second;
			}
		}		
	}


	public void createGeometricObjects()
	{

		for (int i=0;i<World.GEOMETRICOBJECTS;i++)
		{
			int type=(int)(Math.random()*3);
			switch (type){
			case 0:
			{GeometricObject geomObj=new GeomEllipse();
			mGeometricObjects[i]=geomObj;}
			break;
			case 1:
			{GeometricObject geomObj=new GeomRectangle();
			mGeometricObjects[i]=geomObj;}
			break;
			case 2:
			{GeometricObject geomObj=new GeomBezier();
			mGeometricObjects[i]=geomObj;}
			break;
			}		
		}

	}







	public void computeFitnesses()
	{
		for (int i=0;i<mPopulation.length;i++)
		{
			computeFitness(i);

		}
	}


	/**
	 * computes the fitness of an individual, hence how much of its rectangles cover the desk
	 * @param aIndividual
	 * @return
	 */
	public void computeFitness(int aIndividual)
	{
		Individual indi=mPopulation[aIndividual];

		BufferedImage image=new BufferedImage(WORLDSIZE,WORLDSIZE,BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2=image.createGraphics();

		//the computational costs of this part can be reduced: draw to a very small image (with geometries scaled and use small image for counting)
		Desk.paintGeometriesForIndividual(g2, indi);		
		int countOcupiedPixels=countOcupiedPrixels(image);
		indi.mFitness=countOcupiedPixels;

		g2.dispose();

	}

	private int countOcupiedPrixels(BufferedImage image) 
	{
		Raster raster=image.getData();
		int ocupiedPixels=0;
		for (int x=0;x<image.getWidth();x++)
		{
			for (int y=0;y<image.getHeight();y++)
			{
				if (raster.getSample(x, y, 3)>00)
				{//transparency band is not zero-> here was something painted
					ocupiedPixels++;
				}
			}
		}

		return ocupiedPixels;
	}

	public static boolean checkCoor(int aX,int aY)
	{
		if ((aX>=0)&&(aX<WORLDSIZE)&&(aY>=0)&&(aY<WORLDSIZE))
		{
			return true;
		}else
		{
			return false;
		}
	}


}
