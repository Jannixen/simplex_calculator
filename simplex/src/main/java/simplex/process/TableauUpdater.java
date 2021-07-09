package simplex.process;

import simplex.objects.Tableau;
import simplex.objects.Variable;

import java.util.ArrayList;


public class TableauUpdater {

    void actualizeTableau(Tableau tableau, boolean ifMaximization) {

        PivotFinder pivotFinder = new PivotFinder();
        int pivotColumnIndex;
        int pivotRowIndex;

        if (ifMaximization) {
            pivotColumnIndex = pivotFinder.findMinimumOptimalityIndex(tableau);
        } else {
            pivotColumnIndex = pivotFinder.findMaximumOptimalityIndex(tableau);
        }
        pivotRowIndex = pivotFinder.findMinimumMinRatio(tableau, pivotColumnIndex);

        if (pivotColumnIndex == Integer.MAX_VALUE || pivotRowIndex == Integer.MAX_VALUE) {
            throw new ArrayIndexOutOfBoundsException();
        }

        swap(tableau, pivotRowIndex, pivotColumnIndex);
        actualizePivotRow(tableau, pivotRowIndex, pivotColumnIndex);
        actualizeRowsOtherThanPivotRow(tableau, pivotRowIndex, pivotColumnIndex);
    }

    void actualizePivotRow(Tableau tableau, int pivotRowIndex, int pivotColumnIndex) {
        ArrayList<Double> pivotRow = tableau.getCoefficients().get(pivotRowIndex);

        double pivotConstant = tableau.getConstants().get(pivotRowIndex);
        double pivotValue = pivotRow.get(pivotColumnIndex);

        for (int i = 0; i < pivotRow.size(); i++) {

            double newCellValue = pivotRow.get(i) / pivotValue;

            pivotRow.set(i, newCellValue);
            tableau.getConstants().set(pivotRowIndex, pivotConstant / pivotValue);
        }
    }

    void actualizeRowsOtherThanPivotRow(Tableau tableau, int pivotRowIndex, int pivotColumnIndex) {

        ArrayList<Double> pivotRow = tableau.getCoefficients().get(pivotRowIndex);
        double pivotMinRatio = tableau.getConstants().get(pivotRowIndex);

        for (int rowIndex = 0; rowIndex < tableau.getLength(); rowIndex++) {
            if (rowIndex != pivotRowIndex) {

                double crossValue = tableau.getCoefficients().get(rowIndex).get(pivotColumnIndex);

                actualizeCoefficients(tableau, crossValue, rowIndex, pivotRow);

                actualizeConstants(tableau, crossValue, rowIndex, pivotMinRatio);
            }
        }
    }

    void actualizeCoefficients(
            Tableau tableau, double crossValue, int rowIndex, ArrayList<Double> pivotRow) {

        for (int columnIndex = 0; columnIndex < tableau.getWidth(); columnIndex++) {

            double oldCoefficientValue = tableau.getCoefficients().get(rowIndex).get(columnIndex);
            double newCoefficientValue = oldCoefficientValue - crossValue * pivotRow.get(columnIndex);

            tableau.getCoefficients().get(rowIndex).set(columnIndex, newCoefficientValue);
        }
    }

    private void actualizeConstants(Tableau tableau, double crossValue, int rowIndex, double pivotConstant) {

        double oldConstantValue = tableau.getConstants().get(rowIndex);
        double newConstantValue = oldConstantValue - crossValue * pivotConstant;

        tableau.getConstants().set(rowIndex, newConstantValue);
    }

    private void swap(Tableau tableau, int pivotRowIndex, int pivotColumnIndex) {

        tableau.getBaseVariables().remove(pivotRowIndex);
        Variable variableInPivotColumn = tableau.getNonBaseVariables().get(pivotColumnIndex);

        tableau.getBaseVariables().add(pivotRowIndex, variableInPivotColumn);
    }
}
