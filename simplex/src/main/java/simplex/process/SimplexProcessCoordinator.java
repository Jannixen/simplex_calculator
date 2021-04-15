package simplex.process;

import communication.Instruction;
import communication.InstructionsSender;
import simplex.objects.Tableau;

public class SimplexProcessCoordinator {

    private final OptimalityRequirementChecker optimalityChecker;
    private final OptimalityIndexCounter indexCounter;
    private final TableauUpdater tableauUpdater;

    public SimplexProcessCoordinator() {
        optimalityChecker = new OptimalityRequirementChecker();
        indexCounter = new OptimalityIndexCounter();
        tableauUpdater = new TableauUpdater();
    }

    public void conductSimplexProcess(Tableau tableau, boolean ifMaximization) {
        indexCounter.calculateOptimalityIndexes(tableau);
        for (int j = 0; j < tableau.getWidth(); j++) {
            System.out.println(tableau.getOptimalityIndexes().get(j));
        }
        do {
            tableauUpdater.actualizeTableau(tableau);
            indexCounter.calculateOptimalityIndexes(tableau);
            for (int j = 0; j < tableau.getWidth(); j++) {
                for (int i = 0; i < tableau.getLength(); i++) {
                    System.out.println(tableau.getCoefficients().get(j).get(i));
                    System.out.println(tableau.getOptimalityIndexes().get(i));
                }
            }
        } while (!optimalityChecker.checkOptimalityForMinimization(tableau));
        ResultsCreator resultsCreator = new ResultsCreator(tableau);
    }

}
