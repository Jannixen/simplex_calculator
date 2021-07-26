package simplex.equations;

import simplex.objects.Tableau;
import simplex.process.SimplexProcessCoordinator;

public class UserInputProcessor {

    public UserInputProcessor(String objectiveString, String constraintsString, boolean ifMaximization) {
        ObjectiveFunctionReader objectiveFunctionReader = new ObjectiveFunctionReader(objectiveString);
        ConstraintsReader constraintsReader = new ConstraintsReader(objectiveFunctionReader.getVariables(), constraintsString);
        if (constraintsReader.getVariables() != null & constraintsReader.getConstants() != null) {
            Tableau newTableau = new Tableau(constraintsReader.getVariables(), constraintsReader.getConstants());
            SimplexProcessCoordinator simplexCoordinator = new SimplexProcessCoordinator();
            simplexCoordinator.conductSimplexProcess(newTableau, ifMaximization);
        }
    }
}
