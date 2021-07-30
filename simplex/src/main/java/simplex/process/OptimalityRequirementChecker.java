package simplex.process;

import simplex.objects.Tableau;

class OptimalityRequirementChecker {

    boolean checkOptimality(Tableau tableau, boolean ifMaximization) {
        if (ifMaximization) {
            return checkOptimalityForMaximization(tableau);
        } else {
            return checkOptimalityForMinimization(tableau);
        }
    }

    boolean checkOptimalityForMinimization(Tableau tableau) {
        for (Double optimalityIndex : tableau.getOptimalityIndexes()) {
            if (optimalityIndex > 0) {
                return false;
            }
        }
        return true;
    }

    boolean checkOptimalityForMaximization(Tableau tableau) {
        for (Double optimalityIndex : tableau.getOptimalityIndexes()) {
            if (optimalityIndex < 0) {
                return false;
            }
        }
        return true;
    }

}
