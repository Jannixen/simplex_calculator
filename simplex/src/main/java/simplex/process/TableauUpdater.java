package simplex.process;

import simplex.objects.Tableau;
import simplex.objects.Variable;

import java.util.ArrayList;


class TableauUpdater {

    void actualizeTableau(Tableau tableau, boolean ifMaximization) throws ArrayIndexOutOfBoundsException {

        PivotFinder pivotFinder = new PivotFinder();
        int pivotColumnIndex = pivotFinder.findPivotColumnIndex(tableau, ifMaximization);
        int pivotRowIndex = pivotFinder.findPivotRowIndex(tableau, pivotColumnIndex);


        swap(tableau, pivotRowIndex, pivotColumnIndex);
        actualizePivotRow(tableau, pivotRowIndex, pivotColumnIndex);
        actualizeRowsOtherThanPivotRow(tableau, pivotRowIndex, pivotColumnIndex);
    }

    private void actualizePivotRow(Tableau tableau, int pivotRowIndex, int pivotColumnIndex) {
        ArrayList<Double> pivotRow = tableau.getCoefficients().get(pivotRowIndex);

        double pivotConstant = tableau.getConstants().get(pivotRowIndex);
        double pivotValue = pivotRow.get(pivotColumnIndex);

        for (int colIndex = 0; colIndex < pivotRow.size(); colIndex++) {

            double newCellValue = pivotRow.get(colIndex) / pivotValue;

            pivotRow.set(colIndex, newCellValue);
            tableau.getConstants().set(pivotRowIndex, pivotConstant / pivotValue);
        }
    }

    private void actualizeRowsOtherThanPivotRow(Tableau tableau, int pivotRowIndex, int pivotColumnIndex) {

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

    private void actualizeCoefficients(
            Tableau tableau, double crossValue, int rowIndex, ArrayList<Double> pivotRow) {

        for (int colIndex = 0; colIndex < tableau.getWidth(); colIndex++) {

            double oldCoefficientValue = tableau.getCoefficients().get(rowIndex).get(colIndex);
            double newCoefficientValue = oldCoefficientValue - crossValue * pivotRow.get(colIndex);

            tableau.getCoefficients().get(rowIndex).set(colIndex, newCoefficientValue);
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
