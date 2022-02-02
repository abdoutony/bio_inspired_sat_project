package gen_algorithm;

import javafx.scene.chart.XYChart.Data;
import loading_sat_dataset.Loading;
import algo_gen_global_classes.GenAlgoController;
import algo_gen_global_classes.Population;
import algo_gen_global_classes.Solution;
import java.util.ArrayList;
import java.util.List;
public class Algo_Body_Gen {
	
	    public static double timeOutDefaultValue = 60000;
	    public static List<Data<Number, Number>> listAccuracyForEachInstanceGEN = new ArrayList<Data<Number, Number>>();
	    public static List<Data<Number, Number>> listSatisfactionGENData = new ArrayList<Data<Number, Number>>();
		private static  int permanentSolutions = 0; 
	    public static int nbrInstancesGen = 10;
		public static  double chanceOfMutation = .05;  
	    public static  double chanceOfCrossOver = .95; 
	    public static  int sizeOfTheSelection = 30;
	    public static Solution bestSolution = null;
	    public static Population population;
	    public static long timeStart;
	    
	    
	    public Algo_Body_Gen() {
	    	 population = new Population();
		     bestSolution = population.getListSolutions().get(0);
	    }
	    
	    
	    public static void algoBody(int x) {
	    	 Algo_Body_Gen g1 = new Algo_Body_Gen();
	        	timeStart = System.currentTimeMillis();
	            int iterationCounter = 0;
	            while (g1.returnPopulation().getFitnessBestValue() < Loading.numberOfClauses && System.currentTimeMillis() - timeStart < timeOutDefaultValue) { // si problem n'est pas satisfait et tmps < timeout
	            	GenAlgoController.runCrossOver(permanentSolutions,chanceOfCrossOver,population,sizeOfTheSelection);
	            	GenAlgoController.runMutation(permanentSolutions,population,bestSolution,chanceOfMutation);
	                iterationCounter++;
	            }
	            
	            Run_Algo_Gen.totalTimeGEN += System.currentTimeMillis() - timeStart;
	          
	            /* save data for to be displayed on graphs */
	            int numberOfSatisifiedClauses = g1.returnBestSolution().getValueFitness(); 
	            float valueOfAccuracy = (float)numberOfSatisifiedClauses/Loading.numberOfClauses*100;
	            
	            listAccuracyForEachInstanceGEN.add(new Data<Number, Number>(x,valueOfAccuracy));
	            listSatisfactionGENData.add(new Data<Number, Number>(x, iterationCounter));
	           
	         
	    }
	
	    

	    
	    public Population returnPopulation() {
	        return population;
	    }

	    
	    public void updatePopulation(Population population) {
	        Algo_Body_Gen.population = population;
	    }
	    
	    public Solution returnBestSolution() {
	        return bestSolution;
	    }
	    
  

}
