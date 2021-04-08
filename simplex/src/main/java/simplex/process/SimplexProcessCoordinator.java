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

    private void conductSimplexProcess(Tableau tableau, boolean ifMaximization) {
        indexCounter.calculateOptimalityIndexes(tableau);
        do {
            try {
                tableauUpdater.actualizeTableau(tableau);
                indexCounter.calculateOptimalityIndexes(tableau);
            } catch (ArrayIndexOutOfBoundsException outOfBound) {
                InstructionsSender.getInstructionSender().showUserInstruction(Instruction.MINIMIZATION_IMPOSSIBLE);
            }

        } while (!optimalityChecker.checkOptimalityForMinimization(tableau));
    }

}
