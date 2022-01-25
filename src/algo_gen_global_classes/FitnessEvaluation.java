package algo_gen_global_classes;

import java.util.Comparator;

public class FitnessEvaluation implements Comparator<Solution> {

	@Override
	public int compare(Solution o1, Solution o2) {
		// TODO Auto-generated method stub
		return o2.getValueFitness() - o1.getValueFitness();
	}
  
}
