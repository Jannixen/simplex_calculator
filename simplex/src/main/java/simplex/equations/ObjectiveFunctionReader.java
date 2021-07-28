package simplex.equations;


import communication.Instruction;
import communication.InstructionsSender;
import simplex.objects.Variable;

import java.util.ArrayList;
import java.util.HashMap;

import static simplex.equations.ValueChecker.valueChecker;

public class ObjectiveFunctionReader {

    private final HashMap<Variable, ArrayList<Double>> Variables;

    public ObjectiveFunctionReader(String userInput) {
        Variables = new HashMap<>();
        readObjectiveFunction(userInput);
    }

    public HashMap<Variable, ArrayList<Double>> getVariables() {
        return Variables;
    }

    private void readObjectiveFunction(String userInput) {
        if (userInput.isBlank()) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NO_OBJECTIVE);
        }
        EquationElementsSeparator newSeparator = new EquationElementsSeparator();
        InstructionsSender.getInstructionSender().showInstructionForUser(newSeparator.separate(userInput));
        for (String variableName : newSeparator.getCoefficientsPerNameMap().keySet()) {
            addVariableFromObjectiveFunction(variableName, newSeparator.getCoefficientsPerNameMap().get(variableName));
        }
    }

    private void addVariableFromObjectiveFunction(String rawVariableName, String rawVariableCost) {
        String variableName = valueChecker.checkName(rawVariableName);
        try {
            double variableCost = valueChecker.checkNumber(rawVariableCost);
            if (variableName != null) {
                Variable newVariable = new Variable(variableCost, variableName);
                Variables.put(newVariable, new ArrayList<>());
            } else {
                InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.BAD_NAME);
            }
        } catch (NumberFormatException e) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.BAD_COST);
        }

    }

}
