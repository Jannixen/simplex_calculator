package simplex.process;

import simplex.objects.Tableau;
import simplex.objects.Variable;

import java.util.ArrayList;


public class TableauUpdater {

    void actualizeTableau(Tableau tableau) {

        PivotFinder pivotFinder = new PivotFinder();

        int pivotColumnIndex = pivotFinder.findMaximumOptimalityIndex(tableau);
        int pivotRowIndex = pivotFinder.findMinimumQuantity(tableau, pivotColumnIndex);

        if (pivotColumnIndex == Integer.MAX_VALUE || pivotRowIndex == Integer.MAX_VALUE) {
            throw new ArrayIndexOutOfBoundsException();
        }

        swap(tableau, pivotRowIndex, pivotColumnIndex);
        actualizePivotRow(tableau, pivotRowIndex, pivotColumnIndex);
        actualizeOtherThanPivotRow(tableau, pivotRowIndex, pivotColumnIndex);
    }

    void actualizePivotRow(Tableau tableau, int pivotRowIndex, int pivotColumnIndex) {
        ArrayList<Double> pivotRow = tableau.getCoefficients().get(pivotRowIndex);
        double pivotValue = pivotRow.get(pivotColumnIndex);

        for (int i = 0; i < pivotRow.size(); i++) {
            double newCellValue = pivotRow.get(i) * pivotValue;
            pivotRow.set(i, newCellValue);
        }
    }

    void actualizeOtherThanPivotRow(Tableau tableau, int pivotRowIndex, int pivotColumnIndex) {

        ArrayList<Double> pivotRow = tableau.getCoefficients().get(pivotRowIndex);
        double pivotQuantity = tableau.getConstants().get(pivotRowIndex);

        for (int rowIndex = 0; rowIndex < tableau.getLength(); rowIndex++) {
            if (rowIndex != pivotRowIndex) {

                double crossValue = tableau.getCoefficients().get(rowIndex).get(pivotColumnIndex);

                actualizeCoefficients(tableau, crossValue, rowIndex, pivotRow);

                actualizeQuantities(tableau, crossValue, rowIndex, pivotQuantity);
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

    private void actualizeQuantities(
            Tableau tableau, double crossValue, int rowIndex, double pivotQuantity) {
        double oldQuantitiesValue = tableau.getConstants().get(rowIndex);
        double newQuantitiesValue = oldQuantitiesValue - crossValue * pivotQuantity;

        tableau.getConstants().set(rowIndex, newQuantitiesValue);
    }

    private void swap(Tableau tableau, int pivotRowIndex, int pivotColumnIndex) {

        tableau.getBaseVariables().remove(pivotRowIndex);
        Variable variableInPivotColumn = tableau.getNonBaseVariables().get(pivotColumnIndex);

        tableau.getBaseVariables().add(pivotRowIndex, variableInPivotColumn);
    }
}
