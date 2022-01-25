package loading_charts;

import a_star_algorithm.Algo_Body_AStar;
import dfs_algorithm.Algo_Body_Dfs;
import gen_algorithm.Algo_Body_Gen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class LoadingCharts {
	
	/*///////////////// DFS CHARTS //////////////*/
	
	public static void scatterChartDFS(ScatterChart<Number,Number> scatterChartDFS,ObservableList<Series<Number,Number>> seriesList ) {
		/* declare the scatter chart that contains the accuracy of execution for each instance of DFS Algorithm */
		XYChart.Series<Number, Number> algoDFSAccuracySerie = new XYChart.Series<Number, Number>();
		algoDFSAccuracySerie.setName("The Accuracy of execution for each instance");
		
		for (XYChart.Data<Number,Number> data : Algo_Body_Dfs.listAccuracyForEachInstanceDFS) {
			/* fill the series of the accuracies of each instance */
			algoDFSAccuracySerie.getData().add(data);
		}
		
		/* fill the chart of the serie that contains the data */
		scatterChartDFS.getData().add(algoDFSAccuracySerie);
		seriesList.add(algoDFSAccuracySerie);
		//comparatif_line_chart.getData().add(serie);
		
	}
	public static void lineChartDFS(LineChart<Number, Number> lineChartDFS,ObservableList<Series<Number,Number>> seriesList) {
		/* declare the line chart that contains the  execution time for each instance of DFS Algorithm */
		XYChart.Series<Number, Number> algoDFSExecutionTimeSerie = new XYChart.Series<Number, Number>();
		algoDFSExecutionTimeSerie.setName("The execution time for each instence in ms");
		
		for (Data<Number, Number> data : Algo_Body_Dfs.listSatisfactionDFSData) {
			/* fill the series of the eecution time of each instance */
		   algoDFSExecutionTimeSerie.getData().add(data);
		}
		/* fill the chart of the serie that contains the data */
		lineChartDFS.getData().add(algoDFSExecutionTimeSerie);
		Algo_Body_Dfs.listSatisfactionDFSData.clear();
	}
	
	public static void pieChartDFS(PieChart pieChartDFS,ObservableList<Series<Number,Number>> seriesList) {
		int i = 0;
		float global_accuracy=0;
		for (Data<Number, Number> data : Algo_Body_Dfs.listAccuracyForEachInstanceDFS) {
			global_accuracy = global_accuracy + Float.parseFloat(data.getYValue().toString());
			i++;
		}
		global_accuracy = global_accuracy/i;
		ObservableList<PieChart.Data> ol = FXCollections.observableArrayList(
		new PieChart.Data("Satisfied Instances (Global Accuracy): %"+global_accuracy,global_accuracy),
		new PieChart.Data("Unsatisfied Instances (Global Accuracy): %"+(100-global_accuracy),100-global_accuracy));
		pieChartDFS.setData(ol);
		Algo_Body_Dfs.listAccuracyForEachInstanceDFS.clear();
	}
	
	
	/*////////////////////////////////// A star charts ///////////////////////// */
	
	public static void scatterChartASTAR(ScatterChart<Number,Number> scatterChartDFS,ObservableList<Series<Number,Number>> seriesList ) {
		XYChart.Series<Number, Number> serieAstar = new XYChart.Series<Number, Number>();
		serieAstar.setName("accuracy of Astar");
	
		for (XYChart.Data<Number,Number> data : Algo_Body_AStar.listAccuracyForEachInstanceASTAR) {
			serieAstar.getData().add(data);
		}
		
		scatterChartDFS.getData().add(serieAstar);
		seriesList.add(serieAstar);
	}
	
	public static void lineChartASTAR(LineChart<Number, Number> lineChartASTAR,ObservableList<Series<Number,Number>> seriesList) {
		XYChart.Series<Number, Number> s = new XYChart.Series<Number, Number>();
		s.setName("Time exe for each instence in ms (Astar)");
	
		for (Data<Number, Number> data : Algo_Body_AStar.listSatisfactionASTARData) {
			
			s.getData().add(data);
		}
		
		lineChartASTAR.getData().add(s);
		Algo_Body_AStar.listSatisfactionASTARData.clear();
	}
	
	public static void pieChartASTAR(PieChart pieChartASTAR,ObservableList<Series<Number,Number>> seriesList) {
		int i = 0;
		float global_accuracy=0;
		for (Data<Number, Number> data : Algo_Body_AStar.listAccuracyForEachInstanceASTAR) {
			 global_accuracy = global_accuracy + Float.parseFloat(data.getYValue().toString());
			 i++;
		}
		global_accuracy = global_accuracy/i;
		ObservableList<PieChart.Data> ol = FXCollections.observableArrayList(
				new PieChart.Data("accuracy of global satisfed instences: %"+global_accuracy,global_accuracy),
				new PieChart.Data("accuracy of global no satisfed instences: %"+(100-global_accuracy),100-global_accuracy));
		pieChartASTAR.setData(ol);
		Algo_Body_AStar.listAccuracyForEachInstanceASTAR.clear();
		
	}
	
		/*////////////////////////////////// Gentic Algorithm charts ///////////////////////// */
	
	public static void scatterChartGEN(ScatterChart<Number,Number> scatterChartGEN,ObservableList<Series<Number,Number>> seriesList ) {
		XYChart.Series<Number, Number> serieGEN = new XYChart.Series<Number, Number>();
		serieGEN.setName("accuracy of GEN");
	
		for (XYChart.Data<Number,Number> data : Algo_Body_Gen.listAccuracyForEachInstanceGEN) {
			serieGEN.getData().add(data);
		}
		
		scatterChartGEN.getData().add(serieGEN);
		seriesList.add(serieGEN);
	}
	
	public static void lineChartGEN(LineChart<Number, Number> lineChartGEN,ObservableList<Series<Number,Number>> seriesList) {
		XYChart.Series<Number, Number> serieGen = new XYChart.Series<Number, Number>();
		serieGen.setName("Time exe for each instence in ms (GEN)");
	
		for (Data<Number, Number> data : Algo_Body_Gen.listSatisfactionGENData) {
			
			serieGen.getData().add(data);
		}
		
		lineChartGEN.getData().add(serieGen);
		Algo_Body_Gen.listSatisfactionGENData.clear();
	}
	
	public static void pieChartGEN(PieChart pieChartGEN,ObservableList<Series<Number,Number>> seriesList) {
		int i = 0;
		float global_accuracy=0;
		for (Data<Number, Number> data : Algo_Body_Gen.listAccuracyForEachInstanceGEN) {
			 global_accuracy = global_accuracy + Float.parseFloat(data.getYValue().toString());
			 i++;
		}
		global_accuracy = global_accuracy/i;
		ObservableList<PieChart.Data> ol = FXCollections.observableArrayList(
				new PieChart.Data("accuracy of global satisfed instences: %"+global_accuracy,global_accuracy),
				new PieChart.Data("accuracy of global no satisfed instences: %"+(100-global_accuracy),100-global_accuracy));
		pieChartGEN.setData(ol);
		Algo_Body_Gen.listAccuracyForEachInstanceGEN.clear();
		
	}

}
