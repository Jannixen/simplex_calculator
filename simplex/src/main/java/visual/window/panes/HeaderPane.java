package visual.window.panes;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.io.FileNotFoundException;

import static visual.window.panes.PanesProperties.*;

public class HeaderPane extends TemplateIconPane {

    public HeaderPane() {
        super(HEADER_PANE_POSITION_X, HEADER_PANE_POSITION_Y, HEADER_PANE_WIDTH, HEADER_PANE_HEIGHT, Color.WHITE, true);
        makeHeaderPane();
    }

    private void makeHeaderPane() {
        getChildren().add(makeTitleTextLabel("Simplex Method Calculator"));
        getChildren().add(makeIconImageView());
    }


    private Label makeTitleTextLabel(String titleName) {
        Label titleText = new Label(titleName);
        titleText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setWrapText(true);
        titleText.setMaxSize(150, 150);
        titleText.layoutXProperty().bind(widthProperty().subtract(titleText.widthProperty()).divide(1.2));
        titleText.layoutYProperty().bind(heightProperty().subtract(titleText.heightProperty()).divide(2));
        return titleText;
    }

    private ImageView makeIconImageView() {
        ImageView titleIconView = null;
        try {
            titleIconView = fetchIconImage("src/main/resources/Simplex-description-en.svg.png", TITLE_ICON_SIZE);
            titleIconView.layoutXProperty().bind(widthProperty().subtract(titleIconView.getFitWidth()).divide(6));
            titleIconView.layoutYProperty().bind(heightProperty().subtract(titleIconView.getFitHeight()).divide(12));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return titleIconView;
    }


}
