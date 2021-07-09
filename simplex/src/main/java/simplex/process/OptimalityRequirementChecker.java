package simplex.process;

import simplex.objects.Tableau;

public class OptimalityRequirementChecker {

    public boolean checkOptimality(Tableau tableau, boolean ifMaximization) {
        if (ifMaximization) {
            return checkOptimalityForMaximization(tableau);
        } else {
            return checkOptimalityForMinimization(tableau);
        }
    }

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
