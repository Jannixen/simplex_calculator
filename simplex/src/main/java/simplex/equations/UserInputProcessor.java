package simplex.equations;

import simplex.objects.RealVariable;
import simplex.objects.Variable;

import java.util.ArrayList;

public class UserInputProcessor {


    public static void main(String[] args) {
        String example = "2x1 + 3x2+ 4x3";
        String example2 = "2x1 + 3x2+ 4x3 < 10 \n 3x2 + 5x3 >= 5";
        ObjectiveReader objectiveReader = new ObjectiveReader(example);
        for (Variable l : objectiveReader.getRealVariables().keySet()) {
            if (l instanceof RealVariable)
                System.out.print(((RealVariable) l).getName());
        }
       /* ConstraintsReader constraintReader = new ConstraintsReader(objectiveReader.getRealVariables(), example2);
        for (ArrayList<Double> l : constraintReader.getVariables().values()) {
            for (int i = 0; i < l.size(); i++) {
                System.out.print(l.get(i));
            }
        }

        */
    }

}
