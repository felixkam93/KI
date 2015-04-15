package leonardoTangram;




/**
 * this class stores the genes of an domain individual
 * @author Tobias
 *
 */
public class Individual implements Comparable<Individual>
{
	Gene[] mGenes;
	long mFitness;
	
	
	
	public Individual(int size)
	{
		mGenes=new Gene[size];
		mFitness=0;
		for (int i=0;i<size;i++)
		{
			mGenes[i]=new Gene(Gene.getRandPos(),Gene.getRandPos(),Gene.getRandScale(),Gene.getRandRot());
		}
	}
	
	public void mutate(float aMutationProbability)
	{
		int mutatedGens=0;
		
		//System.out.println("show genes before mutation (next line) and after (the line after next line)");
		//System.out.println(showGenes());
		
		for (Gene gene:mGenes)
		{
			double mutationProbability=Math.random();
			if (aMutationProbability>mutationProbability)
			{
				gene.x=Gene.checkPos(gene.x+(int)(Math.random()*10-5));
				gene.y=Gene.checkPos(gene.y+(int)(Math.random()*10-5));				
				gene.scale=Gene.checkScale(gene.scale+(float)Math.random()*0.2f-0.1f);				
				gene.rotation=Gene.checkRot(gene.rotation+(float)Math.random()*0.4f-0.2f);				
				mutatedGens=mutatedGens+1;				
			}
		}
		
		//System.out.println(showGenes());
		//System.out.println(mutatedGens+" mutated Genes in individual "+this);
//		
		
	}

	public int compareTo(Individual aIndividual) {
		// TODO Auto-generated method stub

		if (aIndividual.mFitness>this.mFitness)
		{
			return 1;
		}else
		{
			if (aIndividual.mFitness<this.mFitness)
			{
				return -1;
			}else
			{
				return 0;
			}
		}
	}
	
	
	
	public Individual clone()
	{
		Individual indi=new Individual(mGenes.length);
		for (int i=0;i<mGenes.length;i++)
		{
			indi.mGenes[i].set(mGenes[i]);
		}
		return indi;
			
			
	}
	
}
