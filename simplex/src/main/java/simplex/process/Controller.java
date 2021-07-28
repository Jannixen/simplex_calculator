package simplex.process;

import communication.Instruction;
import communication.InstructionsSender;
import simplex.equations.ConstraintsReader;
import simplex.equations.ObjectiveFunctionReader;
import simplex.objects.Tableau;

public class Controller {

    private ObjectiveFunctionReader objectiveFunctionReader;
    private ConstraintsReader constraintsReader;
    private Tableau tableau;
    private boolean ifMaximization;

    public Controller(String objectiveString, String constraintsString, boolean ifMaximization) {
        objectiveFunctionReader = new ObjectiveFunctionReader(objectiveString);
        constraintsReader = new ConstraintsReader(objectiveFunctionReader.getVariables(), constraintsString);
        this.ifMaximization = ifMaximization;

    }

    public void conduct(){
        if (constraintsReader.getVariables() == null & constraintsReader.getConstants() == null){
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NO_CONSTRAINTS);
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NO_OBJECTIVE);
        } else if (constraintsReader.getVariables() == null){
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NO_CONSTRAINTS);
        } else if (objectiveFunctionReader.getVariables() ==null){
            InstructionsSender.getInstructionSender().showInstructionForUser(Instruction.NO_OBJECTIVE);
        } else{
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
