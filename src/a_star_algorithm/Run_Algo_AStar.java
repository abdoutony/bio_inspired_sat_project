package a_star_algorithm;

import algos_dfs_astar_global_classes.Run;

public class Run_Algo_AStar extends Run {

    public static long totalTimeASTAR = 0;
    public static void astarAlgorithmRun() {
    	Run.run("astar");
    	System.out.println("ASTAR Total Time = " + totalTimeASTAR + "ms");
    	
    }
}
