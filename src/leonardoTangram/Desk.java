package leonardoTangram;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Desk extends JPanel implements ActionListener,Runnable
{
	public final static int  WINDOWSIZE=500;
	
	
	
	
	JPanel mButtonPanel;
	JButton mButtonStartEvolving;
	JButton mButtonEvolve;
	
	JList mListIndividuals;
	DefaultListModel mListModelIndividuals;
	
	static World mWorld;
	
	Desk mThis;
	
	int mStatus;
	private static final int statusMANUAL=0;
	private static final int statusAUTOMATIC=1;
	
	Thread mThread;
	long mCountSteps=0;
	
	
	public Desk()
	{
		mThis=this;
		this.setPreferredSize(new Dimension(WINDOWSIZE,WINDOWSIZE));
		mStatus=statusMANUAL;
		
		this.setLayout(new BorderLayout());
		mButtonPanel=new JPanel();
		mButtonPanel.setLayout(new BoxLayout(mButtonPanel,BoxLayout.PAGE_AXIS));
		//mButtonInitialize=new JButton("init");
		//mButtonPanel.add(mButtonInitialize);
		
		mButtonEvolve=new JButton("evolve");
		mButtonEvolve.addActionListener(this);
		mButtonPanel.add(mButtonEvolve);
		
		mButtonStartEvolving=new JButton("start thread");
		mButtonStartEvolving.addActionListener(this);
		mButtonPanel.add(mButtonStartEvolving);
		
		mListIndividuals=new JList();
		mListIndividuals.setPreferredSize(new Dimension(50,400));
		mListIndividuals.addListSelectionListener(new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent e) {				
				
				mThis.repaint();
			}});
		mListModelIndividuals=new DefaultListModel();
		mListIndividuals.setModel(mListModelIndividuals);
		
		mButtonPanel.add(mListIndividuals);
		
		
		this.add(mButtonPanel,BorderLayout.EAST);
		
		mWorld=new World();
		
		mWorld.computeFitnesses();
		
		Arrays.sort(mWorld.mPopulation);
		
		for (int i=0;i<mWorld.mPopulation.length;i++)
		{
			long fitness=mWorld.mPopulation[i].mFitness;
			mListModelIndividuals.addElement(fitness);
		}
		
		
		
		mThread=new Thread(this);
		mThread.start();
		
	}
	


	
	
	public void paint(Graphics g)
	{
		
		super.paint(g);
		
		Graphics2D g2=(Graphics2D)g;
		
		
		int bias=(Desk.WINDOWSIZE-World.WORLDSIZE)/2;
		int distx=20;
		g2.drawRect(distx,bias,World.WORLDSIZE,World.WORLDSIZE);
		g2.translate(distx, bias);
		
		int selectedIndividual=mListIndividuals.getSelectedIndex();
		if (selectedIndividual<0)
		{
			selectedIndividual=0;
			mListIndividuals.setSelectedIndex(0);
		}		
		
		Individual indi=mWorld.mPopulation[selectedIndividual];
		paintGeometriesForIndividual(g2, indi);
		
	}
	
	public static void paintGeometriesForIndividual(Graphics2D g2,Individual aIndividual)
	{
		for (int i=0;i<mWorld.mGeometricObjects.length;i++)
		{
			GeometricObject geomObj=mWorld.mGeometricObjects[i];
			
			
			Gene gene=aIndividual.mGenes[i];
			int posx=gene.x;
			int posy=gene.y;
			float scale=gene.scale;
			float rotate=gene.rotation;
			
			geomObj.paint(g2, posx,posy,scale,rotate);
		}
	}

	public static void main(String[] args)
	{
		JFrame f=new JFrame("Leonardo-Tangram Desk");
		Desk d=new Desk();
		
		f.add(d);
		f.pack();
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	private void step()
	{
		mWorld.evolve();

		//System.out.println("fitnesses");

		for (int i=0;i<mWorld.mPopulation.length;i++)
		{
			Individual indi=mWorld.mPopulation[i];
			long fitness=indi.mFitness;
			//System.out.println("individual "+indi+" with fitness "+fitness);
			mListModelIndividuals.setElementAt(fitness, i);
		}
		repaint();
		System.out.println(mWorld.mPopulation[0].mFitness+" fitness of the fittest individual at generation "+mCountSteps);
		mCountSteps++;
	}


	/**
	 * evolve the population
	 * @param arg0
	 */
	public void actionPerformed(ActionEvent arg0) 
	{
		
		if (arg0.getSource()==mButtonEvolve)
		{
			step();			
		}
		
		if (arg0.getSource()==mButtonStartEvolving)
		{
			switch (mStatus)
			{
			case statusAUTOMATIC:
				mStatus=statusMANUAL;
				mButtonStartEvolving.setText("start thread");
				break;
			case statusMANUAL:
				mStatus=statusAUTOMATIC;
				mButtonStartEvolving.setText("stop thread");
				break;
			}
		}
		
		
	}





	public void run() 
	{
		while (true)
		{
			
			try {
				Thread.sleep(100);
				switch (mStatus)
				{
				case statusMANUAL:
					break;
				case statusAUTOMATIC:
					step();
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}


