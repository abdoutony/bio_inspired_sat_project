package algo_gen_global_classes;

import java.util.Arrays;
import java.util.Random;

import gen_algorithm.Run_Algo_Gen;
import loading_sat_dataset.Loading;

public class Solution {

    private int[] arraySolution;
    private int valueFitness;

    public Solution() {
        Random random = new Random();
        arraySolution = new int[Loading.numberOfVariables];
        for (int i = 0; i < Loading.numberOfVariables; i++) {
            arraySolution[i] = random.nextInt(2);
        }
        valueFitness = Run_Algo_Gen.sat.satisfiedClauses(arraySolution);
    }
    
    public Solution(int[] values) {
        this.arraySolution = values.clone();
        valueFitness = Run_Algo_Gen.sat.satisfiedClauses(values);
    }

 
    public int[] getarraySolution() {
        return arraySolution;
    }

    public void setArraySolution(int[] arraySolution) {
        this.arraySolution = arraySolution;
    }


    public int getValueFitness() {
        return valueFitness;
    }


    public void setValueFitness(int valueFitness) {
        this.valueFitness = valueFitness;
    }
    
    public String toString(){
        return Arrays.toString(arraySolution);
    }
}