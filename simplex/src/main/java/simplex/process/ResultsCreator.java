package simplex.process;

import communication.InstructionsSender;
import simplex.objects.Tableau;
import simplex.objects.Variable;


public class ResultsCreator {

    String results;

    public ResultsCreator() {
        this.results = "";
    }

    String createResults(Tableau tableau) {
        double totalCost = readResultsFromTableau(tableau);
        totalCost = Math.round(totalCost * 100.00) / 100.00;
        results += "Entire cost: ";
        results += String.valueOf(totalCost);
        return results;
    }

    private double readResultsFromTableau(Tableau tableau) {
        double totalCost = 0;

        for (int row = 0; row < tableau.getLength(); row++) {

            totalCost += makeResultsLine(tableau, row);
        }

        return totalCost;
    }

    private double makeResultsLine(Tableau tableau, int row) {
        double transaction = 0;
        Variable baseVariable = tableau.getBaseVariables().get(row);

        if (baseVariable.isRealVariable()) {
            transaction = baseVariable.getCost() * tableau.getConstants().get(row);
            results += baseVariable.getName();
            results += " [Cost = " + tableau.getConstants().get(row) + " * " +
                    baseVariable.getCost() + " = ";
            results += transaction + "] \n";
        }
        return transaction;
    }


}


