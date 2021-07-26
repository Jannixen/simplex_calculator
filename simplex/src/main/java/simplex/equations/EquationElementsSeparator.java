package simplex.equations;

import communication.Instruction;
import communication.InstructionsSender;

import java.util.HashMap;
import java.util.Scanner;

public class EquationElementsSeparator {

    HashMap<String, String> coefficientsPerNameMap = new HashMap<>();
    String constant;

    EquationElementsSeparator(String strEquation) {
        separateConstant(strEquation);
    }

    HashMap<String, String> getCoefficientsPerNameMap() {
        return coefficientsPerNameMap;
    }

    String getConstant() {
        return constant;
    }

    private void separateConstant(String strEquation) {
        String constantDelimiter = "=|<=|>=|<|>";
        String[] afterSplit = strEquation.split(constantDelimiter);

        if (afterSplit.length < 2) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NOT_PROPER_EQUATION);
        } else {
            separateVariables(afterSplit[0]);
            constant = afterSplit[1].strip();
        }
    }

    private void separateVariables(String strEquation) {
        String variablesDelimiter = "((?<=\\+)|(?=\\+))|((?<=-)|(?=-))";
        String[] afterSplit = strEquation.split(variablesDelimiter);

        if (afterSplit.length == 0) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NOT_PROPER_EQUATION);
            return;
        }
        if (afterSplit.length % 2 == 1) {
            separateIntoCoefficientAndName("+", afterSplit[0].strip());
            for (int i = 1; i < afterSplit.length - 1; i = i + 2) {
                separateIntoCoefficientAndName(afterSplit[i].strip(), afterSplit[i + 1].strip());
            }
        }else {
            for (int i = 0; i < afterSplit.length - 1; i = i + 2) {
                separateIntoCoefficientAndName(afterSplit[i].strip(), afterSplit[i + 1].strip());
            }
        }
    }


    private void separateIntoCoefficientAndName(String sign, String strVariable) {
        String nameDelimiter = "^\\d+";
        String coefficientDelimiter = "[a-z]\\d+";

        Scanner nameSeparatingScanner = new Scanner(strVariable).useDelimiter(nameDelimiter);
        Scanner coefficientSeparatingScanner = new Scanner(strVariable).useDelimiter(coefficientDelimiter);

        if (!nameSeparatingScanner.hasNext()) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NOT_NEEDED_VALUE);
        } else if (!coefficientSeparatingScanner.hasNext()) {
            if (sign.equals("-")) {
                coefficientsPerNameMap.put(nameSeparatingScanner.next().strip(), "-1");
            } else {
                coefficientsPerNameMap.put(nameSeparatingScanner.next().strip(), "1");
            }
        } else {
            if (sign.equals("-")) {
                coefficientsPerNameMap.put(nameSeparatingScanner.next().strip(), "-" + coefficientSeparatingScanner.next().strip());
            } else {
                coefficientsPerNameMap.put(nameSeparatingScanner.next().strip(), coefficientSeparatingScanner.next().strip());
            }
        }

    }
}


