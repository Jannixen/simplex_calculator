package visual.window;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

abstract class TemplateIconPane extends TemplatePane {

    TemplateIconPane(int x, int y, int width, int height, Color color, boolean ifWithBorder) {
        super(x, y, width, height, color, ifWithBorder);
    }

    ImageView fetchIconImage(String path, int size) throws FileNotFoundException {
        InputStream stream;
        stream = new FileInputStream(path);
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(size);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}
