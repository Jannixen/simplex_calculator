package simplex.equations;

import simplex.objects.RealVariable;
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

    private HashMap<Variable, ArrayList<Double>> variables;
    private ArrayList<Double> constants;
    private int currentConstraintEquationNumber;

    ConstraintsReader(HashMap<RealVariable, ArrayList<Double>> realVariables, String constraintsInput) {
        addRealVariables(realVariables);
        this.constants = new ArrayList<>();
        currentConstraintEquationNumber = 0;
        readConstraints(constraintsInput);
    }

    public HashMap<Variable, ArrayList<Double>> getVariables() {
        return variables;
    }

    public ArrayList<Double> getConstants() {
        return constants;
    }

    private void addRealVariables(HashMap<RealVariable, ArrayList<Double>> realVariables) {
        variables = new HashMap<>();
        for (RealVariable realVariable : realVariables.keySet()) {
            variables.put(realVariable, realVariables.get(realVariable));
        }
    }

    private void readConstraints(String constraintsInput) {
        Scanner constraintsInputScanner = new Scanner(constraintsInput);
        while (constraintsInputScanner.hasNextLine()) {
            readNextEquation(constraintsInputScanner);
        }
    }

    private void readNextEquation(Scanner constraintsInputScanner) {
        String equation = constraintsInputScanner.nextLine();
        currentConstraintEquationNumber++;
        EquationElementsSeparator separator = new EquationElementsSeparator(equation);
        addNewCoefficientsLineForAlreadyDeclared(separator.getCoefficientsPerNameMap());
        addAdditionalVariables(equation);
    }

    private void addNewCoefficientsLineForAlreadyDeclared(HashMap<String, String> coefficientPerNameMap) {
        for (Variable variable : variables.keySet()) {
            if (variable instanceof RealVariable && coefficientPerNameMap.containsKey(((RealVariable) variable).getName())) {
                String variableName = coefficientPerNameMap.get(((RealVariable) variable).getName());
                variables.get(variable).add(valueChecker.checkCost(variableName));
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
                addAdditionalVariable(VariableType.SLACK);
            } else if (Objects.equals(m.group().strip(), ">") || Objects.equals(m.group().strip(), ">=")) {
                addAdditionalVariable(VariableType.SURPLUS);
                addAdditionalVariable(VariableType.ARTIFICIAL);
            } else if (Objects.equals(m.group().strip(), "=")) {
                addAdditionalVariable(VariableType.ARTIFICIAL);
            }
        }
    }


    private void addAdditionalVariable(VariableType varType) {
        Variable newVar = new Variable(varType.getCost());

        ArrayList<Double> newVarCoefficientLine = complementCoefficientsAfterAddingVariable();
        newVarCoefficientLine.add(varType.getCoefficient());

        variables.put(newVar, newVarCoefficientLine);
    }

    private ArrayList<Double> complementCoefficientsAfterAddingVariable() {
        ArrayList<Double> newVariableCoefficientLine = new ArrayList<>();
        for (int i = 0; i < currentConstraintEquationNumber - 1; i++) {
            newVariableCoefficientLine.add(0.0);
        }
        return newVariableCoefficientLine;
    }

}





