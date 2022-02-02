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
   
    public void algoBody(int instence) {
        timeStart = System.currentTimeMillis();
        int[] values = new int[Loading.numberOfVariables];
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
                if (solution.getLevel() < Loading.numberOfVariables - 1) {
                    solution.getKidsAStar(stack, Loading.numberOfVariables, Loading.sat);
                }
                solutionsAlreadyPassed.add(solution);
                solution = stack.pop();
            }
        }

        timeStop = System.currentTimeMillis();
        bestSolution = solution;   
        int numberOfSatisfiedClauses = Loading.sat.satisfiedClauses(bestSolution); 
        float valueOfAccuracy = (float) numberOfSatisfiedClauses / Loading.numberOfClauses * 100;
        Long executionTime = timeStop - timeStart;
        listAccuracyForEachInstanceASTAR.add(new XYChart.Data<Number, Number>(instence,valueOfAccuracy));
        listSatisfactionASTARData.add(new Data<Number, Number>(instence,executionTime));
        Run_Algo_AStar.totalTimeASTAR += timeStop - timeStart;

    }
}
