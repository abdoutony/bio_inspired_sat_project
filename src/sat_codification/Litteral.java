package sat_codification;

public class Litteral {
	
    private int litName;
    private int litValue;

    public Litteral(int litName, int litValue) {
        this.litName = litName;
        this.litValue = litValue;
    }

    public int getLitName() {
        return this.litName;
    }
    
    public int getLitValue() {
        return this.litValue;
    }

    public void setLitName(int litName) {
        this.litName = litName;
    }

   

    public void setLitValue(int litValue) {
        this.litValue = litValue;
    }

    public int getLitteralValue(int value) {
        if (this.litValue == 1) {
            return value;
        } else {
            if (value == 1) {
                return 0;
            } else {
                return 1;
            }
        }
    }
    public int getLitteralName() {
        if (this.litValue == 1) {
            return this.litName;
        }
        return -this.litName;
    }

}
