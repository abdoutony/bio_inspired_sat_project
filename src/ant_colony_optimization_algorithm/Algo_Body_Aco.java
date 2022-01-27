package ant_colony_optimization_algorithm;

import java.util.ArrayList;
import java.util.List;

import algo_aco_global_classes.AgentAnt;
import javafx.scene.chart.XYChart.Data;
import loading_sat_dataset.Loading;

public class Algo_Body_Aco {
   
    public static int nbtInstancesACO = 2;
    public static  double alphaValue = .3; 
    public static  double betaValue = .3; 
	public static  double vrateValue = .1; 
	public static  double pheromonInitialValue = .1; 
	public static  int nbrMaxIterations = 50000; 
	public static  int nbrAnts = 100; 
    public static double timeOutDefaultValue=60000;
    public static final double Q0 = 0.005; 
    public static int[][] arrayFitness; 
    public static List<AgentAnt> listAnts;
    public static double[][] arrayPheromon;
	public static List<Data<Number, Number>> listAccuracyForEachInstanceACO =new ArrayList<Data<Number,Number>>();
	public static List<Data<Number, Number>> listSatisfactionACOData =new ArrayList<Data<Number,Number>>();
	
	public static void algoBody(int x) {
		 arrayPheromon = new double[Loading.numberOfVariables][2]; 
	        for (int i = 0; i < Loading.numberOfVariables; i++) { 
	            arrayPheromon[i][0] = arrayPheromon[i][1] = pheromonInitialValue;
	        }
	     
	        double timeStart = System.currentTimeMillis();
	        int valueFitness =0;
	        int iterationCounter=0;
			while (iterationCounter < nbrMaxIterations && valueFitness != 325 &&  System.currentTimeMillis() - timeStart < timeOutDefaultValue) {
				iterationCounter++;	
	        	listAnts = new ArrayList<>(); 
	            for (int j = 0; j < nbrAnts; j++) {
	                listAnts.add(new AgentAnt());
	            }
	            int valueBest = 0; 
	            AgentAnt antBestOne = null; 
	            for (AgentAnt agentAnt : listAnts) {
	                agentAnt.start();
	                if (agentAnt.getNbrSatisfiedClauses() > valueBest) {
	                    antBestOne = agentAnt;
	                    valueBest = agentAnt.getNbrSatisfiedClauses();
	                }
	            }
	            for (int j = 0; j < Loading.numberOfVariables; j++) { 
	                if (antBestOne.getArraySolution()[j] == 0) {
	                    arrayPheromon[j][0] = (1 - vrateValue) * arrayPheromon[j][0] + vrateValue * (double) (arrayFitness[j][0]);
	                    arrayPheromon[j][1] = (1 - vrateValue) * arrayPheromon[j][1];
	                } else {
	                    arrayPheromon[j][0] = (1 - vrateValue) * arrayPheromon[j][0];
	                    arrayPheromon[j][1] = (1 - vrateValue) * arrayPheromon[j][1] + vrateValue * (double) (arrayFitness[j][1]);
	                }
	            }
	            valueFitness=antBestOne.getNbrSatisfiedClauses();	
			}
		 System.out.print("Iteration " + iterationCounter + " : "+valueFitness);
         int numberOfSatisifiedClauses = valueFitness; 
         float accuracy = (float)valueFitness/Loading.numberOfClauses*100;
       
         listAccuracyForEachInstanceACO.add(new Data<Number, Number>(x,accuracy));
         listSatisfactionACOData.add(new Data<Number, Number>(x, iterationCounter));
         System.out.println("accuracy:"+accuracy+"    satisfactions:"+numberOfSatisifiedClauses);
         System.out.println("instence:"+x+"    iterarion:"+iterationCounter);
         Run_Algo_Aco.totalTimeAco += (long) (System.currentTimeMillis()-timeStart);
	     System.out.println(" --time = "+Run_Algo_Aco.totalTimeAco+" ms");
	}
}
