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
        Scanner constantSeparatingScanner = new Scanner(strEquation).useDelimiter(constantDelimiter);
        constantSeparatingScanner.findAll(constantDelimiter);
        if (!constantSeparatingScanner.hasNext()) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NOT_PROPER_EQUATION);
            return;
        }
        while (constantSeparatingScanner.hasNext()) {
            separateVariables(constantSeparatingScanner.next());
            if (constantSeparatingScanner.hasNext()) {
                constant = constantSeparatingScanner.next().strip();
            }
        }
    }

    private void separateVariables(String strEquation) {
        String variablesDelimiter = "[+\\-]";
        Scanner variablesSeparatingScanner = new Scanner(strEquation).useDelimiter(variablesDelimiter);
        if (!variablesSeparatingScanner.hasNext()) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NOT_PROPER_EQUATION);
            return;
        }
        while (variablesSeparatingScanner.hasNext()) {
            String strVariable = variablesSeparatingScanner.next();
            separateIntoCoefficientAndName(strVariable.strip());
        }
    }

    private void separateIntoCoefficientAndName(String strVariable) {
        String nameDelimiter = "^\\d+";
        String coefficientDelimiter = "[a-z]\\d+";
        Scanner nameSeparatingScanner = new Scanner(strVariable).useDelimiter(nameDelimiter);
        Scanner coefficientSeparatingScanner = new Scanner(strVariable).useDelimiter(coefficientDelimiter);

        if (nameSeparatingScanner.hasNext() && coefficientSeparatingScanner.hasNext()) {
            coefficientsPerNameMap.put(nameSeparatingScanner.next().strip(), coefficientSeparatingScanner.next().strip());
        } else if (nameSeparatingScanner.hasNext() && !coefficientSeparatingScanner.hasNext()) {
            coefficientsPerNameMap.put(nameSeparatingScanner.next().strip(), "1");
        } else if (coefficientSeparatingScanner.hasNext() && !nameSeparatingScanner.hasNext()) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NOT_NEEDED_VALUE);
        }
    }

}



