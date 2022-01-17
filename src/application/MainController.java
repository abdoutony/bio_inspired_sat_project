package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;


public class MainController implements Initializable {	 
	public MainController() {}
	 // Create empty series
    private ObservableList<Series<Number,Number>> seriesList = FXCollections.observableArrayList();
    
	////////////////////////////////////////     bfs algo variables
    int initialValue = 10;
	@FXML private Spinner<Integer> nbrInstancesDFS ;
	@FXML private Slider timeOutDFS;
	@FXML private LineChart<Number,Number> lineChartDFS;
	@FXML private AreaChart<Number, Number> barChartDFS;
	@FXML private PieChart pieChartDFS;
	@FXML private Label TotalExecutionTimeDFS;

    @FXML
    private Button LogoutBtn;
    
	@FXML
	public void proccessLogout(ActionEvent event) {
		LoginController login = new LoginController();
		login.logout();
		this.loadScreen(LogoutBtn, "welcome_scene.fxml");
	}
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
	
	 
		/////////////////////////////////////////////// afs
		@FXML
		public void processDFS(ActionEvent e) {
			Run_Algo_Dfs.NUMBER_OF_INSTANCES_TO_USE = Integer.valueOf(nbrInstancesDFS.getValue());
			//System.out.println(Load.NUMBER_OF_INSTANCES_TO_USE);
			Algo_Body_Dfs.DEFAULT_TIMEOUT = (long)timeOutDFS.getValue();
			//System.out.println(Algorithmes.DEFAULT_TIMEOUT);
			
			
			Run_Algo_Dfs.runAlgoDFS();
			
			//
			loadLineDataDFS();
			loadBarDataDFS();
			loadPieDataDFS();
			
			TotalExecutionTimeDFS.setText("Time: "+String.valueOf((double)Run_Algo_Dfs.DEPTH_FIRST_TOTAL_TIME/60000)+" min");
		}
		
		private void loadLineDataDFS() {
			
			
			XYChart.Series<Number, Number> serieBFS = new XYChart.Series<Number, Number>();
			serieBFS.setName("accuracy of DFS");
		
			for (XYChart.Data<Number,Number> data : Algo_Body_Dfs.list_of_data_accuracyDFS) {
				serieBFS.getData().add(data);
			}
			
			lineChartDFS.getData().add(serieBFS);
			seriesList.add(serieBFS);
			//comparatif_line_chart.getData().add(serie);
			
		}
		private void loadBarDataDFS() {
			
			XYChart.Series<Number, Number> s = new XYChart.Series<Number, Number>();
			s.setName("Time exe for each instence in ms (BFS)");
			
			for (Data<Number, Number> data : Algo_Body_Dfs.list_of_data_satisfactionDFS) {
			   s.getData().add(data);
			}
			
			barChartDFS.getData().add(s);
			Algo_Body_Dfs.list_of_data_satisfactionDFS.clear();
		}
		
		private void loadPieDataDFS() {
			int i = 0;
			float global_accuracy=0;
			for (Data<Number, Number> data : Algo_Body_Dfs.list_of_data_accuracyDFS) {
			global_accuracy = global_accuracy + Float.parseFloat(data.getYValue().toString());
			i++;
			}
			global_accuracy = global_accuracy/i;
			ObservableList<PieChart.Data> ol = FXCollections.observableArrayList(
			new PieChart.Data("accuracy of global satisfed instences: %"+global_accuracy,global_accuracy),
			new PieChart.Data("accuracy of global no satisfed instences: %"+(100-global_accuracy),100-global_accuracy));
			pieChartDFS.setData(ol);
			Algo_Body_Dfs.list_of_data_accuracyDFS.clear();
		}
	
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, initialValue);
		nbrInstancesDFS.setValueFactory(svf);
		
	}
}
