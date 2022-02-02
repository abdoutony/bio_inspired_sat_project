package gen_algorithm;

import gen_algorithm.Algo_Body_Gen;
import loading_sat_dataset.Loading;
import sat_codification.Sat;

public class Run_Algo_Gen {
    public static Sat sat;
    public static long totalTimeGEN = 0; 
    
    
    public Run_Algo_Gen() {
    	
    }
	
	  public static void genAlgorithmRun() {
	  
		 
		  for (int x = 1; x <= Algo_Body_Gen.nbrInstancesGen; x++) { 
			    System.out.println("Instance number: " + x);
	            sat = Loading.LoadFile("/src/res/uf75-325/uf75-0" + x + ".cnf");
	            Algo_Body_Gen.algoBody(x);
	           
	  }
		 System.out.println("Genitic algorithm Total Time = " + totalTimeGEN + "ms");
	
	  }
	  
	
}
