package simplex.equations;

import communication.Instruction;
import communication.InstructionsSender;
import simplex.objects.Variable;
import simplex.objects.VariableType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static simplex.equations.ValueChecker.valueChecker;

public class ConstraintsReader {

    private final ArrayList<Double> constants;
    private final HashMap<Variable, ArrayList<Double>> variables;
    private int currentEquationNumber;

    public ConstraintsReader(HashMap<Variable, ArrayList<Double>> variables, String input) {
        this.constants = new ArrayList<>();
        currentEquationNumber = 0;

        this.variables = variables;
        readConstraints(input);
    }

    public HashMap<Variable, ArrayList<Double>> getVariables() {
        return variables;
    }

    public ArrayList<Double> getConstants() {
        return constants;
    }

    private void readConstraints(String input) {
        Scanner constraintsScanner = new Scanner(input);
        if (!constraintsScanner.hasNext()) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NO_CONSTRAINTS);
            return;
        }
        while (constraintsScanner.hasNextLine()) {
            readNextEquation(constraintsScanner);
        }
    }

    private void readNextEquation(Scanner input) {
        String equation = input.nextLine();
        currentEquationNumber++;
        EquationSeparator separator = new EquationSeparator();
        InstructionsSender.getInstructionSender().showInstructionForUser(separator.separate(equation));
        fillInVariablesCoefficients(separator.getCoefficientsPerNameMap());
        addAdditionalVariables(equation);
        addConstant(separator);
    }

    private void fillInVariablesCoefficients(HashMap<String, String> coefficientPerNameMap) {
        for (Variable variable : variables.keySet()) {
            if (variable != null && coefficientPerNameMap.containsKey(variable.getName())) {
                String variableName = coefficientPerNameMap.get(variable.getName());
                variables.get(variable).add(valueChecker.checkNumber(variableName));
            } else {
                variables.get(variable).add(0.0);
            }
        }
    }


    private void addAdditionalVariables(String strEquation) {
        String constantDelimiter = "<=|>=|<|>|=";
        final Matcher m = Pattern.compile(constantDelimiter).matcher(strEquation);
        while (m.find()) {
            if (Objects.equals(m.group().strip(), "<=") || Objects.equals(m.group().strip(), "<")) {
                addSingleAdditionalVariable(VariableType.SLACK);
            } else if (Objects.equals(m.group().strip(), ">") || Objects.equals(m.group().strip(), ">=")) {
                addSingleAdditionalVariable(VariableType.SURPLUS);
                addSingleAdditionalVariable(VariableType.ARTIFICIAL);
            } else if (Objects.equals(m.group().strip(), "=")) {
                addSingleAdditionalVariable(VariableType.ARTIFICIAL);
            }
        }
    }


    private void addSingleAdditionalVariable(VariableType varType) {
        Variable newVar = new Variable(varType.getCost());
        if (varType.equals(VariableType.ARTIFICIAL)) {
            newVar = new Variable(varType.getCost(), true);
        }
        ArrayList<Double> newVariableCoefficientLine = new ArrayList<>();
        for (int i = 0; i < currentEquationNumber - 1; i++) {
            newVariableCoefficientLine.add(0.0);
        }
        newVariableCoefficientLine.add(varType.getCoefficient());
        variables.put(newVar, newVariableCoefficientLine);
    }

    private void addConstant(EquationSeparator separator) {
        if (separator.getConstant() == null) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.CONSTANT_LACK);
        }
        try {
            constants.add(valueChecker.checkNumber(separator.getConstant()));
        } catch (NumberFormatException e) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.BAD_CONSTANT);
        }
    }


}





