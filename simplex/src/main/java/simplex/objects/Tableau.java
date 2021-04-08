package simplex.objects;

import java.util.ArrayList;

public class Tableau {

    private ArrayList<Variable> baseVariables;
    private ArrayList<Variable> nonBaseVariables;
    private ArrayList<ArrayList<Integer>> coefficients;
    private ArrayList<Integer> constants;
    private ArrayList<Double> optimalityIndexes;
    private ArrayList<Double> dotProducts;

    public Tableau() {

        baseVariables = new ArrayList<>();
        nonBaseVariables = new ArrayList<>();
        coefficients = new ArrayList<>();
        constants = new ArrayList<>();
        optimalityIndexes = new ArrayList<>();
        dotProducts = new ArrayList<>();

    }

    public ArrayList<Variable> getBaseVariables() {
        return baseVariables;
    }

    public ArrayList<Variable> getNonBaseVariables() {
        return nonBaseVariables;
    }

    public ArrayList<ArrayList<Integer>> getCoefficients() {
        return coefficients;
    }

    public ArrayList<Integer> getConstants() {
        return constants;
    }

    public ArrayList<Double> getOptimalityIndexes() {
        return optimalityIndexes;
    }

    public ArrayList<Double> getDotProducts() {
        return dotProducts;
    }

    public int getLength() {
        return baseVariables.size();
    }

    public int getWidth() {
        return nonBaseVariables.size();
    }
}
