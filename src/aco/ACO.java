package aco;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import sat_codification.Clause;
import sat_codification.Litteral;
import sat_codification.Sat;
import loading_sat_dataset.*;
import javafx.scene.chart.XYChart.Data;


public class ACO {

    public static List<Clause> clauses;
    public static List<Litteral> litterals;
    public static Litteral litteral;
    public static Clause clause;
    public static Sat sat;

   
    
    // Parametres ACO
    public static  double ALPHA = .3; // ALPHA
    public static void setALPHA(double aLPHA) {
		ALPHA = aLPHA;
	}
    public static  double BETA = .3; // BETA
	public static void setBETA(double bETA) {
		BETA = bETA;
	}
	public static  double V_RATE = .1; // Taux d'evaporation
	public static void setV_RATE(double v_RATE) {
		V_RATE = v_RATE;
	}
	public static  double I_PHEROMON = .1; // Pheromone initale
	public static void setI_PHEROMON(double i_PHEROMON) {
		I_PHEROMON = i_PHEROMON;
	}
	public static  int MAX_ITERATIONS = 50000; // Max Iterations
	public static void setMAX_ITERATIONS(int mAX_ITERATIONS) {
		MAX_ITERATIONS = mAX_ITERATIONS;
	}
	public static  int ANT_COUNT = 100; // Nombre des fourmis
	public static void setANT_COUNT(int aNT_COUNT) {
		ANT_COUNT = aNT_COUNT;
	}
	public static int NUMBER_OF_INSTENCES = 2;
    public static void setNUMBER_OF_INSTENCES(int nUMBER_OF_INSTENCES) {
    	NUMBER_OF_INSTENCES = nUMBER_OF_INSTENCES;
	}
    private static double DEFAULT_TIMEOUT=60000;
    public static void setDEFAULT_TIMEOUT(double dEFAULT_TIMEOUT) {
		DEFAULT_TIMEOUT = dEFAULT_TIMEOUT;
	}
    public static final double Q_0 = 0.005; // Q0

    public static long ACO_TOTAL_TIME = 0; // temps totale
    public static int[][] FITNESS; // matrice contiens le nombre des clauses satisfiable pour chaque literal

    public static List<Ant> ants; // liste des fourmis (Population)
    public static double[][] pheromon; // matrice du pheromone
	
	public static List<Data<Number, Number>> load_data_of_linechart_ACO =new ArrayList<Data<Number,Number>>();
	public static List<Data<Number, Number>> load_data_of_barchart_ACO =new ArrayList<Data<Number,Number>>();

	
    
   
    
    
   

	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {

    	runACOAlgo(); 
    }

	
	public static void runACOAlgo() {
		for (int x = 1; x <= NUMBER_OF_INSTENCES; x++) { // pour les 100 instnaces 
	        
			sat = Loading.LoadSat("/res/uf75-325/uf75-0"+x+".cnf"); // chargement de l'instance
	 
	        pheromon = new double[Loading.VAR_NUM][2]; // declaration de la matrice du pheromone
	        for (int i = 0; i < Loading.VAR_NUM; i++) { 
	        	// initialisation du pheromone pour chaque fourmi à 0.1 
	            pheromon[i][0] = pheromon[i][1] = I_PHEROMON;
	        }
	        
	        //ACO_TOTAL_TIME = System.currentTimeMillis(); // start time
	        double startTime = System.currentTimeMillis();
	        int fitness =0;
	        int iter=0;
			while (iter < MAX_ITERATIONS && fitness != 325 &&  System.currentTimeMillis() - startTime < DEFAULT_TIMEOUT) {
				iter++;	
	        	ants = new ArrayList<>(); // crée la liste des fourmis
	            for (int j = 0; j < ANT_COUNT; j++) {
	                ants.add(new Ant()); // ajout des nouvelles fourmis 
	            }
	
	            int best = 0; // best sat
	            Ant bestAnt = null; // best solution
	
	            for (Ant ant : ants) {
	                ant.begin(); // contruction d'une solution
	                if (ant.getFitness() > best) { // evaluation 
	                    bestAnt = ant; // mis a jours de la meilleur fourmis
	                    best = ant.getFitness();
	                }
	            }
	
	
	            for (int j = 0; j < Loading.VAR_NUM; j++) { // offline update
	                if (bestAnt.getValues()[j] == 0) {
	                    pheromon[j][0] = (1 - V_RATE) * pheromon[j][0] + V_RATE * (double) (FITNESS[j][0]);
	                    pheromon[j][1] = (1 - V_RATE) * pheromon[j][1];
	                } else {
	                    pheromon[j][0] = (1 - V_RATE) * pheromon[j][0];
	                    pheromon[j][1] = (1 - V_RATE) * pheromon[j][1] + V_RATE * (double) (FITNESS[j][1]);
	                }
	            }
	            fitness=bestAnt.getFitness();
	             
				
			}
			 System.out.print("Iteration " + iter + " : "+fitness);
			//stokage de donnees pour les graphe de statistique
            int satisfactions = fitness; 
            float accuracy = (float)fitness/Loading.CLAUSE_NUMBER*100;
          
            load_data_of_linechart_ACO.add(new Data<Number, Number>(x,accuracy));
            load_data_of_barchart_ACO.add(new Data<Number, Number>(x, iter));
            System.out.println("accuracy:"+accuracy+"    satisfactions:"+satisfactions);
            System.out.println("instence:"+x+"    iterarion:"+iter);
            ACO_TOTAL_TIME += (long) (System.currentTimeMillis()-startTime);
	        System.out.println(" --time = "+ACO_TOTAL_TIME+" ms");
    	}
	}

}
