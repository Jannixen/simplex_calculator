package simplex;

import communication.Instruction;
import communication.InstructionsSender;
import simplex.equations.ConstraintsReader;
import simplex.equations.ObjectiveFunctionReader;
import simplex.objects.Tableau;
import simplex.process.ResultsCreator;
import simplex.process.SimplexProcessCoordinator;

public class Controller {

    private final ObjectiveFunctionReader objectiveFunctionReader;
    private final ConstraintsReader constraintsReader;
    private final boolean ifMaximization;
    private Tableau tableau;

    public Controller(String objectiveString, String constraintsString, boolean ifMaximization) {
        objectiveFunctionReader = new ObjectiveFunctionReader(objectiveString);
        constraintsReader = new ConstraintsReader(objectiveFunctionReader.getVariables(), constraintsString);
        this.ifMaximization = ifMaximization;
    }

    public void conduct() {
        if (constraintsReader.getVariables() == null & constraintsReader.getConstants() == null) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NO_CONSTRAINTS);
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NO_OBJECTIVE);
        } else if (constraintsReader.getVariables() == null) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NO_CONSTRAINTS);
        } else if (objectiveFunctionReader.getVariables() == null) {
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NO_OBJECTIVE);
        } else {
            String results = makeSimplex();
            InstructionsSender.getInstructionSender().showResults(results);
        }
    }

    public Tableau getTableau() {
        return tableau;
    }

    String makeSimplex() {
        tableau = new Tableau(constraintsReader.getVariables(), constraintsReader.getConstants());
        SimplexProcessCoordinator simplexCoordinator = new SimplexProcessCoordinator();
        simplexCoordinator.conductSimplexProcess(tableau, ifMaximization);
        ResultsCreator resultsCreator = new ResultsCreator();
        String results = resultsCreator.createResults(tableau);
        System.out.println(results);
        return results;
    }


}
