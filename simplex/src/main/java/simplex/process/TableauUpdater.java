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
        ArrayList<Integer> pivotRow = tableau.getCoefficients().get(pivotRowIndex);
        int pivotValue = pivotRow.get(pivotColumnIndex);

        for (int i = 0; i < pivotRow.size(); i++) {
            int newCellValue = pivotRow.get(i) * pivotValue;
            pivotRow.set(i, newCellValue);
        }
    }

    void actualizeOtherThanPivotRow(Tableau tableau, int pivotRowIndex, int pivotColumnIndex) {

        ArrayList<Integer> pivotRow = tableau.getCoefficients().get(pivotRowIndex);
        int pivotQuantity = tableau.getConstants().get(pivotRowIndex);

        for (int rowIndex = 0; rowIndex < tableau.getLength(); rowIndex++) {
            if (rowIndex != pivotRowIndex) {

                int crossValue = tableau.getCoefficients().get(rowIndex).get(pivotColumnIndex);

                actualizeCoefficients(tableau, crossValue, rowIndex, pivotRow);

                actualizeQuantities(tableau, crossValue, rowIndex, pivotQuantity);
            }
        }
    }

    void actualizeCoefficients(
            Tableau tableau, int crossValue, int rowIndex, ArrayList<Integer> pivotRow) {

        for (int columnIndex = 0; columnIndex < tableau.getWidth(); columnIndex++) {

            int oldCoefficientValue = tableau.getCoefficients().get(rowIndex).get(columnIndex);
            int newCoefficientValue = oldCoefficientValue - crossValue * pivotRow.get(columnIndex);

            tableau.getCoefficients().get(rowIndex).set(columnIndex, newCoefficientValue);
        }
    }

    private void actualizeQuantities(
            Tableau tableau, int crossValue, int rowIndex, int pivotQuantity) {
        int oldQuantitiesValue = tableau.getConstants().get(rowIndex);
        int newQuantitiesValue = oldQuantitiesValue - crossValue * pivotQuantity;

        tableau.getConstants().set(rowIndex, newQuantitiesValue);
    }

    private void swap(Tableau tableau, int pivotRowIndex, int pivotColumnIndex) {

        tableau.getBaseVariables().remove(pivotRowIndex);
        Variable variableInPivotColumn = tableau.getNonBaseVariables().get(pivotColumnIndex);

        tableau.getBaseVariables().add(pivotRowIndex, variableInPivotColumn);
    }
}
