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
    
	public static  long TIMEOUT_DEFAULT_VALUE = 6000; 
    public static List<XYChart.Data<Number, Number>> list_accuracy_for_each_instance_ASTAR = new ArrayList<XYChart.Data<Number,Number>>();
    public static List<XYChart.Data<Number, Number>> list__satisfaction_ASTAR_data = new ArrayList<XYChart.Data<Number,Number>>();
   // private static final String A_STAR_FIRST_ALGORITHM = "A*"; // en ms
    
    private long timeStart, timeStop;
    private Solution bestSolution;

    private int nbr_satisfied_clauses;
    private int tmp_sats;
    
    private void print_ASTAR_Best_Solution() {
        System.out.println(" Solution:\n " + Arrays.toString(bestSolution.getArraySolution()));
        //System.out.println("Satisfactions : " + Load.sat.satisfiedClauses(best) + " - " + (float) Load.sat.satisfiedClauses(best) / Load.CLAUSE_NUMBER * 100 + "%");
        System.out.println("Execution Time : " + (timeStop - timeStart) + "ms");
    }
    
    
    // A etoile algorithme
    public void aStarAlgorithm(int instence) {

        timeStart = System.currentTimeMillis();

        int[] values = new int[Loading.VAR_NUM];

        Solution s = new Solution(values, -1, Loading.sat); // creation de la solutions initial vecteur des 0
        Stack<Solution> pile = new Stack<>(); // intialisation de la pile

        List<Solution> passedSolutions = new ArrayList<>(); // Liste des solution deja testé

        boolean passed; //variable boolean si solution has passed or not

        while (!Loading.sat.satisfied(s) && (System.currentTimeMillis() - timeStart) < TIMEOUT_DEFAULT_VALUE) {
            passed = false;
            for (Solution sol : passedSolutions) { // verifier si la solution has passed or not
                if (Arrays.equals(s.getArraySolution(), sol.getArraySolution())) { 
                    passed = true;
                }
            }
            if (passed == true) { // si passé -> retirer une autre solution 
                if (pile.empty()) {
                    break;
                }
                s = pile.pop();
            } else { // si non developer des fils
                if (s.getLevel() < Loading.VAR_NUM - 1) {
                    s.getKidsAStar(pile, Loading.VAR_NUM, Loading.sat);
                }
                passedSolutions.add(s);
                s = pile.pop();
            }
        }

        timeStop = System.currentTimeMillis();

        bestSolution = s;
        
        print_ASTAR_Best_Solution();
        
      //stokage de donnees pour les graphe de statistique
        int satisfactions = Loading.sat.satisfiedClauses(bestSolution); 
        float accuracy = (float) satisfactions / Loading.CLAUSE_NUMBER * 100;
        Long time_exe = timeStop - timeStart;
        System.out.println("satisfactions :"+satisfactions+"     ////////////    accuracy :"+ accuracy+" instence : "+ instence);
        list_accuracy_for_each_instance_ASTAR.add(new XYChart.Data<Number, Number>(instence,accuracy));
        list__satisfaction_ASTAR_data.add(new Data<Number, Number>(instence,time_exe));
		
        Run_Algo_AStar.TOTAL_TIME_ASTAR += timeStop - timeStart;

    }
}
