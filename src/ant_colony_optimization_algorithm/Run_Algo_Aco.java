package ant_colony_optimization_algorithm;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import sat_codification.Clause;
import sat_codification.Litteral;
import sat_codification.Sat;
import loading_sat_dataset.*;
import javafx.scene.chart.XYChart.Data;


public class Run_Algo_Aco {


    public static Sat sat;
    public static long totalTimeAco = 0;
    

		
	public static void acoAlgorithmRun() {
		for (int x = 1; x <= Algo_Body_Aco.nbtInstancesACO; x++) {
			sat = Loading.LoadFile("/res/uf75-325/uf75-0"+x+".cnf");
	       Algo_Body_Aco.algoBody(x);
    	}
	}

}
