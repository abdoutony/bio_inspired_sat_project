package application;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import a_star_algorithm.Algo_Body_AStar;
import a_star_algorithm.Run_Algo_AStar;
import ant_colony_optimization_algorithm.Algo_Body_Aco;
import ant_colony_optimization_algorithm.Run_Algo_Aco;
import authentication.LoginController;
import dfs_algorithm.Algo_Body_Dfs;
import dfs_algorithm.Run_Algo_Dfs;
import gen_algorithm.Algo_Body_Gen;
import gen_algorithm.Run_Algo_Gen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
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
    int initialValue = 15;
	@FXML 
	private Spinner<Integer> nbrInstancesDFS ;
	@FXML 
	private Slider timeOutDFS;
	@FXML 
	private ScatterChart<Number,Number> scatterChartDFS;
	@FXML 
	private LineChart<Number, Number> lineChartDFS;
	@FXML 
	private PieChart pieChartDFS;
	@FXML 
	private Label TotalExecutionTimeDFS;
	
	/* The variables used to deal with a star algorithm */
	@FXML 
	private Spinner<Integer> nbrInstancesASTAR ;
	@FXML 
	private Slider timeOutASTAR;
	@FXML 
	private ScatterChart<Number,Number> scatterChartASTAR;
	@FXML 
	private LineChart<Number, Number> lineChartASTAR;
	@FXML private PieChart pieChartASTAR;
	@FXML 
	private Label TotalExecutionTimeASTAR;
	
	/* The variables used to deal with genitic algorithm */
	@FXML 
	private Spinner<Integer> nbrInstancesGEN ;
	@FXML 
	private Spinner<Integer> populationSizeGEN ; 
	@FXML 
	private Spinner<Double> crossOverChanceGEN ; 
	@FXML 
	private Spinner<Double> mutationChanceGEN ;
	@FXML 
	private Spinner<Integer> selectionSizeGEN ;
	@FXML 
	private Slider timeOutGEN;
	@FXML 
	private ScatterChart<Number,Number> scatterChartGEN;
	@FXML 
	private LineChart<Number, Number> lineChartGEN;
	@FXML 
	private PieChart pieChartGEN;
	@FXML 
	private Label TotalExecutionTimeGEN;
	private int initialValuePopulation=75;
	private double initialValuedoubleCross = .95;
	private double initialValuedoubleMutations = .05;
	private int initialValueSelection = 30;
	
	
	/* The variables used to deal with ACO algorithm */

 	@FXML
    private Spinner<Double> alphaValueACO;
    @FXML
    private Spinner<Double> betaValueACO;
    @FXML
    private Spinner<Double> vrateValueACO;
    @FXML
    private Spinner<Integer> maxNbrIterationsACO;
    @FXML
    private Spinner<Integer> nbrInstancesACO;
    @FXML
    private Spinner<Integer> nbrAntsACO;
    @FXML
    private Slider timeOutACO;
    @FXML
    private Label TotalExecutionTimeACO;
    @FXML
    private ScatterChart<Number,Number> scatterChartACO;
    @FXML
    private LineChart<Number, Number> lineChartACO;
    @FXML 
    private PieChart pieChartACO;
	double alphaInitialValue=0.3;
	double betaInitialValue=0.1;
	double vrateInitialValue=0.1;
	int nbrMaxIterationsInitialValue=50000;
	int nbrInstancesInitialValue=10;
	int nbrAntsInitialValue=100;
    
    @FXML
    private LineChart<Number,Number> lineChartComparaison;

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
			Run_Algo_Dfs.instancesToUse = Integer.valueOf(nbrInstancesDFS.getValue());
			Algo_Body_Dfs.timeOutDefaultValue = (long)timeOutDFS.getValue();
			/* the function that runs the DFS Algorithm */
			Run_Algo_Dfs.dfsAlgorithmRun();
			/* Loading the graphs to show the results after executing the DFS Algorithm */
			LoadingCharts.scatterChartDFS(scatterChartDFS,seriesList);
			LoadingCharts.lineChartDFS(lineChartDFS, seriesList);
			LoadingCharts.pieChartDFS(pieChartDFS, seriesList);
			/* calculating and setting the total execution time of the DFS Algorithm */
			Double execTime = (double)Run_Algo_Dfs.totalTimeDFS/60000;
			TotalExecutionTimeDFS.setText(new DecimalFormat("##.##").format(execTime)+" min");
		}
		
	
		
		/*  /////////////////////A Star Algorithm ///////////////////////////////// */
		
		@FXML
		public void executeASTAR(ActionEvent e) {
			Run_Algo_AStar.instancesToUse = Integer.valueOf(nbrInstancesDFS.getValue());
			//System.out.println(Load.NUMBER_OF_INSTANCES_TO_USE);
			Algo_Body_AStar.timeOutDefaultValue = (long)timeOutASTAR.getValue();
			//System.out.println(Algorithmes.DEFAULT_TIMEOUT);		
			Run_Algo_AStar.astarAlgorithmRun();
			/* Loading the graphs to show the results after executing the DFS Algorithm */
			LoadingCharts.scatterChartASTAR(scatterChartASTAR,seriesList);
			LoadingCharts.lineChartASTAR(lineChartASTAR, seriesList);
			LoadingCharts.pieChartASTAR(pieChartASTAR, seriesList);	
			/* calculating and setting the total execution time of the DFS Algorithm */
			Double execTime = (double)Run_Algo_AStar.totalTimeASTAR/60000;
			TotalExecutionTimeASTAR.setText(new DecimalFormat("##.##").format(execTime)+" min");
		}
		 
		/*  ///////////////////// Gentic Algorithm ///////////////////////////////// */
		 
		 @FXML
		 void executeGen(ActionEvent event) {
			   Algo_Body_Gen.timeOutDefaultValue=timeOutGEN.getValue();
			   Algo_Body_Gen.nbrInstancesGen = Integer.valueOf(nbrInstancesGEN.getValue());
			   Algo_Body_Gen.chanceOfCrossOver=Double.valueOf(crossOverChanceGEN.getValue());
			   Algo_Body_Gen.chanceOfMutation =Double.valueOf(mutationChanceGEN.getValue());
			   Algo_Body_Gen.sizeOfTheSelection = Integer.valueOf(selectionSizeGEN.getValue());
			   Run_Algo_Gen.genAlgorithmRun();
				LoadingCharts.scatterChartGEN(scatterChartGEN,seriesList);
				LoadingCharts.lineChartGEN(lineChartGEN, seriesList);
				LoadingCharts.pieChartGEN(pieChartGEN, seriesList);
				
				Double execTime = (double)Run_Algo_Gen.totalTimeGEN/60000;
				TotalExecutionTimeGEN.setText(new DecimalFormat("##.##").format(execTime)+" min");
		 }
		 
			/*  ///////////////////// Ant colony optimization Algorithm ///////////////////////////////// */

	    @FXML
	    void executeAco(ActionEvent envent) {
	    	Algo_Body_Aco.vrateValue= Double.valueOf(vrateValueACO.getValue());
	    	Algo_Body_Aco.alphaValue = Double.valueOf(alphaValueACO.getValue());
	    	Algo_Body_Aco.betaValue = Double.valueOf(betaValueACO.getValue());
	    	Algo_Body_Aco.nbrMaxIterations = Integer.valueOf(maxNbrIterationsACO.getValue());
	    	Algo_Body_Aco.nbtInstancesACO = Integer.valueOf(nbrInstancesACO.getValue());
	    	Algo_Body_Aco.timeOutDefaultValue = timeOutACO.getValue();
	    	Run_Algo_Aco.acoAlgorithmRun();
	    	LoadingCharts.scatterChartACO(scatterChartACO,seriesList);
			LoadingCharts.lineChartACO(lineChartACO, seriesList);
			LoadingCharts.pieChartACO(pieChartACO, seriesList);	
	    	Double execTime = (double)Run_Algo_Aco.totalTimeAco/60000;
			TotalExecutionTimeACO.setText(new DecimalFormat("##.##").format(execTime)+" min");
	    }
	    
	    @FXML
		void executeComparaison(ActionEvent event) {
			
			lineChartComparaison.getData().addAll(seriesList);

		}
		 
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, initialValue);
		nbrInstancesDFS.setValueFactory(svf);
		nbrInstancesASTAR.setValueFactory(svf);
		
		nbrInstancesGEN.setValueFactory(svf);
		
		SpinnerValueFactory<Integer> svf_selection = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, initialValueSelection);
		selectionSizeGEN.setValueFactory(svf_selection);
		
		SpinnerValueFactory<Integer> svf_population = new SpinnerValueFactory.IntegerSpinnerValueFactory(75, 80, initialValuePopulation);
		populationSizeGEN.setValueFactory(svf_population);
		
		SpinnerValueFactory<Double> svf_double_cross = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 2, initialValuedoubleCross,0.01);
		crossOverChanceGEN.setValueFactory(svf_double_cross);
		
		SpinnerValueFactory<Double> svf_double_mutation = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 2, initialValuedoubleMutations,0.01);
		mutationChanceGEN.setValueFactory(svf_double_mutation);		 
		 
		
		SpinnerValueFactory<Double> spfAlphaACS = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1, alphaInitialValue,0.001);
		alphaValueACO.setValueFactory(spfAlphaACS);
		SpinnerValueFactory<Double> spfBetaACS = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1, betaInitialValue,0.01);
		betaValueACO.setValueFactory(spfBetaACS);
		SpinnerValueFactory<Double> spfVrateACS = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1, vrateInitialValue,0.01);
		vrateValueACO.setValueFactory(spfVrateACS);
		nbrInstancesACO.setValueFactory(svf);
		SpinnerValueFactory<Integer> svfMaxIter = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100000, nbrMaxIterationsInitialValue);
		maxNbrIterationsACO.setValueFactory(svfMaxIter);
		SpinnerValueFactory<Integer> svfNbrAnt = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, nbrAntsInitialValue);
		nbrAntsACO.setValueFactory(svfNbrAnt);

		
	}
}
