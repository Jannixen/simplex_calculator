package simplex.objects;

public enum VariableType {

    SLACK(0, 1.0), ARTIFICIAL(Integer.MAX_VALUE, 1.0),
    SURPLUS(0, -1.0);

    private double cost;
    private double coefficient;

    private VariableType(double cost, double coefficient) {
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
