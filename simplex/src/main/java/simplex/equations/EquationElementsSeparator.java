package simplex.equations;

import communication.Instruction;

import java.util.HashMap;
import java.util.Scanner;

public class EquationElementsSeparator {

    HashMap<String, String> coefficientsPerNameMap;
    String constant;

    public EquationElementsSeparator() {
        this.coefficientsPerNameMap = new HashMap<>();
        this.constant = "";
    }

    HashMap<String, String> getCoefficientsPerNameMap() {
        return coefficientsPerNameMap;
    }

    String getConstant() {
        return constant;
    }

    public Instruction separate(String strEquation) {
        return separateConstant(strEquation);
    }

    private Instruction separateConstant(String strEquation) {
        String constantDelimiter = "=|<=|>=|<|>";
        String[] afterSplit = strEquation.split(constantDelimiter);

        if (afterSplit.length > 1)
            constant = afterSplit[1].strip();
        return separateVariables(afterSplit[0]);
    }

    private Instruction separateVariables(String strEquation) {
        String variablesDelimiter = "((?<=\\+)|(?=\\+))|((?<=-)|(?=-))";
        String[] afterSplit = strEquation.split(variablesDelimiter);

        if (afterSplit.length == 0) {
            return Instruction.NOT_PROPER_EQUATION;
        }
        if (afterSplit.length % 2 == 1) {
            Instruction instruction = separateIntoCoefficientAndName("+", afterSplit[0].strip());
            if (instruction!= Instruction.CONTINUE){
                return instruction;
            }
            for (int i = 1; i < afterSplit.length - 1; i = i + 2) {
                instruction = separateIntoCoefficientAndName(afterSplit[i].strip(), afterSplit[i + 1].strip());
                if (instruction!= Instruction.CONTINUE){
                    return instruction;
                }
            }
        } else {
            for (int i = 0; i < afterSplit.length - 1; i = i + 2) {
                Instruction instruction = separateIntoCoefficientAndName(afterSplit[i].strip(), afterSplit[i + 1].strip());
                if (instruction!= Instruction.CONTINUE){
                    return instruction;
                }
            }
        }
        return Instruction.CONTINUE;
    }


    private Instruction separateIntoCoefficientAndName(String sign, String strVariable) {
        String nameDelimiter = "^\\d+";
        String coefficientDelimiter = "[a-z]\\d+";

        Scanner nameSeparatingScanner = new Scanner(strVariable).useDelimiter(nameDelimiter);
        Scanner coefficientSeparatingScanner = new Scanner(strVariable).useDelimiter(coefficientDelimiter);

        if (!nameSeparatingScanner.hasNext()) {
            return Instruction.NOT_NEEDED_VALUE;
        } else if (!coefficientSeparatingScanner.hasNext()) {
            putInMap(nameSeparatingScanner.next().strip(), "1", sign);
        } else {
            putInMap(nameSeparatingScanner.next().strip(), coefficientSeparatingScanner.next().strip(), sign);
        }
        return Instruction.CONTINUE;
    }

    private void putInMap(String varName, String coefficient, String sign) {
        if (sign.equals("-")) {
            coefficientsPerNameMap.put(varName, "-" + coefficient);
        } else {
            coefficientsPerNameMap.put(varName, coefficient);
        }
    }
}


