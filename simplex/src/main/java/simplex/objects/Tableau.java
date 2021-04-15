package simplex.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Tableau {

    private ArrayList<Variable> baseVariables;
    private ArrayList<Variable> nonBaseVariables;
    private ArrayList<ArrayList<Double>> coefficients;
    private ArrayList<Double> constants;
    private ArrayList<Double> optimalityIndexes;
    private ArrayList<Double> dotProducts;

    public Tableau(HashMap<Variable, ArrayList<Double>> variables, ArrayList<Double> constants) {

        initiateBaseVariables(variables.keySet());
        initiateNonBaseVariable(variables.keySet());
        initiateCoefficients(variables);
        this.constants = constants;
        initiateOptimalityIndexes();

    }

    public ArrayList<Variable> getBaseVariables() {
        return baseVariables;
    }

    public ArrayList<Variable> getNonBaseVariables() {
        return nonBaseVariables;
    }

    public ArrayList<ArrayList<Double>> getCoefficients() {
        return coefficients;
    }

    public ArrayList<Double> getConstants() {
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

    private void initiateBaseVariables(Set<Variable> variables) {
        baseVariables = new ArrayList<>();
        for (Variable variable : variables) {
            if (!variable.isRealVariable() && !variable.isSurplusVariable()) {
                baseVariables.add(variable);
            }
        }
    }

    private void initiateNonBaseVariable(Set<Variable> variables) {
        nonBaseVariables = new ArrayList<>();
        nonBaseVariables.addAll(variables);
    }


    private void initiateCoefficients(HashMap<Variable, ArrayList<Double>> coefficientsList) {
        this.coefficients = new ArrayList<>();
        for (int i = 0; i < baseVariables.size(); i++) {
            ArrayList<Double> row = new ArrayList<>();
            for (ArrayList<Double> v : coefficientsList.values()) {
                row.add(v.get(i));
            }
            coefficients.add(row);
        }
    }

    private void initiateOptimalityIndexes() {
        optimalityIndexes = new ArrayList<>();
        dotProducts = new ArrayList<>();
        int columnIndex;
        for (columnIndex = 0; columnIndex < this.getWidth(); columnIndex++) {
            optimalityIndexes.add(0.0);
            dotProducts.add(0.0);
        }
    }

}
