package simplex.equations;


import simplex.objects.Variable;

import java.util.ArrayList;
import java.util.HashMap;

import static simplex.equations.ValueChecker.valueChecker;

public class ObjectiveReader {

    private HashMap<Variable, ArrayList<Double>> Variables;

    public ObjectiveReader(String objectiveInput) {
        Variables = new HashMap<>();
        readObjective(objectiveInput);
    }


    public HashMap<Variable, ArrayList<Double>> getVariables() {
        return Variables;
    }

    private void readObjective(String userInput) {
        EquationElementsSeparator newSeparator = new EquationElementsSeparator(userInput);
        for (String variableName : newSeparator.getCoefficientsPerNameMap().keySet()) {
            addVariableFromObjective(variableName, newSeparator.getCoefficientsPerNameMap().get(variableName));
        }
    }

    private void addVariableFromObjective(String rawVariableName, String rawVariableCost) {
        String variableName = valueChecker.checkName(rawVariableName);
        double variableCost = valueChecker.checkNumber(rawVariableCost);
        if (variableCost != 0 && variableName != null) {
            Variable newVariable = new Variable(variableCost, variableName);
            Variables.put(newVariable, new ArrayList<>());
        } else {
            //TODO obsługa wyjątku
        }
    }

}
