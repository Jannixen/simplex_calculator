package communication;

import javafx.scene.text.Text;

public final class InstructionsSender {

    private static final InstructionsSender instructionsSender = new InstructionsSender();

    public static InstructionsSender getInstructionSender() {
        return instructionsSender;
    }

    public void showUserInstruction(Instruction msg) {
        Text text = new Text(msg.instruction);
        //APPLICATION_WINDOW.textField.getChildren().add(text);
    }

    public void showUserInstruction(Instruction msg, int lineNumber) {
        Text text = new Text(msg.instruction + " " + lineNumber);
        //APPLICATION_WINDOW.textField.getChildren().add(text);
    }
}
