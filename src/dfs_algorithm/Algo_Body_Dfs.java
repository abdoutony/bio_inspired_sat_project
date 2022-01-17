package dfs_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import dfs_algorithm.Run_Algo_Dfs;
import dfs_algorithm.Solution;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import loading_sat_dataset.Loading;

public class Algo_Body_Dfs {
	
	public static  long DEFAULT_TIMEOUT = 6000; // en ms
    public static List<XYChart.Data<Number, Number>> list_of_data_accuracyDFS = new ArrayList<XYChart.Data<Number,Number>>();
    public static List<XYChart.Data<Number, Number>> list_of_data_satisfactionDFS = new ArrayList<XYChart.Data<Number,Number>>();
    private static final String DEPTH_FIRST_ALGORITHM = "Profondeur"; // en ms

    private long startTime, stopTime;
    private Solution best;

    private int best_sats;
    private int tmp_sats;

    private void printBestSolution(String algorithm) {
        System.out.println(algorithm + " Solution " + Arrays.toString(best.getValues()));
        //System.out.println("Satisfactions : " + Load.sat.satisfiedClauses(best) + " - " + (float) Load.sat.satisfiedClauses(best) / Load.CLAUSE_NUMBER * 100 + "%");
        System.out.println("Time : " + (stopTime - startTime) + "ms");
        System.out.println("--------------------------------------------");
    }

    // Profondeur Dabord
    public void depthFirstAlgorithm(int instence) {

        int[] values = new int[Loading.VAR_NUM]; // setting variables a 0 

        Stack<Solution> sols = new Stack<Solution>(); // creation du Pile
        sols.push(new Solution(values, 0, 1)); // remplissage fils droit
        sols.push(new Solution(values, 0, 0)); // remplissage fils gauche
        Solution s = sols.pop(); // extraction du dernier element LIFO

        best = s;  // initialisation de la meilleur solution

        best_sats = Loading.sat.satisfiedClauses(best); // nombre de clauses satisfaites
        tmp_sats = 0;

        startTime = System.currentTimeMillis(); // le temp de debut

        while (!Loading.sat.satisfied(s) && (System.currentTimeMillis() - startTime) < DEFAULT_TIMEOUT) {
            if (s.getLvl() < Loading.VAR_NUM - 1) {
                s.getKidsProfondeur(sols); // generation des fils
            }
            s = sols.pop(); // extraction du dernier element LIFO 
            tmp_sats = Loading.sat.satisfiedClauses(s); // Mis a jours de la meilleur solution
            if (tmp_sats > best_sats) {
                best_sats = tmp_sats;
                best = s;
            }
        }

        stopTime = System.currentTimeMillis();

        printBestSolution(DEPTH_FIRST_ALGORITHM);// affichage de la solution
        
        //stokage de donnees pour les graphe de statistique
        int satisfactions = Loading.sat.satisfiedClauses(best); 
        Long time_exe = stopTime - startTime;
        float accuracy = (float) satisfactions / Loading.CLAUSE_NUMBER * 100;
        System.out.println("satisfactions :"+satisfactions+"     ////////////    accuracy :"+ accuracy+" instence : "+ instence);
		list_of_data_accuracyDFS.add(new XYChart.Data<Number, Number>(instence,accuracy));
        list_of_data_satisfactionDFS.add(new Data<Number, Number>(instence,time_exe));
		Run_Algo_Dfs.DEPTH_FIRST_TOTAL_TIME += stopTime - startTime; // ajout a global algo time

    }


}
