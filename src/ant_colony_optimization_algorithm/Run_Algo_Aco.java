package ant_colony_optimization_algorithm;

import sat_codification.Sat;
import loading_sat_dataset.*;


public class Run_Algo_Aco {


    public static Sat sat;
    public static long totalTimeAco = 0;
    

		
	public static void acoAlgorithmRun() {
		for (int x = 1; x <= Algo_Body_Aco.nbtInstancesACO; x++) {
			 System.out.println("Instance number: " + x);
			sat = Loading.LoadFile("/res/uf75-325/uf75-0"+x+".cnf");
	       Algo_Body_Aco.algoBody(x);
    	}
		System.out.println("Ant colony optimization algorithm Total Time = " + totalTimeAco + "ms");
		
	}

}
