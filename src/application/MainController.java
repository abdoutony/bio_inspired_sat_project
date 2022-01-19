package application;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import a_star_algorithm.Algo_Body_AStar;
import a_star_algorithm.Run_Algo_AStar;
import authentication.LoginController;
import dfs_algorithm.Algo_Body_Dfs;
import dfs_algorithm.Run_Algo_Dfs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import loading_charts.*;


public class MainController implements Initializable {	 
	public MainController() {}
	 /* Create an empty series of type observable array list */
    private ObservableList<Series<Number,Number>> seriesList = FXCollections.observableArrayList();
    
	/*The variable used to deal with Depth first search algorithm*/
    int initialValue = 10;
	@FXML private Spinner<Integer> nbrInstancesDFS ;
	@FXML private Slider timeOutDFS;
	@FXML private ScatterChart<Number,Number> scatterChartDFS;
	@FXML private LineChart<Number, Number> lineChartDFS;
	@FXML private PieChart pieChartDFS;
	@FXML private Label TotalExecutionTimeDFS;
	
	/* The variables used to deal with a star algorithm */
	@FXML private Spinner<Integer> nbrInstancesASTAR ;
	@FXML private Slider timeOutASTAR;
	@FXML private ScatterChart<Number,Number> scatterChartASTAR;
	@FXML private LineChart<Number, Number> lineChartASTAR;
	@FXML private PieChart pieChartASTAR;
	@FXML private Label TotalExecutionTimeASTAR;
     
	/* Variable to trigger logout button */
    @FXML
    private Button LogoutBtn;
    
    
    /* function to execute logout*/
	@FXML
	public void executeLogout(ActionEvent event) {
		LoginController login = new LoginController();
		login.logout();
		this.loadScreen(LogoutBtn, "welcome_scene.fxml");
	}
	/*function to load a given screen*/
	 public void loadScreen(Button button,String scene){  
	
		  try {
		  Stage s = (Stage) button.getScene().getWindow();
		  s.close();
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scene));
		            Parent root = (Parent) fxmlLoader.load();
		            Stage stage = new Stage();
		            stage.setScene(new Scene(root));  
		            stage.show();
		    } catch(Exception e) {
		       e.printStackTrace();
		      }
		 }
	
	 
		/* function to execute depth first search algorithm */
		@FXML
		public void executeDFS(ActionEvent e) {
			Run_Algo_Dfs.INSTANCES_TO_USE = Integer.valueOf(nbrInstancesDFS.getValue());
			Algo_Body_Dfs.TIMEOUT_DEFAULT_VALUE = (long)timeOutDFS.getValue();
			/* the function that runs the DFS Algorithm */
			Run_Algo_Dfs.dfsAlgorithmRun();
			/* Loading the graphs to show the results after executing the DFS Algorithm */
			LoadingCharts.scatterChartDFS(scatterChartDFS,seriesList);
			LoadingCharts.lineChartDFS(lineChartDFS, seriesList);
			LoadingCharts.pieChartDFS(pieChartDFS, seriesList);
			/* calculating and setting the total execution time of the DFS Algorithm */
			Double execTime = (double)Run_Algo_Dfs.TOTAL_TIME_DFS/60000;
			TotalExecutionTimeDFS.setText(new DecimalFormat("##.##").format(execTime)+" min");
		}
		
	
		
		/*  /////////////////////A Star Algorithm ///////////////////////////////// */
		
		@FXML
		public void executeASTAR(ActionEvent e) {
			Run_Algo_AStar.INSTANCES_TO_USE = Integer.valueOf(nbrInstancesDFS.getValue());
			//System.out.println(Load.NUMBER_OF_INSTANCES_TO_USE);
			Algo_Body_AStar.TIMEOUT_DEFAULT_VALUE = (long)timeOutASTAR.getValue();
			//System.out.println(Algorithmes.DEFAULT_TIMEOUT);		
			Run_Algo_AStar.astarAlgorithmRun();
			/* Loading the graphs to show the results after executing the DFS Algorithm */
			LoadingCharts.scatterChartASTAR(scatterChartASTAR,seriesList);
			LoadingCharts.lineChartASTAR(lineChartASTAR, seriesList);
			LoadingCharts.pieChartASTAR(pieChartASTAR, seriesList);	
			/* calculating and setting the total execution time of the DFS Algorithm */
			Double execTime = (double)Run_Algo_AStar.TOTAL_TIME_ASTAR/60000;
			TotalExecutionTimeASTAR.setText(new DecimalFormat("##.##").format(execTime)+" min");
		}
	
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, initialValue);
		nbrInstancesDFS.setValueFactory(svf);
		nbrInstancesASTAR.setValueFactory(svf);
		
	}
}
