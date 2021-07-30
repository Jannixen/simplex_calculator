package simplex.objects;

public class Variable {

    private final String name;
    private final boolean ifRealVariable;
    private final boolean ifArtificialVariable;
    private double cost;

    public Variable(double cost) {
        this.cost = cost;
        this.name = "";
        this.ifRealVariable = false;
        this.ifArtificialVariable = false;
    }

    public Variable(double cost, boolean ifArtificialVariable) {
        this.cost = cost;
        this.name = "";
        this.ifRealVariable = false;
        this.ifArtificialVariable = ifArtificialVariable;
    }

    public Variable(double cost, String name) {
        this.cost = cost;
        this.name = name;
        ifRealVariable = true;
        ifArtificialVariable = false;
    }


    public String getName() {
        return name;
    }

    public boolean isRealVariable() {
        return ifRealVariable;
    }

    public boolean isArtificialVariable() {
        return ifArtificialVariable;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
