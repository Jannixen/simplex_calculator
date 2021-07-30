package simplex.equations;

import communication.Instruction;

import java.util.HashMap;
import java.util.Scanner;

class EquationSeparator {

    HashMap<String, String> coefficientsPerNameMap;
    String constant;

    public EquationSeparator() {
        this.coefficientsPerNameMap = new HashMap<>();
        this.constant = "";
    }

    HashMap<String, String> getCoefficientsPerNameMap() {
        return coefficientsPerNameMap;
    }

    String getConstant() {
        return constant;
    }

    Instruction separate(String strEquation) {
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
        String coefficient;
        int characterCounter = 0;
        String sign;

        while (characterCounter < afterSplit.length) {
            if (afterSplit.length % 2 == 1 && characterCounter == 0) {
                sign = "+";
            } else {
                sign = afterSplit[characterCounter++].strip();
            }
            coefficient = afterSplit[characterCounter++].strip();
            try {
                separateIntoCoefficientAndName(sign, coefficient);
            } catch (ExceptionInInitializerError e) {
                return Instruction.NOT_NEEDED_VALUE;
            }
        }
        return Instruction.CONTINUE;
    }


    private void separateIntoCoefficientAndName(String sign, String strVariable) {
        String nameDelimiter = "^\\d+";
        String coefficientDelimiter = "[a-z]\\d+";

        Scanner nameSeparatingScanner = new Scanner(strVariable).useDelimiter(nameDelimiter);
        Scanner coefficientSeparatingScanner = new Scanner(strVariable).useDelimiter(coefficientDelimiter);

        if (!nameSeparatingScanner.hasNext()) {
            throw new ExceptionInInitializerError();
        } else if (!coefficientSeparatingScanner.hasNext()) {
            putInMap(nameSeparatingScanner.next().strip(), "1", sign);
        } else {
            putInMap(nameSeparatingScanner.next().strip(), coefficientSeparatingScanner.next().strip(), sign);
        }
    }

    private void putInMap(String varName, String coefficient, String sign) {
        if (sign.equals("-")) {
            coefficientsPerNameMap.put(varName, "-" + coefficient);
        } else {
            coefficientsPerNameMap.put(varName, coefficient);
        }
    }
}


