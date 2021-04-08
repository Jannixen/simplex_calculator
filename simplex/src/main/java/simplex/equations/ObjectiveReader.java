package simplex.equations;


import simplex.objects.RealVariable;

import java.util.ArrayList;
import java.util.HashMap;

import static simplex.equations.ValueChecker.valueChecker;

public class ObjectiveReader {

    private HashMap<RealVariable, ArrayList<Double>> realVariables;

    public ObjectiveReader(String objectiveInput) {
        realVariables = new HashMap<>();
        readObjective(objectiveInput);
    }

    public static void main(String[] args) {
        String example = "5x1 + 10x2 + 8x3";
        String example2 = "3x1 + 5x2 + 2x3 <= 60 \n 4x1 + 4x2 + 4x3 <= 72 \n 2x1 + 4x2 + 5x3 <= 100";
        ObjectiveReader objectiveReader = new ObjectiveReader(example);
        ConstraintsReader constraintsReader = new ConstraintsReader(objectiveReader.getRealVariables(), example2);
        for (ArrayList<Double> v : constraintsReader.getVariables().values()) {
            for (Double d : v) {
                System.out.print(d);
                System.out.print(" ");
            }
            System.out.println("");
        }
        for (Double constant: constraintsReader.getConstants()){
            System.out.print(constant);
            System.out.print(" ");
        }
    }

    public HashMap<RealVariable, ArrayList<Double>> getRealVariables() {
        return realVariables;
    }

    private void readObjective(String userInput) {
        EquationElementsSeparator newSeparator = new EquationElementsSeparator(userInput);
        for (String variableName : newSeparator.getCoefficientsPerNameMap().keySet()) {
            addVariableFromObjective(variableName, newSeparator.getCoefficientsPerNameMap().get(variableName));
        }
    }

    private void addVariableFromObjective(String rawVariableName, String rawVariableCost) {
        String variableName = valueChecker.checkName(rawVariableName);
        double variableCost = valueChecker.checkCost(rawVariableCost);
        if (variableCost != 0 && variableName != null) {
            RealVariable newVariable = new RealVariable(variableName, variableCost);
            realVariables.put(newVariable, new ArrayList<>());
        } else {
            //TODO obsługa wyjątku
        }
    }

}
