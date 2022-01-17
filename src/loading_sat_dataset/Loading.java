package loading_sat_dataset;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import aco.ACO;
import sat_codification.Clause;
import sat_codification.Litteral;
import sat_codification.Sat;

public class Loading {

	    public static List<Clause> clauses;
	    public static List<Litteral> litterals;
	    public static Litteral litteral;
	    public static Clause clause;
	    public static Sat sat;
	    
	    
	    // Parametres
	    public static int VAR_NUM; // nombre des Varriables
	    public static int CLAUSE_NUMBER; // nombre des Clauses
	    

	  
		

	    // Chargement d'un fichier
	    public static Sat LoadSat(String fileName) {

	        //Initilisation des Listes
	        clauses = new ArrayList<>();

	        //Juste une varriable tmp
	        int tmpParseInt;

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
	                        VAR_NUM = Integer.parseInt(lit[0]);
	                        CLAUSE_NUMBER = Integer.parseInt(lit[1]);
	                        ACO.FITNESS = new int[VAR_NUM][2];
	                        continue;
	                    }
	                    litterals = new ArrayList<Litteral>();
	                    if (first) {
	                        line = line.replaceFirst(" ", "");
	                        first = false;
	                    }
	                    lit = line.split(" ");
	                    for (int i = 0; i < 3; i++) {
	                        tmpParseInt = Integer.parseInt(lit[i]);
	                        if (tmpParseInt > 0) {
	                            ACO.FITNESS[tmpParseInt - 1][1]++;
	                            litteral = new Litteral(tmpParseInt, 1);
	                        } else {
	                            ACO.FITNESS[-(tmpParseInt) - 1][0]++;
	                            litteral = new Litteral(-tmpParseInt, -1);
	                        }
	                        litterals.add(litteral);
	                    }
	                    clause = new Clause(litterals);
	                    clauses.add(clause);
	                }
	            }
	            br.close();
	        } catch (Exception e) {
	            System.out.println(e.toString());
	        }
	        return new Sat(clauses);

	    }
	
}
