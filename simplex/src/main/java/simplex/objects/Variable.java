package simplex.objects;

import java.util.Objects;

public class Variable {

    protected final String name;
    protected final boolean ifRealVariable;
    protected final boolean ifArtificialVariable;
    protected double cost;

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
        ifArtificialVariable=false;
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
