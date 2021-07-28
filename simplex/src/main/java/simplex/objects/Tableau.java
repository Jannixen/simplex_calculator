package simplex.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Tableau {

    private ArrayList<Variable> baseVariables;
    private ArrayList<Variable> nonBaseVariables;
    private ArrayList<ArrayList<Double>> coefficients;
    private final ArrayList<Double> constants;
    private ArrayList<Double> optimalityIndexes;
    private ArrayList<Double> dotProducts;

    public Tableau(HashMap<Variable, ArrayList<Double>> variables, ArrayList<Double> constants) {

        initiateNonBaseVariable(variables.keySet());
        initiateBaseVariables(variables);
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

    private void initiateBaseVariables(HashMap<Variable, ArrayList<Double>> coefficientsList) {
        baseVariables = new ArrayList<>();
        for (int i = 0; i < coefficientsList.values().iterator().next().size(); i++) {
            for (Map.Entry<Variable, ArrayList<Double>> entry : coefficientsList.entrySet()) {
                if (entry.getValue().get(i) == 1 & !entry.getKey().isRealVariable()) {
                    baseVariables.add(entry.getKey());
                }
            }
        }
    }

    private void initiateNonBaseVariable(Set<Variable> variables) {
        nonBaseVariables = new ArrayList<>();
        nonBaseVariables.addAll(variables);
    }


    private void initiateCoefficients(HashMap<Variable, ArrayList<Double>> coefficientsList) {
        this.coefficients = new ArrayList<>();
        for (int i = 0; i < coefficientsList.values().iterator().next().size(); i++) {
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


    public void printTableau() {
        for (int i = 0; i < this.getWidth(); i++) {
            System.out.print(this.getNonBaseVariables().get(i).getCost() + " ");
        }
        System.out.println();
        for (int i = 0; i < this.getLength(); i++) {
            System.out.print(this.getBaseVariables().get(i).getCost() + " ");
            System.out.print(this.getConstants().get(i) + " ");
            for (int j = 0; j < this.getWidth(); j++) {
                System.out.print(this.getCoefficients().get(i).get(j) + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < this.getWidth(); i++) {
            System.out.print(this.getDotProducts().get(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < this.getWidth(); i++) {
            System.out.print(this.getOptimalityIndexes().get(i) + " ");
        }
        System.out.println();
        System.out.println("----------------------");
    }

    public double getTotalCost() {
        double transaction;
        double totalCost = 0;

        for (int row = 0; row < getLength(); row++) {
            Variable baseVariable = baseVariables.get(row);
            if (baseVariable.isRealVariable()) {
                transaction = baseVariable.getCost() * constants.get(row);
                totalCost += transaction;
            }
        }
        return totalCost;
    }

}
