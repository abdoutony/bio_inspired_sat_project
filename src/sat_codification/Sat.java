package sat_codification;

import java.util.List;

import algo_aco_global_classes.AgentAnt;
import algos_dfs_astar_global_classes.Solution;
import sat_codification.Clause;
import sat_codification.Litteral;

public class Sat {
     
	 private List<Clause> listClauses;

	    public Sat(List<Clause> listClauses) {
	        this.listClauses = listClauses;
	    }

	    public List<Clause> getClauses() {
	        return this.listClauses;
	    }

	    public void setClauses(List<Clause> listClauses) {
	        this.listClauses = listClauses;
	    }

	    public boolean satisfied(Solution solution) {
	        int[] values = solution.getArraySolution();
	        boolean sat = false;
	        for (Clause c : listClauses) {
	            sat = false;
	            for(Litteral l : c.getListLitterals()){
	                if(l.getLitteralValue(values[l.getLitName()-1]) == 1){
	                    sat = true;
	                }
	            }
	            if(!sat)
	                return false;
	        }
	        return true;
	    }
	    
	    //////////////////////////////////////////  ANT COLONY OPTIMISATION 
	    public boolean satisfied(AgentAnt solution) {
	        int[] values = solution.getArraySolution();
	        boolean sat = false;
	        for (Clause c : listClauses) {
	            sat = false;
	            for(Litteral l : c.getListLitterals()){
	                if(l.getLitteralValue(values[l.getLitName()-1]) == 1){
	                    sat = true;
	                }
	            }
	            if(!sat)
	                return false;
	        }
	        return true;
	    }
	    
	    public int satisfiedClauses(Solution solution) {
	        int satisfactions = 0;
	        int[] values = solution.getArraySolution();
	        for (Clause c : listClauses) {
	            for(Litteral l : c.getListLitterals()){
	                if(l.getLitteralValue(values[l.getLitName()-1]) == 1){
	                    satisfactions ++;
	                    break;
	                }
	            }
	        }
	        return satisfactions;
	    }
	    
	    
	    ////////////////////////////////////////////////// BFS AND Astar
	    
	    public int satisfiedClauses(int values[]) {
	        int satisfactions = 0;
	        for (Clause c : listClauses) {
	            for(Litteral l : c.getListLitterals()){
	                if(l.getLitteralValue(values[l.getLitName()-1]) == 1){
	                    satisfactions ++;
	                    break;
	                }
	            }
	        }
	        return satisfactions;
	    }
}
