package ant_colony_optimization_algorithm;

import loading_sat_dataset.Loading;

public class AgentAnt {

    public static final double pheromonIntialValue = .000005;
    private int[] arraySolution; 
    private int nbrStatisfiedClauses; 

    public AgentAnt() { 
        arraySolution = new int[Loading.VAR_NUM];
        for (int i = 0; i < Loading.VAR_NUM; i++) {
            arraySolution[i] = -1;
        }
    }

    public void start() {
        for (int i = 0; i < Loading.VAR_NUM; i++) {
            move(i); 
            onlineUpdate(i);
        }
        nbrStatisfiedClauses = Run_Algo_Aco.sat.satisfiedClauses(arraySolution); // evaluation
    }

    public void move(int move) {
        double t[] = new double[2];
        double n[] = new double[2];
        double proba;
        double sum = 0;
        for (int i = 0; i < 2; i++) { 
            t[i] = Math.pow(Algo_Body_Aco.arrayPheromon[move][i], Algo_Body_Aco.alphaValue);
            n[i] = Math.pow(Algo_Body_Aco.arrayFitness[move][i], Algo_Body_Aco.betaValue);
            sum += t[i] * n[i];
        }
        proba = t[0] * n[0] / sum;
        if (Math.random() > Algo_Body_Aco.Q0) {
            if (Math.random() <= proba) { 
                arraySolution[move] = 0;
            } else {
                arraySolution[move] = 1;
            }
        } else {
            if(proba >= 0.5){
                arraySolution[move] = 0;
            } else {
                arraySolution[move] = 1;
            }
        }

    }

    private void onlineUpdate(int i) {
    	Algo_Body_Aco.arrayPheromon[i][arraySolution[i]] = Algo_Body_Aco.arrayPheromon[i][arraySolution[i]] * (1 - Algo_Body_Aco.vrateValue) + Algo_Body_Aco.vrateValue * pheromonIntialValue;
    }

    public int[] getArraySolution() {
        return arraySolution;
    }

    public int getNbrSatisfiedClauses() {
        return nbrStatisfiedClauses;
    }

}
