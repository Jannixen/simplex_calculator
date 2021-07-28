package simplex.process;

import communication.Instruction;
import communication.InstructionsSender;
import simplex.objects.Tableau;
import simplex.objects.Variable;

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
        int numberTry = 0;
        if (ifMaximization) {
            customizeForMaximization(tableau);
        }
        do {
            makeSimplexRound(tableau, ifMaximization);
            if (numberTry == 100) {
                InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.LONG_TIME);
            }
            numberTry++;
        } while (!optimalityChecker.checkOptimality(tableau, ifMaximization) && numberTry < 10000);
        if (numberTry > 10000) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.UNSOLVABLE);
        }
    }

    private void makeSimplexRound(Tableau tableau, boolean ifMaximization) {
        try {
            indexCounter.calculateOptimalityIndexes(tableau);
            tableauUpdater.actualizeTableau(tableau, ifMaximization);
        } catch (ArrayIndexOutOfBoundsException outOfBound) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.UNSOLVABLE);
        }
    }

    private void customizeForMaximization(Tableau tableau) {
        for (Variable variable : tableau.getBaseVariables()) {
            if (variable.isArtificialVariable()) {
                variable.setCost(-variable.getCost());
            }
        }
    }
}
