package simplex.objects;

import java.util.Objects;

public class Variable {

    protected final double cost;
    protected boolean ifRealVariable;


    public Variable(double cost) {
        this.cost = cost;
        ifRealVariable = false;
    }


    public boolean isRealVariable() {
        return ifRealVariable;
    }

    public double getCost() {
        return cost;
    }

}
