package algo_gen_global_classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import loading_sat_dataset.Loading;

public class GenAlgoController {
	 
	    /* Cross Over function  */
	    public static void runCrossOver(int PERMANENT_SOLS, double CROSSOVER_CHANCE,Population population,int SELECTION_SIZE) {
	        List<Solution> newPopulation = new ArrayList<>();
	        Solution parent1, parent2;
	        for (int i = 0; i < PERMANENT_SOLS; i++) {
	        	/* save parent solutions */
	            newPopulation.add(population.getListSolutions().get(i));
	        }
	        for (int i = PERMANENT_SOLS; i < Population.sizeOfThePopulation; i++) { 
	            if (Math.random() < CROSSOVER_CHANCE) {
	            	
	                parent1 = solutionsSelection(SELECTION_SIZE,population).get(0); 
	                parent2 = solutionsSelection(SELECTION_SIZE,population).get(0); 
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

	    public static  void runMutation(int PERMANENT_SOLS,Population population,Solution best,double MUTATION_CHANCE) {
	        List<Solution> newPopulation = new ArrayList<>();
	        for (int i = 0; i < PERMANENT_SOLS; i++) { 
	        	/* save the parent solutions */
	            newPopulation.add(population.getListSolutions().get(i));
	        }
	        /* mutate all the population */
	        for (int i = PERMANENT_SOLS; i < Population.sizeOfThePopulation; i++) { 
	            newPopulation.add(solutionMutating(population.getListSolutions().get(i),MUTATION_CHANCE));
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
	        int[] values = new int[Loading.VAR_NUM];
	        for (int i = 0; i < Loading.VAR_NUM; i++) {
	            if (Math.random() < 0.5) {
	                values[i] = sol1.getarraySolution()[i];
	            } else {
	                values[i] = sol2.getarraySolution()[i];
	            }
	        }
	        return new Solution(values);
	    }

	    /* mutating 2 chromosoms */
	    public static  Solution solutionMutating(Solution sol,double MUTATION_CHANCE) { 
	        int[] values = new int[Loading.VAR_NUM];
	        for (int i = 0; i < Loading.VAR_NUM; i++) {
	            if (Math.random() < MUTATION_CHANCE) {
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

	    public static List<Solution> solutionsSelection(int SELECTION_SIZE,Population population) { // fonction de selection aleatoire
	        List<Solution> sols = new ArrayList<>();
	        Random random = new Random();
	        for (int i = 0; i < SELECTION_SIZE; i++) {
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
