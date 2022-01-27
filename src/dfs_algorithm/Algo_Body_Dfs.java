package dfs_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import dfs_algorithm.Run_Algo_Dfs;
import algos_dfs_astar_global_classes.Solution;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import loading_sat_dataset.Loading;

public class Algo_Body_Dfs {
	
	public static  long timeOutDefaultValue = 6000; 
    public static List<XYChart.Data<Number, Number>> listAccuracyForEachInstanceDFS = new ArrayList<XYChart.Data<Number,Number>>();
    public static List<XYChart.Data<Number, Number>> listSatisfactionDFSData = new ArrayList<XYChart.Data<Number,Number>>();
    private long timeStart, timeStop;
    private Solution bestSolution;
    private int nbrSatisfiedClauses;
    private int satsTmp;
    

    /* the body of the dfs algorithm */
    public void algoBody(int instance) {

    	/*instanciate variable with 0 value*/
        int[] values = new int[Loading.numberOfVariables]; 
        
        /* create the stack to stock the nodes */
        Stack<Solution> sols = new Stack<Solution>(); 
        /*fill the left stack*/
        sols.push(new Solution(values, 0, 1));
        /*fill the right stack*/
        sols.push(new Solution(values, 0, 0));
        
        /*pull the last element of the stack apply last in first out principle*/
        Solution solution = sols.pop();
       
        /*instanciate the bestSolution with the solution that we extracted from the stack */
        bestSolution = solution;

        /* the number of satisfied clauses*/
        nbrSatisfiedClauses = Loading.sat.satisfiedClauses(bestSolution); 
        satsTmp = 0;

        /*get the starting time of the execution*/
        timeStart = System.currentTimeMillis(); 

        while (!Loading.sat.satisfied(solution) && (System.currentTimeMillis() - timeStart) < timeOutDefaultValue) {
            if (solution.getLevel() < Loading.numberOfVariables - 1) {
            	/* generate the kids nodes */
                solution.getDepthNodes(sols); 
            }
            /*extract the last element of the graph apply last in first out principle*/
            solution = sols.pop();
            /*update the best solution*/
            satsTmp = Loading.sat.satisfiedClauses(solution);
            if (satsTmp > nbrSatisfiedClauses) {
                nbrSatisfiedClauses = satsTmp;
                bestSolution = solution;
            }
        }

        timeStop = System.currentTimeMillis();

        print_DFS_Best_Solution();
        
       
        int satisfiedClauses = Loading.sat.satisfiedClauses(bestSolution); 
        Long execution_time = timeStop - timeStart;
        float accuracyValue = (float) satisfiedClauses / Loading.numberOfClauses * 100;
        System.out.println("The Number of the satisfied clauses :"+satisfiedClauses+"\nThe accuracy for the instance "+ instance +" is:"+ accuracyValue +"%");
        System.out.println("////////////////////////////////////////////");
		listAccuracyForEachInstanceDFS.add(new XYChart.Data<Number, Number>(instance,accuracyValue));
        listSatisfactionDFSData.add(new Data<Number, Number>(instance,execution_time));
        
        /*set the total execution time*/
		Run_Algo_Dfs.totalTimeDFS += timeStop - timeStart; 

    }

    private void print_DFS_Best_Solution() {
        System.out.println(" Solution:\n " + Arrays.toString(bestSolution.getArraySolution()));
        //System.out.println("Satisfactions : " + Load.sat.satisfiedClauses(best) + " - " + (float) Load.sat.satisfiedClauses(best) / Load.CLAUSE_NUMBER * 100 + "%");
        System.out.println("Execution Time : " + (timeStop - timeStart) + "ms");
        
    }


}
