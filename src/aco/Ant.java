package aco;

import loading_sat_dataset.Loading;

public class Ant {

    public static final double I_PHEROMON = .000005; // initial pheromone
    private int[] values; // solution
    private int fitness; // nombre de clauses satisfaites

    public Ant() { // initialisation par une solution vide (-1)
        values = new int[Loading.VAR_NUM];
        for (int i = 0; i < Loading.VAR_NUM; i++) {
            values[i] = -1;
        }
    }

    public void begin() { // construction de la solution
        for (int i = 0; i < Loading.VAR_NUM; i++) {
            step(i); // step ou etape
            onlineUpdate(i); // online update
        }
        fitness = ACO.sat.satisfiedClauses(values); // evaluation
    }

    public void step(int step) {

        double t[] = new double[2];
        double n[] = new double[2];
        double proba;
        double sum = 0;

        for (int i = 0; i < 2; i++) { // calcule des probas
            t[i] = Math.pow(ACO.pheromon[step][i], ACO.ALPHA);
            n[i] = Math.pow(ACO.FITNESS[step][i], ACO.BETA);
            sum += t[i] * n[i];
        }

        proba = t[0] * n[0] / sum;
        if (Math.random() > ACO.Q_0) { // parametre Q0
            if (Math.random() <= proba) { // folow the best
                values[step] = 0;
            } else {
                values[step] = 1;
            }
        } else {
            if(proba >= 0.5){ // choix d'une etape Xi ou /Xi
                values[step] = 0;
            } else {
                values[step] = 1;
            }
        }

    }

    private void onlineUpdate(int i) { // online update
        ACO.pheromon[i][values[i]] = ACO.pheromon[i][values[i]] * (1 - ACO.V_RATE) + ACO.V_RATE * I_PHEROMON;
    }

    public int[] getValues() {
        return values;
    }

    public int getFitness() {
        return fitness;
    }

}
