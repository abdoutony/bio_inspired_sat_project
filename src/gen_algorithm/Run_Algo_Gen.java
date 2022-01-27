package gen_algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algo_gen_global_classes.GenAlgoController;
import gen_algorithm.Algo_Body_Gen;
import javafx.scene.chart.XYChart.Data;
import loading_sat_dataset.Loading;
import sat_codification.Sat;

public class Run_Algo_Gen {
    public static Sat sat;
    public static long totalTimeGEN = 0; 
    
    
    public Run_Algo_Gen() {
    	
    }
	
	  public static void genAlgorithmRun() {
	  
		 
		  for (int x = 1; x <= Algo_Body_Gen.nbrInstancesGen; x++) { 
	            sat = Loading.LoadSat("/res/uf75-325/uf75-0" + x + ".cnf");
	            Algo_Body_Gen.algoBody(x);
	           
	  }
		 // System.out.println("Gen Total Time = " + TOTAL_TIME_GEN + "ms");
	
	  }
	  
	
}
