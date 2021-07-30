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

    private void readObjectiveFunction(String input) {
        if (input.isBlank()) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NO_OBJECTIVE);
        }
        EquationSeparator newSeparator = new EquationSeparator();
        InstructionsSender.getInstructionSender().showInstructionForUser(newSeparator.separate(input));
        for (String variableName : newSeparator.getCoefficientsPerNameMap().keySet()) {
            addSingleVariable(variableName, newSeparator.getCoefficientsPerNameMap().get(variableName));
        }
    }

    private void addSingleVariable(String rawName, String rawCost) {
        String variableName = valueChecker.checkName(rawName);
        try {
            double variableCost = valueChecker.checkNumber(rawCost);
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
