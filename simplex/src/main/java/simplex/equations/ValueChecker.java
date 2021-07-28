package simplex.equations;


public final class ValueChecker {

    public static final ValueChecker valueChecker = new ValueChecker();

    String checkName(String name) {
        if (name.length() > 0 & name.length() <= 50) return name;
        return null;
    }


    double checkNumber(String strNumber) throws NumberFormatException {
        double num;
        double roundedNum;

        num = Double.parseDouble(strNumber);
        roundedNum = Math.round(num * 100.00) / 100.00;
        if (roundedNum >= -100000 && roundedNum <= 100000) {
            return roundedNum;
        } else {
            throw new NumberFormatException();
        }

    }

}
