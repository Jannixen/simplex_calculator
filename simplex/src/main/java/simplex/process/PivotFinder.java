package simplex.process;

import simplex.objects.Tableau;

public class PivotFinder {

    int findMaximumOptimalityIndex(Tableau tableau) {
        double maxOptimality = -Integer.MAX_VALUE;
        int pivotColumnIndex = Integer.MAX_VALUE;

        for (int columnIndex = 0; columnIndex < tableau.getWidth(); columnIndex++) {
            double optimalityIndex = tableau.getOptimalityIndexes().get(columnIndex);
            if (optimalityIndex > maxOptimality) {
                maxOptimality = optimalityIndex;
                pivotColumnIndex = columnIndex;
            }
        }
        return pivotColumnIndex;
    }

    int findMinimumOptimalityIndex(Tableau tableau) {
        double minOptimality = Integer.MAX_VALUE;
        int pivotColumnIndex = Integer.MAX_VALUE;

        for (int columnIndex = 0; columnIndex < tableau.getWidth(); columnIndex++) {
            double optimalityIndex = tableau.getOptimalityIndexes().get(columnIndex);
            if (optimalityIndex < minOptimality) {
                minOptimality = optimalityIndex;
                pivotColumnIndex = columnIndex;
            }
        }
        return pivotColumnIndex;
    }

    int findMinimumMinRatio(Tableau tableau, int pivotColumnNumber) {
        double minMinRatio = Integer.MAX_VALUE;
        int pivotRowIndex = Integer.MAX_VALUE;
        double minRatioCalculation;

        for (int rowIndex = 0; rowIndex < tableau.getLength(); rowIndex++) {
            if (tableau.getCoefficients().get(rowIndex).get(pivotColumnNumber) != 0) {
                minRatioCalculation = tableau.getConstants().get(rowIndex) / tableau.getCoefficients().get(rowIndex).get(pivotColumnNumber);
                if (minRatioCalculation  < minMinRatio & minRatioCalculation >0) {
                    minMinRatio = minRatioCalculation;
                    pivotRowIndex = rowIndex;
                }
            }
        }
        return pivotRowIndex;
    }
}
