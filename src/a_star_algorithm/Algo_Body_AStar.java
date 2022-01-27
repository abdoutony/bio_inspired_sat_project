package a_star_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import algos_dfs_astar_global_classes.Solution;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import loading_sat_dataset.Loading;

public class Algo_Body_AStar {
    
	public static  long timeOutDefaultValue = 6000; 
    public static List<XYChart.Data<Number, Number>> listAccuracyForEachInstanceASTAR = new ArrayList<XYChart.Data<Number,Number>>();
    public static List<XYChart.Data<Number, Number>> listSatisfactionASTARData = new ArrayList<XYChart.Data<Number,Number>>();
    private long timeStart, timeStop;
    private Solution bestSolution;
    
    private void print_ASTAR_Best_Solution() {
        System.out.println(" Solution:\n " + Arrays.toString(bestSolution.getArraySolution()));
        //System.out.println("Satisfactions : " + Load.sat.satisfiedClauses(best) + " - " + (float) Load.sat.satisfiedClauses(best) / Load.CLAUSE_NUMBER * 100 + "%");
        System.out.println("Execution Time : " + (timeStop - timeStart) + "ms");
    }
    
   
    public void algoBody(int instence) {
        timeStart = System.currentTimeMillis();
        int[] values = new int[Loading.VAR_NUM];
        /* generate an initial solution a list of 0 values */
        Solution solution = new Solution(values, -1, Loading.sat); 
        /* create a stack */
        Stack<Solution> stack = new Stack<>(); 
        List<Solution> solutionsAlreadyPassed = new ArrayList<>(); 
        boolean alreadyPassed; 
        while (!Loading.sat.satisfied(solution) && (System.currentTimeMillis() - timeStart) < timeOutDefaultValue) {
            alreadyPassed = false;
            for (Solution sol : solutionsAlreadyPassed) { // verifier si la solution has passed or not
                if (Arrays.equals(solution.getArraySolution(), sol.getArraySolution())) { 
                    alreadyPassed = true;
                }
            }
            if (alreadyPassed == true) { 
                if (stack.empty()) {
                    break;
                }
                solution = stack.pop();
            } else {
                if (solution.getLevel() < Loading.VAR_NUM - 1) {
                    solution.getKidsAStar(stack, Loading.VAR_NUM, Loading.sat);
                }
                solutionsAlreadyPassed.add(solution);
                solution = stack.pop();
            }
        }

        timeStop = System.currentTimeMillis();
        bestSolution = solution;
        print_ASTAR_Best_Solution();
   
        int numberOfSatisfiedClauses = Loading.sat.satisfiedClauses(bestSolution); 
        float valueOfAccuracy = (float) numberOfSatisfiedClauses / Loading.CLAUSE_NUMBER * 100;
        Long executionTime = timeStop - timeStart;
        System.out.println("satisfactions :"+numberOfSatisfiedClauses+"     ////////////    accuracy :"+ valueOfAccuracy+" instence : "+ instence);
        listAccuracyForEachInstanceASTAR.add(new XYChart.Data<Number, Number>(instence,valueOfAccuracy));
        listSatisfactionASTARData.add(new Data<Number, Number>(instence,executionTime));
        Run_Algo_AStar.totalTimeASTAR += timeStop - timeStart;

    }
}
