package hotciv.view;

import minidraw.standard.ImageFigure;

import java.awt.*;

public class CivFigure extends ImageFigure {
    private final String type;

    public CivFigure(String typeString) {
        type = typeString;
    }

    public CivFigure(Image image, Point position, String typeString) {
        super(image, position);
        type = typeString;
    }

    public CivFigure(String imageName, Point position, String typeString) {
        super(imageName, position);
        type = typeString;
    }

    public String getType() {
        return type;
    }

}
