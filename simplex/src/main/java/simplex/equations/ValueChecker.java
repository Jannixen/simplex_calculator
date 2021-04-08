package simplex.equations;


public final class ValueChecker {

    public static final ValueChecker valueChecker = new ValueChecker();

    String checkName(String name) {
        if (name.length() >= 0 & name.length() <= 50) return name;
        return null;
    }


    double checkCost(String strCost) {
        double cost;
        double roundedCost;
        try {
            cost = Double.parseDouble(strCost);
            roundedCost = Math.round(cost * 100.00) / 100.00;
            if (roundedCost >= 0 && roundedCost <= 100000) return roundedCost;
        } catch (NumberFormatException nfe) {
            return 0.0;
        }
        return 0.0;
    }

}
