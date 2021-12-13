package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class UnitMoveTool extends NullTool {
    private Game game;
    private DrawingEditor editor;
    private Position from, to;
    private Figure figure;
    int oldX, oldY;

    public UnitMoveTool(DrawingEditor e, Game g){
        this.editor = e;
        this.game = g;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y){
        figure = editor.drawing().findFigure(x,y);
        from = GfxConstants.getPositionFromXY(x,y);
        oldX = x; oldY = y;
    }

    @Override
    public void mouseDrag(MouseEvent mouseEvent, int x, int y){
        if (figure != null && GfxConstants.getPositionFromXY(x,y).getColumn()<16 && GfxConstants.getPositionFromXY(x,y).getRow()<16) {
            figure.moveBy(x - oldX, y - oldY);
            oldX = x; oldY = y;
        }
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent, int x, int y){
        if (figure != null) {
            Rectangle rect = figure.displayBox();
            to = GfxConstants.getPositionFromXY(rect.x + GfxConstants.TILESIZE / 2, rect.y + GfxConstants.TILESIZE / 2);
            game.moveUnit(from, to);
        }
    }
}
