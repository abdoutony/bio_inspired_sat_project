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
	
	public static  long TIMEOUT_DEFAULT_VALUE = 6000; // en ms
    public static List<XYChart.Data<Number, Number>> list_accuracy_for_each_instance_DFS = new ArrayList<XYChart.Data<Number,Number>>();
    public static List<XYChart.Data<Number, Number>> list__satisfaction_DFS_data = new ArrayList<XYChart.Data<Number,Number>>();
    /*private static final String Dfs_Algorithm = "DFS"; */// en ms

    private long timeStart, timeStop;
    private Solution bestSolution;

    private int nbr_satisfied_clauses;
    private int tmp_sats;
    

    /* the body of the dfs algorithm */
    public void dfsAlgorithmBody(int instance) {

    	/*instanciate variable with 0 value*/
        int[] values = new int[Loading.VAR_NUM]; 
        
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
        nbr_satisfied_clauses = Loading.sat.satisfiedClauses(bestSolution); 
        tmp_sats = 0;

        /*get the starting time of the execution*/
        timeStart = System.currentTimeMillis(); 

        while (!Loading.sat.satisfied(solution) && (System.currentTimeMillis() - timeStart) < TIMEOUT_DEFAULT_VALUE) {
            if (solution.getLevel() < Loading.VAR_NUM - 1) {
            	/* generate the kids nodes */
                solution.getDepthNodes(sols); 
            }
            /*extract the last element of the graph apply last in first out principle*/
            solution = sols.pop();
            /*update the best solution*/
            tmp_sats = Loading.sat.satisfiedClauses(solution);
            if (tmp_sats > nbr_satisfied_clauses) {
                nbr_satisfied_clauses = tmp_sats;
                bestSolution = solution;
            }
        }

        timeStop = System.currentTimeMillis();

        print_DFS_Best_Solution();
        
       
        int satisfiedClauses = Loading.sat.satisfiedClauses(bestSolution); 
        Long execution_time = timeStop - timeStart;
        float accuracyValue = (float) satisfiedClauses / Loading.CLAUSE_NUMBER * 100;
        System.out.println("The Number of the satisfied clauses :"+satisfiedClauses+"\nThe accuracy for the instance "+ instance +" is:"+ accuracyValue +"%");
        System.out.println("////////////////////////////////////////////");
		list_accuracy_for_each_instance_DFS.add(new XYChart.Data<Number, Number>(instance,accuracyValue));
        list__satisfaction_DFS_data.add(new Data<Number, Number>(instance,execution_time));
        
        /*set the total execution time*/
		Run_Algo_Dfs.TOTAL_TIME_DFS += timeStop - timeStart; 

    }

    private void print_DFS_Best_Solution() {
        System.out.println(" Solution:\n " + Arrays.toString(bestSolution.getArraySolution()));
        //System.out.println("Satisfactions : " + Load.sat.satisfiedClauses(best) + " - " + (float) Load.sat.satisfiedClauses(best) / Load.CLAUSE_NUMBER * 100 + "%");
        System.out.println("Execution Time : " + (timeStop - timeStart) + "ms");
        
    }


}
