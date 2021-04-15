package simplex.process;

import simplex.output.ResultFileCreator;
import simplex.objects.Tableau;
import simplex.objects.Variable;

import java.util.ArrayList;

public class ResultsCreator {

    ResultsCreator(Tableau tableau) {
        ArrayList<String> results = createResults(tableau);
        initiateResultFile(results);
    }

    private ArrayList<String> createResults(Tableau tableau) {
        ArrayList<String> createdResults = new ArrayList<>();
        double totalCost = 0;

        for (int i = 0; i < tableau.getBaseVariables().size(); i++) {

            totalCost += makeResultsLine(tableau, i, createdResults);
        }
        totalCost = Math.round(totalCost * 100.00) / 100.00;
        createdResults.add(String.valueOf(totalCost));
        return createdResults;
    }

    private double makeResultsLine(Tableau tableau, int lineIndex, ArrayList<String> results) {
        double transaction = 0;
        Variable baseVariable = tableau.getBaseVariables().get(lineIndex);

        if (baseVariable.isRealVariable()) {
            transaction = baseVariable.getCost() * tableau.getConstants().get(lineIndex);
            transaction = Math.round(transaction * 100.00) / 100.00;

            results.add(baseVariable.getName());
            results.add(String.valueOf(tableau.getConstants().get(lineIndex)));
            results.add(String.valueOf(baseVariable.getCost()));
            results.add(String.valueOf(transaction));
        }
        return transaction;
    }

    private void initiateResultFile(ArrayList<String> results) {
        new ResultFileCreator(results);
    }
}
