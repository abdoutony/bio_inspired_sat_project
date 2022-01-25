package algo_gen_global_classes;

import java.util.ArrayList;
import java.util.List;



public class Population {
	
	 
	  public static final int sizeOfThePopulation = 80;

	    private List<Solution> listSolutions;
	    public Population() {
	        listSolutions = new ArrayList<>();
	        for (int i = 0; i < sizeOfThePopulation; i++) {
	            listSolutions.add(new Solution());
	        }
	        populationSorting();
	    }

	    public void populationSorting() {
	        listSolutions.sort(new FitnessEvaluation());
	    }

	
	    public List<Solution> getListSolutions() {
	        return listSolutions;
	    }

	    public void setListSolutions(List<Solution> listSolutions) {
	        this.listSolutions = listSolutions;
	        populationSorting();
	    }

	    
	    
	    public int getFitnessBestValue(){
	        return listSolutions.get(0).getValueFitness();
	    }

}
