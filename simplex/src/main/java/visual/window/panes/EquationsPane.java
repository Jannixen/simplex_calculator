package visual.window.panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import simplex.equations.UserInputProcessor;

import java.io.FileNotFoundException;

import static visual.window.panes.PanesProperties.*;

public class EquationsPane extends TemplateIconPane {

    public EquationsPane() {
        super(EQUATIONS_PANE_POSITION_X, EQUATIONS_PANE_POSITION_Y, EQUATIONS_PANE_WIDTH, EQUATIONS_PANE_HEIGHT, Color.AZURE.darker(), false);
        makeEquationPane();
    }

    private void makeEquationPane() {
        TextArea objectiveTextArea = makeWriteObjectiveTextArea();
        TextArea constraintsTextArea = makeWriteConstraintsTextArea();
        getChildren().add(objectiveTextArea);
        getChildren().add(constraintsTextArea);
        getChildren().add(makeObjectiveLabel());
        getChildren().add(makeConstraintLabel());
        getChildren().add(makeSimplexStartButton(constraintsTextArea, objectiveTextArea));
        getChildren().add(makeMinMaxSwitcher());
    }


    private Label makeObjectiveLabel() {
        Label instruction = new Label("Objective function:");

        instruction.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 10));
        instruction.setTextAlignment(TextAlignment.LEFT);
        instruction.layoutXProperty().bind(widthProperty().subtract(instruction.widthProperty()).divide(2));
        instruction.layoutYProperty().bind(heightProperty().subtract(instruction.heightProperty()).divide(14));

        return instruction;
    }

    private TextArea makeWriteObjectiveTextArea() {
        TextArea textArea = new TextArea("5x1 + 10x2 + 8x3");

        textArea.layoutXProperty().bind(widthProperty().subtract(textArea.widthProperty()).divide(2));
        textArea.layoutYProperty().bind(heightProperty().subtract(textArea.heightProperty()).divide(6));

        textArea.setPrefHeight(0.1 * EQUATIONS_PANE_HEIGHT);
        textArea.setPrefWidth(0.8 * EQUATIONS_PANE_WIDTH);

        return textArea;
    }

    private Label makeConstraintLabel() {
        Label instruction = new Label("Constraints:");

        instruction.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 10));
        instruction.setTextAlignment(TextAlignment.LEFT);
        instruction.layoutXProperty().bind(widthProperty().subtract(instruction.widthProperty()).divide(2));
        instruction.layoutYProperty().bind(heightProperty().subtract(instruction.heightProperty()).divide(3));

        return instruction;
    }

    private TextArea makeWriteConstraintsTextArea() {
        TextArea textArea = new TextArea("3x1 + 5x2 + 2x3 >= 60 \n 4x1 + 4x2 + 4x3 <= 72 \n 2x1 + 4x2 + 5x3 <= 100");

        textArea.layoutXProperty().bind(widthProperty().subtract(textArea.widthProperty()).divide(2));
        textArea.layoutYProperty().bind(heightProperty().subtract(textArea.heightProperty()).divide(1.5));

        textArea.setPrefHeight(0.4 * EQUATIONS_PANE_HEIGHT);
        textArea.setPrefWidth(0.8 * EQUATIONS_PANE_WIDTH);

        return textArea;
    }


    private HBox makeMinMaxSwitcher() {
        ToggleGroup minMaxSwitcherGroup = new ToggleGroup();

        ToggleButton toggleButtonMin = new ToggleButton("Minimization");
        ToggleButton toggleButtonMax = new ToggleButton("Maximization");
        toggleButtonMin.setToggleGroup(minMaxSwitcherGroup);
        toggleButtonMax.setToggleGroup(minMaxSwitcherGroup);

        HBox hbox = new HBox(toggleButtonMin, toggleButtonMax);
        hbox.layoutXProperty().bind(widthProperty().subtract(hbox.widthProperty()).divide(6));
        hbox.layoutYProperty().bind(heightProperty().subtract(hbox.heightProperty()).divide(1.05));

        return hbox;
    }


    private Button makeSimplexStartButton(TextArea constraintsTextArea, TextArea objectiveTextArea) {
        Button button = new Button("Start process");
        try {
            button = new Button("Start process", fetchIconImage("src/main/resources/calculator_icon.png", 30));
            button.layoutXProperty().bind(widthProperty().subtract(button.widthProperty()).divide(1.2));
            button.layoutYProperty().bind(heightProperty().subtract(button.heightProperty()).divide(1.05));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        button.setOnAction(makeEventHandler(constraintsTextArea, objectiveTextArea));
        return button;
    }

    private EventHandler makeEventHandler(TextArea constraintsTextArea, TextArea objectiveTextArea) {
        EventHandler<ActionEvent> buttonHandler = event -> {
            String constraintsString = constraintsTextArea.getText();
            String objectiveString = objectiveTextArea.getText();
            UserInputProcessor userInputProcessor = new UserInputProcessor(objectiveString, constraintsString);
            event.consume();
        };
        return buttonHandler;
    }


}
