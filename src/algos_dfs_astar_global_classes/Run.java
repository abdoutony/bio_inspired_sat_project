package algos_dfs_astar_global_classes;

import a_star_algorithm.Algo_Body_AStar;
import dfs_algorithm.Algo_Body_Dfs;
import loading_sat_dataset.Loading;

public class Run {
	public static int INSTANCES_TO_USE = 3;
	
	public static void run(String algorithm,long totaltime) {
		
		 for (int x = 1; x <= INSTANCES_TO_USE; x++) {
	            System.out.println("Instance number: " + x);
	            Loading.sat = Loading.LoadSat("/res/uf75-325/uf75-0" + x + ".cnf");
	            if(algorithm=="dfs") {
	            	Algo_Body_Dfs algo = new Algo_Body_Dfs();
	            	 algo.dfsAlgorithmBody(x);	
	            }
	            if(algorithm=="astar") {
	            	Algo_Body_AStar algo= new Algo_Body_AStar();
	            	algo.aStarAlgorithm(x);
	            }
	           
	            
	     }
		 if(algorithm=="dfs") {
			 System.out.println("DFS Total Time = " + totaltime + "ms");
		 }else if(algorithm=="astar") {
			 System.out.println("A Star Total Time = " + totaltime + "ms"); 
		 }
		
	}
}
