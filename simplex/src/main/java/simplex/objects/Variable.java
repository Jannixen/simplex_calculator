package simplex.objects;

import java.util.Objects;

public class Variable {

    protected final double cost;
    protected final String name;
    protected boolean ifRealVariable;
    protected boolean ifSurplusVariable;

    public Variable(double cost) {
        this.cost = cost;
        this.name = "";
        ifRealVariable = false;
        ifSurplusVariable = false;
    }

    public Variable(double cost, String name) {
        this.cost = cost;
        this.name = name;
        ifRealVariable = true;
        ifSurplusVariable = false;
    }

    public Variable(double cost, boolean ifSurplus){
        this.cost = cost;
        this.name = "";
        ifRealVariable = false;
        ifSurplusVariable = ifSurplus;
    }

    public String getName() {
        return name;
    }

    public boolean isRealVariable() {
        return ifRealVariable;
    }

    public double getCost() {
        return cost;
    }

    public boolean isSurplusVariable() {
        return ifSurplusVariable;
    }
}
