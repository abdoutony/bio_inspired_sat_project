package loading_sat_dataset;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import ant_colony_optimization_algorithm.Algo_Body_Aco;
import sat_codification.Clause;
import sat_codification.Litteral;
import sat_codification.Sat;

public class Loading {

	    public static List<Clause> listClauses;
	    public static List<Litteral> listLitterals;
	    public static Litteral litteral;
	    public static Clause clause;
	    public static Sat sat;
	    public static int numberOfVariables; 
	    public static int numberOfClauses; 
	    
	    public static Sat LoadFile(String fileName) {
	        listClauses = new ArrayList<>();
	        int temporaryParseInteger;
	        try {
	            InputStream is = new FileInputStream(System.getProperty("user.dir") + fileName);
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);
	            String line;
	            String[] lit;
	            boolean first = true;
	            while ((line = br.readLine()) != null) {
	                if (line.startsWith("c") || line.startsWith("%") || line.startsWith("0") || line.equals("")) {
	                } else {
	                    if (line.startsWith("p")) {
	                        line = line.replace("p cnf ", "");
	                        line = line.replaceFirst(" ", "");
	                        lit = line.split(" ");
	                        numberOfVariables = Integer.parseInt(lit[0]);
	                        numberOfClauses = Integer.parseInt(lit[1]);
	                        Algo_Body_Aco.arrayFitness = new int[numberOfVariables][2];
	                        continue;
	                    }
	                    listLitterals = new ArrayList<Litteral>();
	                    if (first) {
	                        line = line.replaceFirst(" ", "");
	                        first = false;
	                    }
	                    lit = line.split(" ");
	                    for (int i = 0; i < 3; i++) {
	                        temporaryParseInteger = Integer.parseInt(lit[i]);
	                        if (temporaryParseInteger > 0) {
	                            Algo_Body_Aco.arrayFitness[temporaryParseInteger - 1][1]++;
	                            litteral = new Litteral(temporaryParseInteger, 1);
	                        } else {
	                        	Algo_Body_Aco.arrayFitness[-(temporaryParseInteger) - 1][0]++;
	                            litteral = new Litteral(-temporaryParseInteger, -1);
	                        }
	                        listLitterals.add(litteral);
	                    }
	                    clause = new Clause(listLitterals);
	                    listClauses.add(clause);
	                }
	            }
	            br.close();
	        } catch (Exception e) {
	            System.out.println(e.toString());
	        }
	        return new Sat(listClauses);

	    }
	
}
