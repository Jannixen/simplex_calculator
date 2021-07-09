package communication;


public enum Instruction {
    BAD_VARIABLE_OBJECTIVE("Variable is not properly written."),
    CONSTANT_LACK("Lack of constant."),
    NO_CONSTRAINTS("You have to write some constraint equations"),
    NO_OBJECTIVE("You have to write objective function"),
    NOT_NEEDED_VALUE("Some unneeded value is present in equations."),
    NOT_PROPER_EQUATION("Some equation has improper form. Please write it again."),
    BAD_NAME_OR_COST("Variable name should be not longer than 50 and cost value has to be lower than 100000."),
    LONG_TIME("Solving problem will take some time. Please wait."),
    UNSOLVABLE("Problem cannot be solved.");

    public final String instruction;

    Instruction(String msg) {
        this.instruction = msg;
    }
}
