package algo_gen_global_classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import loading_sat_dataset.Loading;

public class GenAlgoController {
	 
	    /* run Cross Over function  */
	    public static void runCrossOver(int permanentSolutions, double chanceOfCrossOver,Population population,int sizeOfTheSelection) {
	        List<Solution> newPopulation = new ArrayList<>();
	        Solution parent1, parent2;
	        for (int i = 0; i < permanentSolutions; i++) {
	        	/* save parent solutions */
	            newPopulation.add(population.getListSolutions().get(i));
	        }
	        for (int i = permanentSolutions; i < Population.sizeOfThePopulation; i++) { 
	            if (Math.random() < chanceOfCrossOver) {
	            	
	                parent1 = solutionsSelection(sizeOfTheSelection,population).get(0); 
	                parent2 = solutionsSelection(sizeOfTheSelection,population).get(0); 
	                /* cross over between the best solutions of parent1 and parent2 */
	                newPopulation.add(solutionCrossing(parent1, parent2));
	            } else {
	            	/* add the new population */
	                newPopulation.add(population.getListSolutions().get(i));
	            }
	        }
	        /* overwrite the old population */
	        population.setListSolutions(newPopulation);
	    }

	    public static  void runMutation(int permanentSolutions,Population population,Solution best,double chanceOfMutation) {
	        List<Solution> newPopulation = new ArrayList<>();
	        for (int i = 0; i < permanentSolutions; i++) { 
	        	/* save the parent solutions */
	            newPopulation.add(population.getListSolutions().get(i));
	        }
	        /* mutate all the population */
	        for (int i = permanentSolutions; i < Population.sizeOfThePopulation; i++) { 
	            newPopulation.add(solutionMutating(population.getListSolutions().get(i),chanceOfMutation));
	        }
	        /* overwrite the old population*/
	        population.setListSolutions(newPopulation); 
	        if(population.getListSolutions().get(0).getValueFitness()>best.getValueFitness()){
	        	/* get the best solution */
	            best = population.getListSolutions().get(0); 
	        }
	    }
         
	     /* crossing over 2 chromosoms*/
	    public static  Solution solutionCrossing(Solution sol1, Solution sol2) { 
	        int[] values = new int[Loading.numberOfVariables];
	        for (int i = 0; i < Loading.numberOfVariables; i++) {
	            if (Math.random() < 0.5) {
	                values[i] = sol1.getarraySolution()[i];
	            } else {
	                values[i] = sol2.getarraySolution()[i];
	            }
	        }
	        return new Solution(values);
	    }

	    /* mutating 2 chromosoms */
	    public static  Solution solutionMutating(Solution sol,double chanceOfMutation) { 
	        int[] values = new int[Loading.numberOfVariables];
	        for (int i = 0; i < Loading.numberOfVariables; i++) {
	            if (Math.random() < chanceOfMutation) {
	                if (sol.getarraySolution()[i] == 0) {
	                    values[i] = 1;
	                } else {
	                    values[i] = 0;
	                }
	            } else {
	                values[i] = sol.getarraySolution()[i];
	            }
	        }
	        return new Solution(values);
	    }

	    public static List<Solution> solutionsSelection(int sizeOfTheSelection,Population population) { 
	        List<Solution> sols = new ArrayList<>();
	        Random random = new Random();
	        for (int i = 0; i < sizeOfTheSelection; i++) {
	            Solution s = population.getListSolutions().get(random.nextInt(Population.sizeOfThePopulation));
	            if (!sols.contains(s)) {
	                sols.add(s);
	            } else {
	                i--;
	            }
	        }
	        sols.sort(new FitnessEvaluation());
	        return sols;
	    }



}
