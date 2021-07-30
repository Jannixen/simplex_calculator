package simplex.process;

import simplex.objects.Tableau;

class PivotFinder {

    int findPivotColumnIndex(Tableau tableau, boolean ifMaximization) {
        int pivotColumnIndex;

        if (ifMaximization) {
            pivotColumnIndex = findMinimumOptimalityIndex(tableau);
        } else {
            pivotColumnIndex = findMaximumOptimalityIndex(tableau);
        }
        return pivotColumnIndex;

    }

    int findPivotRowIndex(Tableau tableau, int pivotColumnIndex) throws ArrayIndexOutOfBoundsException {

        int pivotRowIndex;
        pivotRowIndex = findMinimumMinRatio(tableau, pivotColumnIndex);

        if (pivotColumnIndex == Integer.MAX_VALUE || pivotRowIndex == Integer.MAX_VALUE) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return pivotRowIndex;

    }

    private int findMaximumOptimalityIndex(Tableau tableau) {
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

    private int findMinimumOptimalityIndex(Tableau tableau) {
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

    private int findMinimumMinRatio(Tableau tableau, int pivotColumnNumber) {
        double minMinRatio = Integer.MAX_VALUE;
        int pivotRowIndex = Integer.MAX_VALUE;
        double minRatioCalculation;

        for (int rowIndex = 0; rowIndex < tableau.getLength(); rowIndex++) {
            if (tableau.getCoefficients().get(rowIndex).get(pivotColumnNumber) != 0) {
                minRatioCalculation = tableau.getConstants().get(rowIndex) / tableau.getCoefficients().get(rowIndex).get(pivotColumnNumber);
                if (minRatioCalculation < minMinRatio & minRatioCalculation > 0) {
                    minMinRatio = minRatioCalculation;
                    pivotRowIndex = rowIndex;
                }
            }
        }
        return pivotRowIndex;
    }
}
