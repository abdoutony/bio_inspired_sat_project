package dfs_algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import sat_codification.Sat;

public class Solution {
	
	private int[] values;
    private int lvl;
    private int index;
    private int satisfaction;

    //constructors
    public Solution(int[] values, int lvl, int value) {
        this.values = values.clone();
        this.lvl = lvl;
        this.values[lvl] = value;
    }

    public Solution(int[] values, int index, Sat sat, int n) {
        this.values = values.clone();
        this.index = index;
        if(this.values[index] == 0){
            this.values[index] = 1;
        } else {
            this.values[index] = 0;
        }
        this.satisfaction = sat.satisfiedClauses(this);
    }
    
    public Solution(int[] values, int index, Sat sat) {
        this.values = values.clone();
        this.satisfaction = sat.satisfiedClauses(this);
    }

    //geters 
    public int[] getValues() {
        return values;
    }

    public int getLvl() {
        return lvl;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void getKidsProfondeur(Stack<Solution> pile) {
        pile.push(new Solution(values, lvl + 1, 1));
        pile.push(new Solution(values, lvl + 1, 0));
    }

   

}
