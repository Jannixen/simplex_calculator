package simplex.process;

import simplex.objects.Tableau;

public class OptimalityRequirementChecker {

    public boolean checkOptimalityForMinimization(Tableau tableau) {
        for (Double optimalityIndex : tableau.getOptimalityIndexes()) {
            if (optimalityIndex > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean checkOptimalityForMaximization(Tableau tableau) {
        for (Double optimalityIndex : tableau.getOptimalityIndexes()) {
            if (optimalityIndex < 0) {
                return false;
            }
        }
        return true;
    }

}
