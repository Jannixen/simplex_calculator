package communication;

import static visual.window.ApplicationWindow.getApplicationWindow;

public final class InstructionsSender {

    private static final InstructionsSender instructionsSender = new InstructionsSender();

    public static InstructionsSender getInstructionSender() {
        return instructionsSender;
    }

    public void showInstructionForUser(Instruction msg) {
        getApplicationWindow().getNotificationsPane().showNotification(msg.instruction);
    }

    public void showResults(String results) {
        getApplicationWindow().getNotificationsPane().showNotification(results);
    }
}
