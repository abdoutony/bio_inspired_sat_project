package algos_dfs_astar_global_classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import algos_dfs_astar_global_classes.Solution;
import sat_codification.Sat;

public class Solution {
	
	private int[] arraySolution;
    private int valueSatisfaction;
    private int level;
    private int index;
    public Solution(int[] arraySolution, int level, int value) {
        this.arraySolution = arraySolution.clone();
        this.level = level;
        this.arraySolution[level] = value;
    }

    public Solution(int[] arraySolution, int index, Sat sat, int n) {
        this.arraySolution = arraySolution.clone();
        this.index = index;
        if(this.arraySolution[index] == 0){
            this.arraySolution[index] = 1;
        } else {
            this.arraySolution[index] = 0;
        }
        this.valueSatisfaction = sat.satisfiedClauses(this);
    }
    
    public Solution(int[] values, int index, Sat sat) {
        this.arraySolution = values.clone();
        this.valueSatisfaction = sat.satisfiedClauses(this);
    }

    public int[] getArraySolution() {
        return arraySolution;
    }

    public int getLevel() {
        return level;
    }

    public int getValueSatisfaction() {
        return valueSatisfaction;
    }
    
    public void getDepthNodes(Stack<Solution> pile) {
        pile.push(new Solution(arraySolution, level + 1, 1));
        pile.push(new Solution(arraySolution, level + 1, 0));
    }
    
    public void getKidsAStar(Stack<Solution> pile, int size, Sat sat) {
        List<Solution> sols = new ArrayList<Solution>();
        Solution sol;
        for (int i = 0; i < size; i++) {
            if (i != index) {
                sol = new Solution(arraySolution, i, sat, 0);
                if (sols.isEmpty() && sol.getValueSatisfaction() >= valueSatisfaction) {
                    sols.add(sol);
                } else if (sol.getValueSatisfaction() >= valueSatisfaction) {
                    for (int j = 0; j < sols.size(); j++) {
                        if (sol.getValueSatisfaction() >= sols.get(j).getValueSatisfaction()) {
                            sols.add(j, sol);
                            break;
                        }
                    }
                }
            }
        }
        for (int i = sols.size()-1; i >= 0; i--) {
            pile.push(sols.get(i));
        }
    }
    
}
