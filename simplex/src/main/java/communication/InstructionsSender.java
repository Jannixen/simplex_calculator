package communication;

import static visual.window.ApplicationWindow.APPLICATION_WINDOW;

public final class InstructionsSender {

    private static final InstructionsSender instructionsSender = new InstructionsSender();

    public static InstructionsSender getInstructionSender() {
        return instructionsSender;
    }

    public void showInstructionForUser(Instruction msg) {
        APPLICATION_WINDOW.getNotificationsPane().showNotification(msg.instruction);
    }

    public void showResults(String results) {
        APPLICATION_WINDOW.getNotificationsPane().showNotification(results);
    }
}
