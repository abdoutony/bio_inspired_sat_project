package algos_dfs_astar_global_classes;

import a_star_algorithm.Algo_Body_AStar;
import dfs_algorithm.Algo_Body_Dfs;
import loading_sat_dataset.Loading;

public class Run {
	public static int instancesToUse = 3;
	
	public static void run(String algorithm) {
		
		 for (int x = 1; x <= instancesToUse; x++) {
	            System.out.println("Instance number: " + x);
	            Loading.sat = Loading.LoadFile("/res/uf75-325/uf75-0" + x + ".cnf");
	            if(algorithm=="dfs") {
	            	Algo_Body_Dfs algo = new Algo_Body_Dfs();
	            	 algo.algoBody(x);	
	            }
	            if(algorithm=="astar") {
	            	Algo_Body_AStar algo= new Algo_Body_AStar();
	            	algo.algoBody(x);
	            }
	           
	            
	     }
		
		
	}
}
