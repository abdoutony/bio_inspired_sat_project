package sat_codification;

import java.util.List;

import sat_codification.Litteral;

public class Clause {
	 private List<Litteral> listLitterals;

	    public Clause() {}

	    public Clause(List<Litteral> listLitterals) {
	        this.listLitterals = listLitterals;
	    }

	    public List<Litteral> getListLitterals() {
	        return this.listLitterals;
	    }

	    public void setListLitterals(List<Litteral> listLitterals) {
	        this.listLitterals = listLitterals;
	    }
}
