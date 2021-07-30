package simplex.process;

import simplex.objects.Tableau;

class OptimalityIndexCounter {

    void calculateOptimalityIndexes(Tableau tableau) {

        for (int columnNumber = 0; columnNumber < tableau.getWidth(); columnNumber++) {
            double newCalculation = calculateDotProduct(tableau, columnNumber);
            tableau.getDotProducts().set(columnNumber, newCalculation);

            double newOptimalityIndex =
                    tableau.getDotProducts().get(columnNumber)
                            - tableau.getNonBaseVariables().get(columnNumber).getCost();

            tableau.getOptimalityIndexes().set(columnNumber, newOptimalityIndex);
        }
    }

    private double calculateDotProduct(Tableau tableau, int columnNumber) {
        double dotProduct = 0;

        for (int rowNumber = 0; rowNumber < tableau.getLength(); rowNumber++) {
            dotProduct +=
                    tableau.getCoefficients().get(rowNumber).get(columnNumber)
                            * tableau.getBaseVariables().get(rowNumber).getCost();
        }
        return dotProduct;
    }
}
