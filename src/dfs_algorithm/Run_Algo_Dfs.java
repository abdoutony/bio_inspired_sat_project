package dfs_algorithm;

import dfs_algorithm.Algo_Body_Dfs;
import loading_sat_dataset.Loading;

public class Run_Algo_Dfs {
    
	public static int NUMBER_OF_INSTANCES_TO_USE = 3;
    public static long DEPTH_FIRST_TOTAL_TIME = 0;
    
    
    public static void runAlgoDFS() {
		Algo_Body_Dfs algo = new Algo_Body_Dfs();

        System.out.println("--------------------------------------------\nProfondeur\n--------------------------------------------");
        for (int x = 1; x <= NUMBER_OF_INSTANCES_TO_USE; x++) {
            System.out.println("Instance " + x);
            Loading.sat = Loading.LoadSat("/res/uf75-325/uf75-0" + x + ".cnf");
            algo.depthFirstAlgorithm(x);
            
        }
        System.out.println("Total Profondeur Time = " + DEPTH_FIRST_TOTAL_TIME + "ms");
	}
}
