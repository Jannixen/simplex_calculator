package simplex.equations;

import simplex.objects.Tableau;
import simplex.objects.Variable;
import simplex.process.SimplexProcessCoordinator;

import java.util.ArrayList;

public class UserInputProcessor {

    public static void main(String[] args) {
        String example = "5x1 + 10x3 + 8x2";
        String example2 = "3x1 + 5x2 + 2x3 >= 60 \n 4x1 + 4x2 + 4x3 <= 72 \n 2x1 + 4x2 + 5x3 <= 100";
        ObjectiveReader objectiveReader = new ObjectiveReader(example);
        ConstraintsReader constraintsReader = new ConstraintsReader(objectiveReader.getVariables(), example2);
        Tableau newTableau = new Tableau(constraintsReader.getVariables(), constraintsReader.getConstants());
        SimplexProcessCoordinator simplexCoordinator = new SimplexProcessCoordinator();
        simplexCoordinator.conductSimplexProcess(newTableau,false);
    }


}
