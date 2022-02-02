package dfs_algorithm;
import algos_dfs_astar_global_classes.Run;
public class Run_Algo_Dfs extends Run  {
 
    public static long totalTimeDFS = 0;
    
    public static void dfsAlgorithmRun() {
    	Run.run("dfs");
    	System.out.println("DFS Total Time = " + totalTimeDFS + "ms");
	}


	
}
