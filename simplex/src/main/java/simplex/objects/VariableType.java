package simplex.objects;

public enum VariableType {

    SLACK(0, 1.0), ARTIFICIAL(100000, 1.0),
    SURPLUS(0, -1.0);

    private final double cost;
    private final double coefficient;

    VariableType(double cost, double coefficient) {
        this.cost = cost;
        this.coefficient = coefficient;
    }

    public double getCost() {
        return cost;
    }

    public double getCoefficient() {
        return coefficient;
    }

}
